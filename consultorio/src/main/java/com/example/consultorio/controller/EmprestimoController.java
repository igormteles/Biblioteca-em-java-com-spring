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

import com.example.consultorio.model.Emprestimo;
import com.example.consultorio.service.EmprestimoService;
@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<Emprestimo>> obterTodos(){
        List<Emprestimo> emprestimos = emprestimoService.obterTodos();
        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping
    public ResponseEntity<Emprestimo> adicionar(@RequestBody Emprestimo emprestimo){
        Emprestimo emprestimoSalvo = emprestimoService.adicionar(emprestimo);
        return ResponseEntity.ok(emprestimoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> obterPorId(@PathVariable Integer id){

        Optional<Emprestimo> emprestimo =  emprestimoService.obterPorId(id);
        return emprestimo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Integer id){
        Optional<Emprestimo> emprestimo = emprestimoService.obterPorId(id);
        if (emprestimo.isPresent()) {
            emprestimoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizar(@RequestBody Emprestimo emprestimo, @PathVariable Integer id){
        emprestimo = emprestimoService.atualizar(id);
        return ResponseEntity.ok(emprestimo);
    }
}
