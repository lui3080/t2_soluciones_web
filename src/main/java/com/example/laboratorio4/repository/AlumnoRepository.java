package com.example.laboratorio4.repository;

import com.example.laboratorio4.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}