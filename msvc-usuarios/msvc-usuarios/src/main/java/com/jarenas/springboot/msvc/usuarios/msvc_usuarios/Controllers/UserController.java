package com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Controllers;


import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Model.Entity.User;
import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> crear(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.guardar(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody User user, @PathVariable Long id ){
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

}
