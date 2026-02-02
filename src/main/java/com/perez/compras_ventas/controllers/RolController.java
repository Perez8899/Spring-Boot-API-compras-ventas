package com.perez.compras_ventas.controllers;

import java.util.List;

import com.perez.compras_ventas.dto.request.RolRequest;
import com.perez.compras_ventas.dto.response.RolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.perez.compras_ventas.entity.Rol;
import com.perez.compras_ventas.services.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {

    // listar roles GET /rol
    // Obtener rol por id GET /rol/{x}
    //crear rol POST /rol

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Rol> findAllRoles(){
        return rolService.findAllRoles();
    }

    //   /rol/{id} -> /rol/1    Path Variable
    @GetMapping("/{id}")
    public RolResponse findRolById(@PathVariable Integer id){
        return rolService.findRolById(id);
    }

    @PostMapping()
    public Rol createROl(@RequestBody RolRequest rolRequest) {
        return rolService.createRol(rolRequest);
    }

    @PutMapping("/{id}")
    public Rol updateRol(@PathVariable Integer id, @RequestBody RolRequest rol) {
        return rolService.updateRol(id, rol);
    }

    @DeleteMapping("/{id}")
    public void deleteRol(@PathVariable Integer id){
        rolService.deleteRolById(id);
    }


}
