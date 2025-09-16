package com.example.ms_cursos.service;

import com.example.ms_cursos.entity.Curso;

import java.util.List;

public interface CursoService {
    Curso save(Curso curso);
    Curso update(Long id, Curso curso);
    void delete(Long id);
    Curso findById(Long id);
    List<Curso> findAll();
    boolean existsById(Long id);
}