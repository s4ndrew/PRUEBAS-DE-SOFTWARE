package com.pruebas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebas.model.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long>{

    boolean existsByCodigoEstudianteAndCursoId(String codigoEstudiante, Long cursoId);

    List<Inscripcion> findByCodigoEstudiante(String codigoEstudiante);

}
