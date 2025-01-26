package com.example.consultorio.repository;

import com.example.consultorio.model.Livro;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class LivroRepository{
    private List<Livro> livros = new ArrayList<Livro>();
    private Integer ultimoId = 0;

    public Optional<Livro> obterPorId(Integer id){
        return livros
                .stream()
                .filter(livro -> livro.getId() == id)
                .findFirst();
    }

    public List<Livro> obterTodos(){
        return livros;
    }

    public Livro adicionar(Livro livro){
        
        ultimoId++;

        livro.setId(ultimoId);
        livros.add(livro);
        return livro;
    }

    public Optional<Livro> deletar(Integer id){
        Optional<Livro> livroEncontrado = obterPorId(id);

        if(livroEncontrado.isEmpty()) {
            throw new InputMismatchException("Livro não encontrado");
        }

        livroEncontrado.ifPresent(livro -> {
            if (livro.getQuant() > 0) {
                livro.setQuant(livro.getQuant() - 1);
            } else {
                throw new InputMismatchException("Não há mais exemplares deste livro para remover");
            }
        });
        return livroEncontrado;
    }

    public Livro atualizar(Livro livro){
        Optional<Livro> livroEncontrado = obterPorId(livro.getId());

        if(livroEncontrado.isEmpty()) {
            throw new InputMismatchException("Usuario não encontrado");
        }

        deletar(livro.getId());
        livros.add(livro);
        return livro;
    }
}