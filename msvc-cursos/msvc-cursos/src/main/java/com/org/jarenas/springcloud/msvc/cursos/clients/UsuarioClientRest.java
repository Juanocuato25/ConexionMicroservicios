package com.org.jarenas.springcloud.msvc.cursos.clients;

import com.org.jarenas.springcloud.msvc.cursos.Model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-usuarios", url="localhost:8001/users")
public interface UsuarioClientRest {

    @GetMapping
    List<Usuario> listar();

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping
    Usuario crear(@RequestBody Usuario usuario);

    @PutMapping
    Usuario editar(@RequestBody Usuario usuario, @PathVariable Long id);



}
