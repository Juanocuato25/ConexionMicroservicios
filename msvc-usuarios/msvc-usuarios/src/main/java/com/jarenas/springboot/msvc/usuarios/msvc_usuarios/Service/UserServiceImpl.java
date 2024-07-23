package com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Service;

import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Model.Entity.User;
import com.jarenas.springboot.msvc.usuarios.msvc_usuarios.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> listar() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> porId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User guardar(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        userRepository.deleteById(id);
    }
}