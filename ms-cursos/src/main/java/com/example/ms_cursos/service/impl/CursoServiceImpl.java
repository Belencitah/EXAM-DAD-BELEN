package com.example.ms_cursos.service.impl;

import com.example.ms_cursos.entity.Curso;
import com.example.ms_cursos.repository.CursoRepository;
import com.example.ms_cursos.service.CursoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repo;

    public CursoServiceImpl(CursoRepository repo) { this.repo = repo; }

    @Override
    public Curso save(Curso curso) {
        return repo.save(curso);
    }

    @Override
    public Curso update(Long id, Curso data) {
        Curso c = repo.findById(id).orElseThrow(() -> new RuntimeException("Curso not found"));
        c.setNombreCurso(data.getNombreCurso());
        c.setCodigo(data.getCodigo());
        return repo.save(c);
    }

    @Override
    public void delete(Long id) { repo.deleteById(id); }

    @Override
    @Transactional(readOnly = true)
    public Curso findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Curso not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() { return repo.findAll(); }

    @Override
    public boolean existsById(Long id) { return repo.existsById(id); }
}