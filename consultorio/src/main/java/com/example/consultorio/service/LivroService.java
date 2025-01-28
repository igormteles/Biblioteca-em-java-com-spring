package com.example.consultorio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consultorio.model.Livro;
import com.example.consultorio.repository.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;

    public Livro adicionar(Livro livro){
        
        return livroRepository.save(livro);
    }

    @Transactional
    public Livro atualizarQuant(Integer id, Integer novaQuant){
        Livro livroEncontrado = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado com ID " + id));

        livroEncontrado.setQuant(livroEncontrado.getQuant() + novaQuant);
        return livroRepository.save(livroEncontrado);
    }

    public Optional<Livro> obterPorId(Integer id){
        
        return livroRepository.findById(id);
    }

    public List<Livro> obterTodos(){

       return livroRepository.findAll();
    }

    public void deletarPorId(Integer id){
        livroRepository.deleteById(id);
    }
    
}