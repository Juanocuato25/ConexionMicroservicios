package com.org.jarenas.springcloud.msvc.cursos.Service;

import com.org.jarenas.springcloud.msvc.cursos.Model.Entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
}
