package com.example.laboratorio4.service;

import com.example.laboratorio4.model.Alumno;
import com.example.laboratorio4.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> listarAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        for (Alumno a : alumnos) {
            calcularEstado(a);
        }
        return alumnos;
    }

    public Optional<Alumno> obtenerPorId(Long id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        alumno.ifPresent(this::calcularEstado);
        return alumno;
    }

    public Alumno guardarAlumno(Alumno alumno) {
        calcularEstado(alumno);
        return alumnoRepository.save(alumno);
    }

    public Alumno actualizarAlumno(Long id, Alumno alumnoDetalles) {
        if (alumnoRepository.existsById(id)) {
            alumnoDetalles.setId(id);
            calcularEstado(alumnoDetalles);
            return alumnoRepository.save(alumnoDetalles);
        }
        return null;
    }

    public boolean eliminarAlumno(Long id) {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void calcularEstado(Alumno alumno) {
        if (alumno.getPromedio() >= 13) {
            alumno.setEstadoAcademico("REGULAR");
        } else {
            alumno.setEstadoAcademico("EN RIESGO");
        }
    }
}