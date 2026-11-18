package com.pruebas.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebas.model.Curso;
import com.pruebas.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> listar(){
        return repository.findAll();
    }

    public Curso buscar(Long id){
        return repository.findById(id).orElseThrow(() ->
              new RuntimeException("Curso no encontrado"));
    }

    public Curso registrar(Curso curso){
        if(curso.getPrecio() == null || curso.getPrecio() <= 0){
            throw new RuntimeException("Precio inválido");
        }
        return repository.save(curso);
    }

}
