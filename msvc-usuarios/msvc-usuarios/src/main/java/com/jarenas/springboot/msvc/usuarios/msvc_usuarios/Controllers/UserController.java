package com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Controllers;


import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Model.Entity.User;
import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(userService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<User> optionalUser = userService.porId(id);
        if (optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crear(@Valid @RequestBody User user, BindingResult result){
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id ){
        if (result.hasErrors()){
            return validar(result);
        }

        Optional<User> ou = userService.porId(id);
        if (ou.isPresent()){
            User userDB = ou.get();
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(userDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<User> optionalUser = userService.porId(id);
        if (optionalUser.isPresent()){
            userService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error ->{
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
