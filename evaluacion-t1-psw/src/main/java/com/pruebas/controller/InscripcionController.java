package com.pruebas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebas.model.Inscripcion;
import com.pruebas.model.services.InscripcionService;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService service;

    @PostMapping
    public Inscripcion inscribir(@RequestBody Inscripcion inscripcion){
        return service.inscribir(inscripcion);
    }
}
