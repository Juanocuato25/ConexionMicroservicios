package com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Controllers;


import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Model.Entity.User;
import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(userService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<User> optionalUser = userService.porId(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        if (!user.getEmail().isBlank() && userService.existePorEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("error", "Ya existe un usuario con ese correo ingresado!"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<User> ou = userService.porId(id);
        if (ou.isPresent()) {
            User userDB = ou.get();

            if (!user.getEmail().isBlank() && !user.getEmail().equalsIgnoreCase(userDB.getEmail()) && userService.porEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("error", "Ya existe un usuario con ese correo ingresado!"));
            }
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(userDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<User> optionalUser = userService.porId(id);
        if (optionalUser.isPresent()) {
            userService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
        return ResponseEntity.ok(userService.listarPorIds(ids));
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
