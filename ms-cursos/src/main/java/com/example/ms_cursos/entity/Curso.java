package com.example.ms_cursos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "curso", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_curso", nullable = false, length = 120)
    @NotBlank
    private String nombreCurso;

    @Column(name = "codigo", nullable = false, length = 30)
    @NotBlank
    private String codigo;
}