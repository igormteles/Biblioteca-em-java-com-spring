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

import com.example.consultorio.model.Emprestimo;
import com.example.consultorio.service.EmprestimoService;
import com.example.consultorio.view.model.EmprestimoRequest;
import com.example.consultorio.view.model.EmprestimoResponse;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<EmprestimoResponse>> obterTodos(){
        List<Emprestimo> emprestimos = emprestimoService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<EmprestimoResponse> resposta = emprestimos.stream()
        .map(emprestimo -> mapper.map(emprestimo, EmprestimoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponse> adicionar(@RequestBody EmprestimoRequest empReq){
        ModelMapper mapper = new ModelMapper();

        Emprestimo emprestimo = mapper.map(empReq, Emprestimo.class);

        emprestimo = emprestimoService.adicionar(emprestimo);

        return new ResponseEntity<>(mapper.map(emprestimo, EmprestimoResponse.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EmprestimoResponse>> obterPorId(@PathVariable Integer id){

        Optional<Emprestimo> emprestimo =  emprestimoService.obterPorId(id);

        EmprestimoResponse produto = new ModelMapper().map(emprestimo.get(), EmprestimoResponse.class);

        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){

        emprestimoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponse> atualizar(@RequestBody EmprestimoRequest emprestimoReq, @PathVariable Integer id){

        ModelMapper mapper = new ModelMapper();
        Emprestimo emprestimo = mapper.map(emprestimoReq, Emprestimo.class);

        emprestimo = emprestimoService.atualizar(id, emprestimo);

        return new ResponseEntity<>(
            mapper.map(emprestimo, EmprestimoResponse.class), 
            HttpStatus.OK);
    }
}
