package com.org.jarenas.springcloud.msvc.cursos.Controllers;


import com.org.jarenas.springcloud.msvc.cursos.Model.Entity.Curso;
import com.org.jarenas.springcloud.msvc.cursos.Service.CursoService;
import jakarta.validation.Valid;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Curso> o = cursoService.porId(id);
        if (o.isPresent()) {
            Curso cursoDb = o.get();
            cursoDb.setNombreCurso(curso.getNombreCurso());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Curso> o = cursoService.porId(id);
        if (o.isPresent()) {
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
