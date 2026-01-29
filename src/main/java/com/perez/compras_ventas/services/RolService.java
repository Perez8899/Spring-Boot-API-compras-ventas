package com.perez.compras_ventas.services;

import java.util.List;
import java.util.stream.Collectors;

import com.perez.compras_ventas.dto.request.RolRequest;
import com.perez.compras_ventas.dto.response.RolResponse;
import com.perez.compras_ventas.entity.Permiso;
import com.perez.compras_ventas.entity.PermisoRol;
import com.perez.compras_ventas.repository.PermisoRepository;
import com.perez.compras_ventas.repository.RolPermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.perez.compras_ventas.entity.Rol;
import com.perez.compras_ventas.repository.RolRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    private final RolPermisoRepository rolPermisoRepository;

    private final PermisoRepository permisoRepository;

    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }

    public RolResponse findRolById(Integer rolId) {
        try {
            Rol rolRetrieved = rolRepository.findById(rolId).orElseThrow(
                    () -> new RuntimeException("Rol no encontrado"));
            return RolResponse.fromEntity(rolRetrieved,
                    rolPermisoRepository.findByRol(rolRetrieved).stream()
                            .map(rolPermiso -> rolPermiso.getPermiso().getIdPer()).collect(Collectors.toList()));
        } catch (Exception e) {
            throw e;
        }

    }

    @Transactional
    public Rol createRol(RolRequest rol) {
        try {
            Rol rolCreated = rolRepository.save(RolRequest.toEntity(rol));
            rol.getPermisos().stream().map(permisoId -> {
                Permiso permiso = permisoRepository.findById(permisoId)
                        .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
                rolPermisoRepository.save(PermisoRol.builder()
                        .permiso(permiso)
                        .rol(rolCreated)
                        .build());
                return permiso;
            });
            return rolCreated;
        } catch (Exception e) {
            throw e;
        }

    }

    @Transactional
    public Rol updateRol(Integer id, RolRequest rol) {
        try {
            Rol rolRetrieved = rolRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("Rol no encontrado"));
            rolRetrieved.setNombre(rol.getNombre());
            rolRetrieved.setDescripcion(rol.getDescripcion());
            rolPermisoRepository.deleteAll();
            rol.getPermisos().stream().map(permisoId -> {
                Permiso permiso = permisoRepository.findById(permisoId)
                        .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
                rolPermisoRepository.save(PermisoRol.builder()
                        .permiso(permiso)
                        .rol(rolRetrieved)
                        .build());
                return permiso;
            });
            return rolRetrieved;
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteRolById(Integer rolId) {
        rolRepository.deleteById(rolId);
    }
}