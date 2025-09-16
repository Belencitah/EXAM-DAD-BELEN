package com.example.ms_cursos.controller;

import com.example.ms_cursos.entity.Curso;
import com.example.ms_cursos.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTOs ligeros dentro del mismo archivo para simplicidad
record CursoRequest(@NotBlank String nombreCurso, @NotBlank String codigo) {}
record CursoResponse(Long id, String nombreCurso, String codigo) {}

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) { this.service = service; }

    private static CursoResponse toResponse(Curso e) {
        return new CursoResponse(e.getId(), e.getNombreCurso(), e.getCodigo());
    }

    @PostMapping
    public ResponseEntity<CursoResponse> create(@Valid @RequestBody CursoRequest req) {
        Curso saved = service.save(Curso.builder()
                .nombreCurso(req.nombreCurso())
                .codigo(req.codigo())
                .build());
        return ResponseEntity.ok(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody CursoRequest req) {
        Curso updated = service.update(id, Curso.builder()
                .nombreCurso(req.nombreCurso())
                .codigo(req.codigo())
                .build());
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CursoResponse>> list() {
        return ResponseEntity.ok(service.findAll().stream().map(CursoController::toResponse).toList());
    }

    // Endpoint para validación remota desde ms-matricula
    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.existsById(id));
    }
}