package com.perez.compras_ventas.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.github.javafaker.Faker;
import com.perez.compras_ventas.entity.*;
import com.perez.compras_ventas.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final PermisoRepository permisoRepository;
    private final CategoriaRepository categoriaRepository;
    private final RolPermisoRepository rolPermisoRepository;
    private final RolRepository rolRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductRepository productoRepository;
    private final AlmacenRepository almacenRepository;
    private final AlmacenProductRepository almacenProductoRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        log.info("Iniciando DataSeeder...");
        Faker faker = new Faker();
        Random random = new Random();

        // Paso 1: Crear Permisos
        List<Permiso> allPermisos = new ArrayList<>();
        if (permisoRepository.count() == 0) {
            List<String> resources = List.of("usuarios", "roles", "permisos", "productos", "clientes", "ventas", "reportes");
            List<String> actions = List.of("create", "read", "update", "delete", "list");

            for (String resource : resources) {
                for (String action : actions) {
                    Permiso permiso = Permiso.builder()
                            .nombre(action.toUpperCase() + "_" + resource.toUpperCase())
                            .recurso(resource)
                            .accion(action)
                            .descripcion("Permiso para " + action + " en " + resource)
                            .build();
                    allPermisos.add(permisoRepository.save(permiso));
                }
            }
            log.info("✓ Permisos creados: {}", allPermisos.size());
        } else {
            allPermisos = permisoRepository.findAll();
        }

        // Paso 2: Crear Roles
        List<Rol> roles = new ArrayList<>();
        if (rolRepository.count() == 0) {
            Rol adminRol = Rol.builder()
                    .nombre("ADMIN")
                    .descripcion("Administrador con acceso completo al sistema")
                    .build();
            roles.add(rolRepository.save(adminRol));

            Rol managerRol = Rol.builder()
                    .nombre("MANAGER")
                    .descripcion("Gerente con permisos de gestión")
                    .build();
            roles.add(rolRepository.save(managerRol));

            Rol userRol = Rol.builder()
                    .nombre("USER")
                    .descripcion("Usuario estándar con permisos de lectura")
                    .build();
            roles.add(rolRepository.save(userRol));

            Rol sellerRol = Rol.builder()
                    .nombre("SELLER")
                    .descripcion("Vendedor con permisos de ventas")
                    .build();
            roles.add(rolRepository.save(sellerRol));

            log.info("✓ Roles creados: {}", roles.size());
        } else {
            roles = rolRepository.findAll();
        }

        // Paso 3: Crear Permisos_Roles (CORREGIDO)
        if (rolPermisoRepository.count() == 0 && !allPermisos.isEmpty() && !roles.isEmpty()) {
            for (Rol rol : roles) {
                // Asignar todos los permisos a ADMIN
                if (rol.getNombre().equals("ADMIN")) {
                    for (Permiso permiso : allPermisos) {
                        PermisoRol permisoRol = PermisoRol.builder()
                                .permiso(permiso)
                                .rol(rol)
                                .build();
                        rolPermisoRepository.save(permisoRol);
                    }
                }
                // Asignar permisos específicos a otros roles
                else if (rol.getNombre().equals("MANAGER")) {
                    for (Permiso permiso : allPermisos) {
                        if (permiso.getAccion().equals("read") || permiso.getAccion().equals("list")) {
                            PermisoRol permisoRol = PermisoRol.builder()
                                    .permiso(permiso)
                                    .rol(rol)
                                    .build();
                            rolPermisoRepository.save(permisoRol);
                        }
                    }
                }
                // Asignar solo lectura a USER
                else if (rol.getNombre().equals("USER")) {
                    for (Permiso permiso : allPermisos) {
                        if (permiso.getAccion().equals("read") || permiso.getAccion().equals("list")) {
                            PermisoRol permisoRol = PermisoRol.builder()
                                    .permiso(permiso)
                                    .rol(rol)
                                    .build();
                            rolPermisoRepository.save(permisoRol);
                        }
                    }
                }
                // Asignar permisos de ventas a SELLER
                else if (rol.getNombre().equals("SELLER")) {
                    for (Permiso permiso : allPermisos) {
                        if (permiso.getRecurso().equals("ventas") ||
                                (permiso.getRecurso().equals("clientes") && permiso.getAccion().equals("read"))) {
                            PermisoRol permisoRol = PermisoRol.builder()
                                    .permiso(permiso)
                                    .rol(rol)
                                    .build();
                            rolPermisoRepository.save(permisoRol);
                        }
                    }
                }
            }
            log.info("✓ Permisos_Roles creados");
        }

        // Paso 4: Crear Usuarios (CORREGIDO - usando roles persistidos)
        if (usuarioRepository.count() == 0 && !roles.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                // Seleccionar roles aleatorios para cada usuario
                List<Rol> rolesForUser = new ArrayList<>();
                int numRoles = random.nextInt(roles.size()) + 1;
                for (int j = 0; j < numRoles; j++) {
                    Rol rol = roles.get(random.nextInt(roles.size()));
                    if (!rolesForUser.contains(rol)) {
                        rolesForUser.add(rol);
                    }
                }

                Usuario usuario = Usuario.builder()
                        .nombre(faker.name().firstName())
                        .apellido(faker.name().lastName())
                        .correo(faker.internet().emailAddress())
                        .direccion(faker.address().fullAddress())
                        .estado("ACTIVO")
                        .fechaNacimiento(LocalDate.now().minusYears(random.nextInt(30) + 18))
                        .dni(faker.number().digits(8))
                        .userName(faker.name().username())
                        .telefono(faker.phoneNumber().cellPhone())
                        .password(passwordEncoder.encode("123456"))
                        .build();

                // Establecer la relación después de crear el usuario
                usuario.setRoles(rolesForUser);
                usuarioRepository.save(usuario);
            }
            log.info("✓ Usuarios creados");
        }

        // Paso 5: Crear Categorías y Productos
        if (categoriaRepository.count() == 0) {
            List<Categoria> categorias = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Categoria categoriaSaved = categoriaRepository.save(Categoria.builder()
                        .descripcion(faker.lorem().sentence(10))
                        .nombre(faker.commerce().department())
                        .build());
                categorias.add(categoriaSaved);
            }

            for (int i = 0; i < 100; i++) { // Reducido a 100 para pruebas
                Categoria categoria = categorias.get(random.nextInt(categorias.size()));
                productoRepository.save(Producto.builder()
                        .estado(true)
                        .codigoBarra(faker.code().ean13())
                        .descripcion(faker.commerce().productName())
                        .imagen("image-product-example.png")
                        .marca(faker.company().name())
                        .nombre(faker.commerce().productName())
                        .precioVenta(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 1000)))
                        .categoria(categoria)
                        .build());
            }
            log.info("✓ Categorías y Productos creados");
        }

        // Paso 6: Crear Sucursales y Almacenes
        if (sucursalRepository.count() == 0) {
            List<Sucursal> sucursales = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Sucursal sucursal = Sucursal.builder()
                        .nombre("Sucursal " + faker.company().name())
                        .direccion(faker.address().fullAddress())
                        .telefono(faker.phoneNumber().cellPhone())
                        .build();
                sucursales.add(sucursalRepository.save(sucursal));
            }

            // Crear Almacenes
            List<Almacen> almacenes = new ArrayList<>();
            String[] tiposAlmacen = { "Principal", "Secundario", "Temporal" };

            for (int i = 0; i < 10; i++) {
                Sucursal sucursal = sucursales.get(i / 2);
                Almacen almacen = Almacen.builder()
                        .nombre("Almacén " + tiposAlmacen[random.nextInt(tiposAlmacen.length)] + " " + sucursal.getNombre())
                        .codigo("ALM-" + String.format("%03d", i + 1))
                        .descripcion(faker.lorem().sentence(6))
                        .sucursal(sucursal)
                        .build();
                almacenes.add(almacenRepository.save(almacen));
            }

            // Crear relaciones AlmacenProducto
            List<Producto> productos = productoRepository.findAll();
            if (!productos.isEmpty() && !almacenes.isEmpty()) {
                for (Almacen almacen : almacenes) {
                    for (int i = 0; i < 20; i++) { // Reducido a 20 para pruebas
                        Producto producto = productos.get(random.nextInt(productos.size()));
                        AlmacenProducto almacenProducto = AlmacenProducto.builder()
                                .stock(faker.number().numberBetween(0, 500))
                                .fechaActualizacion(LocalDateTime.now())
                                .almacen(almacen)
                                .producto(producto)
                                .build();
                        almacenProductoRepository.save(almacenProducto);
                    }
                }
            }
            log.info("✓ Sucursales y Almacenes creados");
        }

        // Paso 7: Crear Clientes
        if (clienteRepository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                Cliente cliente = Cliente.builder()
                        .razonSocial(faker.company().name())
                        .estado(true)
                        .nroIdentificacion(faker.number().digits(11))
                        .telefono(faker.phoneNumber().cellPhone())
                        .direccion(faker.address().fullAddress())
                        .correo(faker.internet().emailAddress())
                        .tipo(faker.options().option("EMPRESA", "PERSONA", "COOPERATIVA"))
                        .build();
                clienteRepository.save(cliente);
            }
            log.info("✓ Clientes creados");
        }

        log.info("✅ DataSeeder completado exitosamente");
    }

    private String generatePhoneNumber(Faker faker) {
        return faker.phoneNumber().cellPhone();
    }
}