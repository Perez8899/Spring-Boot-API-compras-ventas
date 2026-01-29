package com.perez.compras_ventas.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.perez.compras_ventas.dto.request.PermisoRequest;
import org.springframework.stereotype.Service;

import com.perez.compras_ventas.dto.response.PermisoResponse;
import com.perez.compras_ventas.entity.Permiso;
import com.perez.compras_ventas.repository.PermisoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor                           //constructor
@Service
public class PermisoServiceImpl implements PermisoService {

    private final PermisoRepository permisoRepository; //injection

//-----------------------------------------------------------------In the users class, clean code is used.-----------------
    @Override
    public List<PermisoResponse> findAllPermiso() {
        List<Permiso> permisosRetrieved = permisoRepository.findAll();
        List<PermisoResponse> permisoResponses = new ArrayList<>();

        for (Permiso permiso : permisosRetrieved){
            PermisoResponse permisoResponse = new PermisoResponse();
            permisoResponse.setDescripcion(permiso.getDescripcion());
            permisoResponse.setNombre(permiso.getNombre());
            permisoResponse.setAccion(permiso.getAccion());
            permisoResponse.setRecurso(permiso.getRecurso());

            permisoResponses.add(permisoResponse);
        }
        return permisoResponses;
    }

    @Override
    public PermisoResponse findPermisoById(Integer id) {
        Permiso permisoRetrevied = permisoRepository.findById(id).get();
        PermisoResponse permisoResponse = new PermisoResponse();

        permisoResponse.setId(permisoRetrevied.getIdPer());
        permisoResponse.setNombre(permisoRetrevied.getNombre());
        permisoResponse.setDescripcion(permisoRetrevied.getDescripcion());
        permisoResponse.setAccion(permisoRetrevied.getAccion());
        permisoResponse.setRecurso(permisoRetrevied.getRecurso());

        return permisoResponse;
    }

    @Override
    public PermisoResponse createPermiso(PermisoRequest permisoRequest) {
        Permiso permisoToCreate = new Permiso();

        permisoToCreate.setNombre(permisoRequest.getNombre());
        permisoToCreate.setDescripcion(permisoRequest.getDescripcion());
        permisoToCreate.setAccion(permisoRequest.getAccion());
        permisoToCreate.setRecurso(permisoRequest.getRecurso());
        permisoToCreate.setFechaCreacion(LocalDateTime.now());
        permisoToCreate.setUsuarioCreacion(1);

        Permiso permisoSaved = permisoRepository.save(permisoToCreate);
        PermisoResponse permisoResponse = new PermisoResponse();

        permisoResponse.setId(permisoSaved.getIdPer());
        permisoResponse.setNombre(permisoSaved.getNombre());
        permisoResponse.setDescripcion(permisoSaved.getDescripcion());
        permisoResponse.setAccion(permisoSaved.getAccion());
        permisoResponse.setRecurso(permisoSaved.getRecurso());

        return permisoResponse;
    }

    @Override
    public PermisoResponse updatePermiso(Integer id, PermisoRequest permisoRequest) {
        Permiso permisoRetrieved = permisoRepository.findById(id).get();

        permisoRetrieved.setNombre(permisoRequest.getNombre());
        permisoRetrieved.setDescripcion(permisoRequest.getDescripcion());
        permisoRetrieved.setAccion(permisoRequest.getAccion());
        permisoRetrieved.setRecurso(permisoRequest.getRecurso());
        permisoRetrieved.setFechaModificacion(LocalDateTime.now());
        permisoRetrieved.setUsuarioModificacion(1);

        Permiso permisoUpdated = permisoRepository.save(permisoRetrieved);
        PermisoResponse permisoResponse = new PermisoResponse();

        permisoResponse.setId(permisoUpdated.getIdPer());
        permisoResponse.setDescripcion(permisoUpdated.getDescripcion());
        permisoResponse.setNombre(permisoUpdated.getNombre());
        permisoResponse.setAccion(permisoUpdated.getAccion());
        permisoResponse.setRecurso(permisoUpdated.getRecurso());

        return permisoResponse;
    }

    @Override
    public void deletePermisoById(Integer id) {
        permisoRepository.deleteById(id);
    }

}
