package com.org.jarenas.springcloud.msvc.cursos.Repository;

import com.org.jarenas.springcloud.msvc.cursos.Model.Entity.Curso;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CursoRepository extends CrudRepository<Curso, Long> {

    @Modifying
    @Query("DELETE FROM CursoUsuario as cu WHERE cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long id);


}
