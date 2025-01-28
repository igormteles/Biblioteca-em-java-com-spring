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

import com.example.consultorio.model.Livro_emprestimo;
import com.example.consultorio.service.Livro_emprestimoService;

@RestController
@RequestMapping("/api/livro_emprestimo")
public class Livro_EmprestimoController {
    
    @Autowired
    private Livro_emprestimoService livro_emprestimoService;

    @GetMapping
    public ResponseEntity<List<Livro_emprestimo>> obterTodos(){
        List<Livro_emprestimo> livro_emprestimos = livro_emprestimoService.obterTodos();
        return ResponseEntity.ok(livro_emprestimos);
    }

    @PostMapping
    public ResponseEntity<Livro_emprestimo> adicionar(@RequestBody Livro_emprestimo livro_emprestimo){
        Livro_emprestimo livro_emprestimoSalvo = livro_emprestimoService.adicionar(livro_emprestimo);
        return ResponseEntity.ok(livro_emprestimoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro_emprestimo> obterPorId(@PathVariable Integer id){

        Optional<Livro_emprestimo> livro_emprestimo =  livro_emprestimoService.obterPorId(id);
        return livro_emprestimo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id){
        Optional<Livro_emprestimo> livro_emprestimo = livro_emprestimoService.obterPorId(id);
        if (livro_emprestimo.isPresent()) {
            livro_emprestimoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro_emprestimo> atualizarQuant(@RequestBody Livro_emprestimo livro_emprestimo, @PathVariable Integer id){
        livro_emprestimo = livro_emprestimoService.atualizar(id);

        return ResponseEntity.ok(livro_emprestimo);
    }
}
