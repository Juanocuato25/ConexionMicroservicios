package com.org.jarenas.springcloud.msvc.cursos.Model.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @NotEmpty
    private String nombreCurso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}
