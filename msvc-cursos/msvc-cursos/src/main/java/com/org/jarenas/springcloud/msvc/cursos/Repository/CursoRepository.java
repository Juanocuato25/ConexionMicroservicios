package com.org.jarenas.springcloud.msvc.cursos.Repository;

import com.org.jarenas.springcloud.msvc.cursos.Model.Entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

}
