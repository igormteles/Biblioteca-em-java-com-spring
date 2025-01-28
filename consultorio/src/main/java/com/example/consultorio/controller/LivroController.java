package com.example.consultorio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consultorio.model.Livro;
import com.example.consultorio.service.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    
    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> obterTodos(){
        List<Livro> livros = livroService.obterTodos();
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<Livro> adicionar(@RequestBody Livro livro){
        Livro livroSalvo = livroService.adicionar(livro);
        return ResponseEntity.ok(livroSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> obterPorId(@PathVariable Integer id){

        Optional<Livro> livro =  livroService.obterPorId(id);
        return livro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id){
        Optional<Livro> livro = livroService.obterPorId(id);
        if (livro.isPresent()) {
            livroService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarQuant(@RequestBody Livro livroAtt, @PathVariable Integer id){

        Livro livro = livroService.atualizarQuant(id, livroAtt.getQuant());

        return ResponseEntity.ok(livro);
    }
}
