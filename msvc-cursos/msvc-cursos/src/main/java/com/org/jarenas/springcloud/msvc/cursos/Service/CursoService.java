package com.org.jarenas.springcloud.msvc.cursos.Service;

import com.org.jarenas.springcloud.msvc.cursos.Model.Entity.Curso;
import com.org.jarenas.springcloud.msvc.cursos.Model.Usuario;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
    Optional<Curso> porIdConUsuarios(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoid);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoid);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoid);

}
