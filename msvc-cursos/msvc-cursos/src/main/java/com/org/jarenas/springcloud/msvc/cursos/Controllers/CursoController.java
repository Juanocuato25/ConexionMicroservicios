package com.org.jarenas.springcloud.msvc.cursos.Controllers;


import com.org.jarenas.springcloud.msvc.cursos.Model.Entity.Curso;
import com.org.jarenas.springcloud.msvc.cursos.Service.CursoService;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;


    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Curso> o = cursoService.porId(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> o = cursoService.porId(id);
        if (o.isPresent()){
        Curso cursoDb = o.get();
        cursoDb.setNombreCurso(curso.getNombreCurso());
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> o = cursoService.porId(id);
        if (o.isPresent()){
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
