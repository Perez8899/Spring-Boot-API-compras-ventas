package com.perez.compras_ventas.services;

import com.perez.compras_ventas.dto.request.MovimientoRequest;
import com.perez.compras_ventas.dto.request.NotaRequest;
import com.perez.compras_ventas.dto.response.ClienteResponse;
import com.perez.compras_ventas.dto.response.NotaResponse;
import com.perez.compras_ventas.entity.*;
import com.perez.compras_ventas.repository.AlmacenProductRepository;
import com.perez.compras_ventas.repository.ClienteRepository;
import com.perez.compras_ventas.repository.MovimientoRepository;
import com.perez.compras_ventas.repository.NotaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService{

    private final NotaRepository notaRepository;
    private final MovimientoRepository movimientoRepository;
    private final AlmacenProductRepository almacenProductoRepository;
    private final ClienteRepository clienteRepository;
    private final EntityManager entityManager;

    @Override
    public List<NotaResponse> findAllNotas() {
        try {
            return notaRepository.findAll().stream().map(nota-> NotaResponse.fromEntity(nota))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public NotaResponse createNota(NotaRequest notaRequest) {
        try {
            //CREATE NOTA
            Nota notaToCreate = NotaRequest.toEntity(notaRequest);
            notaToCreate.setCliente(clienteRepository.findById(notaRequest.getClientId()).get());
            notaToCreate.setUsuario(entityManager.getReference(Usuario.class, notaRequest.getUsuId()));
            notaToCreate.setFecha(LocalDateTime.now());
            Nota notaCreated = notaRepository.save(notaToCreate);
            //CREATE MOVIMIENTOS & VALIDATE STOCK
            List<Movimiento> movimientosCreated = new ArrayList<>();
            for(MovimientoRequest movimientoRequest : notaRequest.getMovimientos()){
                if(validStock(movimientoRequest)){
                    Movimiento movimientoToCreate = MovimientoRequest.toEntity(movimientoRequest);
                    movimientoToCreate.setAlmacen(entityManager.getReference(Almacen.class, movimientoRequest.getAlmacenId()));
                    movimientoToCreate.setProducto(entityManager.getReference(Producto.class, movimientoRequest.getProductoId()));
                    movimientoToCreate.setNota(notaCreated);
                    movimientosCreated.add(movimientoRepository.save(movimientoToCreate));
                }else {
                    throw new RuntimeException("error al validar el stock");
                }
            }
            //UPDATE STOCK
            for(Movimiento movimiento : movimientosCreated){
                AlmacenProducto almacenProductoRetrieved = almacenProductoRepository.findByAlmacen_IdAndProducto_IdProducto(movimiento.getAlmacen().getId(), movimiento.getProducto().getIdProducto())
                        .orElseThrow(()->new RuntimeException("No se encontro el producto en el almacen"));
                switch (movimiento.getTipoMovimiento()) {
                    case "COMPRA":
                        almacenProductoRetrieved.setStock(almacenProductoRetrieved.getStock() + movimiento.getCantidad());
                        break;
                    case "VENTA":
                        almacenProductoRetrieved.setStock(almacenProductoRetrieved.getStock() - movimiento.getCantidad());
                        break;
                    default:
                        break;
                }
                almacenProductoRepository.save(almacenProductoRetrieved);
            }
            return NotaResponse.fromEntity(notaCreated);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ClienteResponse> findAllClientes() {
        return clienteRepository.findAll().stream()
                .map(cliente-> ClienteResponse.fromEntity(cliente)).collect(Collectors.toList());
    }

    private boolean validStock(MovimientoRequest movimientoRequest){
        try{
            return  movimientoRequest.getTipoMovimiento().equals("VENTA")
                    && movimientoRequest.getCantidad() <= almacenProductoRepository
                    .findByAlmacen_IdAndProducto_IdProducto(movimientoRequest.getAlmacenId(), movimientoRequest.getProductoId())
                    .get().getStock();
        }
        catch (Exception e) {
            throw new RuntimeException("Error al obtener producto en almacen");
        }
    }

}