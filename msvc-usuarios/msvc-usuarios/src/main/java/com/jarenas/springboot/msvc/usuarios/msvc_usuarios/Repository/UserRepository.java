package com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Repository;

import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Model.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    //QueryMethod
    Optional<User> findByEmail(String email);

    /*
    //With Query
    @Query("SELECT u FROM User as u WHERE u.email=?1")
    Optional<User> porEmail(String email);*/

    //Usando palabra clave
    boolean existsByEmail(String email);

}
