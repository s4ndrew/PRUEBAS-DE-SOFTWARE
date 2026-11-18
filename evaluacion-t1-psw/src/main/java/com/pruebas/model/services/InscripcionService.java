package com.pruebas.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebas.model.Inscripcion;
import com.pruebas.repository.InscripcionRepository;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository repository;

    public Inscripcion inscribir(Inscripcion inscripcion){
        boolean existe = repository.existsByCodigoEstudianteAndCursoId(
                inscripcion.getCodigoEstudiante(), inscripcion.getCursoId());
        if(existe){
            throw new RuntimeException("Estudiante ya inscrito");
        }
        return repository.save(inscripcion);
    }

    public void cancelar(Long id){
        Inscripcion inscripcion = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Inscripción no encontrada"));
        repository.delete(inscripcion);
    }

    public List<Inscripcion> listarPorEstudiante(String codigoEstudiante){
        return repository.findByCodigoEstudiante(codigoEstudiante);
    }

}
