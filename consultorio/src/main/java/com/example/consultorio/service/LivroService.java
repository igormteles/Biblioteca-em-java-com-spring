package com.example.consultorio.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consultorio.model.Livro;
import com.example.consultorio.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;

    public Optional<Livro> obterPorId(Integer id){
        
        Optional<Livro> livro = livroRepository.obterPorId(id);
        if(livro.isEmpty()){
            throw new Error("Livro com id: " + id + " não encontrado");
        }
        Livro liv = new ModelMapper().map(livro.get(), Livro.class);
        return Optional.of(liv);
    }

    public List<Livro> obterTodos(){

       List<Livro> livros = livroRepository.obterTodos();

       return livros.stream()
       .map(livro -> new ModelMapper().map(livro, Livro.class))
       .collect(Collectors.toList());
    }

    public Livro adicionar(Livro livro){
        
        livro.setId(null);

        livro = livroRepository.adicionar(livro);
        livro.setId(livro.getId());
        return livro;
    }

    public Optional<Livro> deletar(Integer id){

        Optional<Livro> livro = livroRepository.obterPorId(id);
        if(livro.isEmpty()){
            throw new Error("Não foi possível deletar o livro com id:" + id + " - Livro não existe");
        }

        livroRepository.deletar(id);
        return livroRepository.obterPorId(id);
    }
    public Livro atualizar(Integer id, Livro livro){
        Optional<Livro> livroEncontrado = obterPorId(livro.getId());

        if(livroEncontrado.isEmpty()) {
            throw new InputMismatchException("Livro não encontrado");
        }

        livroRepository.adicionar(livro);
        return livro;
    }
}