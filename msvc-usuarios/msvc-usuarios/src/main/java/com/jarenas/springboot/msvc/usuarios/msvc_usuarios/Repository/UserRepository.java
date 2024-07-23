package com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Repository;

import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Model.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
