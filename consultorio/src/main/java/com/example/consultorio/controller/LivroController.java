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

import com.example.consultorio.model.Livro;
import com.example.consultorio.service.LivroService;
import com.example.consultorio.view.model.LivroRequest;
import com.example.consultorio.view.model.LivroResponse;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    
    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> obterTodos(){
        List<Livro> livros = livroService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<LivroResponse> resposta = livros.stream()
        .map(livro -> mapper.map(livro, LivroResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LivroResponse> adicionar(@RequestBody LivroRequest livroReq){
        ModelMapper mapper = new ModelMapper();

        Livro livro = mapper.map(livroReq, Livro.class);

        livro = livroService.adicionar(livro);

        return new ResponseEntity<>(mapper.map(livro, LivroResponse.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LivroResponse>> obterPorId(@PathVariable Integer id){

        Optional<Livro> livro =  livroService.obterPorId(id);

        LivroResponse produto = new ModelMapper().map(livro.get(), LivroResponse.class);

        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){

        livroService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizar(@RequestBody LivroRequest livroReq, @PathVariable Integer id){

        ModelMapper mapper = new ModelMapper();
        Livro livro = mapper.map(livroReq, Livro.class);

        livro = livroService.atualizar(id, livro);

        return new ResponseEntity<>(
            mapper.map(livro, LivroResponse.class), 
            HttpStatus.OK);
    }
}
