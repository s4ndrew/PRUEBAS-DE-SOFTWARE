package com.pruebas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pruebas.model.Curso;
import com.pruebas.model.Inscripcion;
import com.pruebas.repository.CursoRepository;
import com.pruebas.repository.InscripcionRepository;

/**
 * Carga datos de prueba al iniciar la aplicación (solo si las tablas están vacías).
 * NO modificar: los enunciados de la evaluación dependen de estos datos.
 *
 * Cursos (ids 1, 2 y 3 en una base H2 recién creada):
 *   1 - Pruebas de Software       - S/ 450.0 - 30 vacantes
 *   2 - Seguridad de Aplicaciones - S/ 380.0 - 25 vacantes
 *   3 - Base de Datos II          - S/ 420.0 - 20 vacantes
 *
 * Inscripciones:
 *   estudiante I2024001 inscrito en el curso 1
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public void run(String... args) {
        if (cursoRepository.count() == 0) {
            cursoRepository.save(curso("Pruebas de Software", 450.0, 30));
            cursoRepository.save(curso("Seguridad de Aplicaciones", 380.0, 25));
            cursoRepository.save(curso("Base de Datos II", 420.0, 20));
        }
        if (inscripcionRepository.count() == 0) {
            Inscripcion i = new Inscripcion();
            i.setCodigoEstudiante("I2024001");
            i.setCursoId(1L);
            inscripcionRepository.save(i);
        }
    }

    private Curso curso(String nombre, Double precio, Integer vacantes) {
        Curso c = new Curso();
        c.setNombre(nombre);
        c.setPrecio(precio);
        c.setVacantes(vacantes);
        return c;
    }
}
