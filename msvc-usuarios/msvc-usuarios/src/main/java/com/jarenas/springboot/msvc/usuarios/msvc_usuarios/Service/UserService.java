package com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Service;

import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Model.Entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> listar();

    Optional<User> porId(Long id);

    User guardar(User user);

    void eliminar(Long id);

    Optional<User> porEmail(String email);


    boolean existePorEmail(String email);
}
