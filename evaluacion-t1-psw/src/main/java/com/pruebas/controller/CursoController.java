package com.pruebas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebas.model.Curso;
import com.pruebas.model.services.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public List<Curso> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id){
        return service.buscar(id);
    }

    @PostMapping
    public Curso registrar(@RequestBody Curso curso){
        return service.registrar(curso);
    }
}
