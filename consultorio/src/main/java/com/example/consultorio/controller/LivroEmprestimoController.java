package com.example.consultorio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.consultorio.service.LivroemprestimoService;
import com.example.consultorio.view.model.Livro_emprestimoRequest;
import com.example.consultorio.view.model.Livro_emprestimoResponse;

@RestController
@RequestMapping("/api/livro_emprestimo")
public class LivroEmprestimoController {
    
    @Autowired
    private LivroemprestimoService livro_emprestimoService;

    @GetMapping
    public ResponseEntity<List<Livro_emprestimoResponse>> obterTodos(){
        List<Livro_emprestimo> livro_emprestimos = livro_emprestimoService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<Livro_emprestimoResponse> resposta = livro_emprestimos.stream()
        .map(livro_emprestimo -> mapper.map(livro_emprestimo, Livro_emprestimoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Livro_emprestimoResponse> adicionar(@RequestBody Livro_emprestimoRequest livro_emprestimoReq){
        ModelMapper mapper = new ModelMapper();

        Livro_emprestimo livro_emprestimo = mapper.map(livro_emprestimoReq, Livro_emprestimo.class);

        livro_emprestimo = livro_emprestimoService.adicionar(livro_emprestimo);

        return new ResponseEntity<>(mapper.map(livro_emprestimo, Livro_emprestimoResponse.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Livro_emprestimoResponse>> obterPorId(@PathVariable Integer id){

        Optional<Livro_emprestimo> livro_emprestimo =  livro_emprestimoService.obterPorId(id);

        Livro_emprestimoResponse produto = new ModelMapper().map(livro_emprestimo.get(), Livro_emprestimoResponse.class);

        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){

        livro_emprestimoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro_emprestimoResponse> atualizar(@RequestBody Livro_emprestimoRequest livro_emprestimoReq, @PathVariable Integer id){

        ModelMapper mapper = new ModelMapper();
        Livro_emprestimo livro_emprestimo = mapper.map(livro_emprestimoReq, Livro_emprestimo.class);

        livro_emprestimo = livro_emprestimoService.atualizar(id, livro_emprestimo);

        return new ResponseEntity<>(
            mapper.map(livro_emprestimo, Livro_emprestimoResponse.class), 
            HttpStatus.OK);
    }
}
