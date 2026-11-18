package com.pruebas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pruebas.model.Inscripcion;
import com.pruebas.model.services.InscripcionService;
import com.pruebas.repository.InscripcionRepository;

/**
 * Estudiante: Segundo Andres Chiroque Mezones
 */
@ExtendWith(MockitoExtension.class)
class InscripcionServiceTest {

    @Mock
    private InscripcionRepository repository;

    @InjectMocks
    private InscripcionService service;

    @Test
    @DisplayName("Caso 1: Inscribir a un estudiante correctamente (código I2024050 en el curso 2)")
    void testInscribirEstudianteCorrectamente() {
        // Arrange
        Inscripcion entrada = new Inscripcion();
        entrada.setCodigoEstudiante("I2024050");
        entrada.setCursoId(2L);
        Inscripcion guardada = new Inscripcion();
        guardada.setId(2L);
        guardada.setCodigoEstudiante("I2024050");
        guardada.setCursoId(2L);

        when(repository.existsByCodigoEstudianteAndCursoId("I2024050", 2L)).thenReturn(false);
        when(repository.save(any(Inscripcion.class))).thenReturn(guardada);

        // Act
        Inscripcion resultado = service.inscribir(entrada);

        // Assert
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals("I2024050", resultado.getCodigoEstudiante());
        assertEquals(2L, resultado.getCursoId());
        verify(repository, times(1)).save(any(Inscripcion.class));
    }

    @Test
    @DisplayName("Caso 2: Intentar inscribir a un estudiante que ya está inscrito en el curso")
    void testInscribirEstudianteDuplicado() {
        // Arrange
        Inscripcion duplicada = new Inscripcion();
        duplicada.setCodigoEstudiante("I2024001");
        duplicada.setCursoId(1L);

        when(repository.existsByCodigoEstudianteAndCursoId("I2024001", 1L)).thenReturn(true);

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.inscribir(duplicada);
        });

        assertEquals("Estudiante ya inscrito", ex.getMessage());
        verify(repository, never()).save(any(Inscripcion.class));
    }

    @Test
    @DisplayName("Caso 3: Listar las inscripciones de un estudiante")
    void testListarInscripcionesPorEstudiante() {
        // Arrange
        String codigo = "I2024001";

        Inscripcion ins1 = new Inscripcion();
        ins1.setId(1L);
        ins1.setCodigoEstudiante(codigo);
        ins1.setCursoId(1L);

        Inscripcion ins2 = new Inscripcion();
        ins2.setId(2L);
        ins2.setCodigoEstudiante(codigo);
        ins2.setCursoId(2L);

        when(repository.findByCodigoEstudiante(codigo)).thenReturn(Arrays.asList(ins1, ins2));

        // Act
        List<Inscripcion> lista = service.listarPorEstudiante(codigo);

        // Assert
        assertEquals(2, lista.size(), "La lista debe tener 2 elementos");
        assertEquals(codigo, lista.get(0).getCodigoEstudiante());
        assertEquals(codigo, lista.get(1).getCodigoEstudiante());
    }
}
