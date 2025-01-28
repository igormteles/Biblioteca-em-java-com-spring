package com.example.consultorio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consultorio.model.Livro_emprestimo;
import com.example.consultorio.repository.Livro_emprestimoRepository;

import jakarta.transaction.Transactional;

@Service
public class Livro_emprestimoService {

    @Autowired
    private Livro_emprestimoRepository livro_emprestimoRepository;

    public Livro_emprestimo adicionar(Livro_emprestimo livro_emprestimo){
        
        return livro_emprestimoRepository.save(livro_emprestimo);
    }

    @Transactional
    public Livro_emprestimo atualizar(Integer id){
        Livro_emprestimo livro_emprestimoEncontrado = livro_emprestimoRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro_emprestimo não encontrado com ID " + id));

        return livro_emprestimoRepository.save(livro_emprestimoEncontrado);
    }

    public Optional<Livro_emprestimo> obterPorId(Integer id){
        
        return livro_emprestimoRepository.findById(id);
    }

    public List<Livro_emprestimo> obterTodos(){

       return livro_emprestimoRepository.findAll();
    }

    public void deletarPorId(Integer id){
        livro_emprestimoRepository.deleteById(id);
    }
}
