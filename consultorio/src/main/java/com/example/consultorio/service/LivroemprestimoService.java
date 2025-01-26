package com.example.consultorio.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consultorio.model.Livro_emprestimo;
import com.example.consultorio.repository.Livro_emprestimoRepository;

@Service
public class LivroemprestimoService {

    @Autowired
    private Livro_emprestimoRepository livro_emprestimoRepository;
    public Optional<Livro_emprestimo> obterPorId(Integer id){
        
        Optional<Livro_emprestimo> livro_emprestimo = livro_emprestimoRepository.obterPorId(id);
        if(livro_emprestimo.isEmpty()){
            throw new Error("Livro_emprestimo com id: " + id + " não encontrado");
        }
        Livro_emprestimo liv = new ModelMapper().map(livro_emprestimo.get(), Livro_emprestimo.class);
        return Optional.of(liv);
        
    }

    public List<Livro_emprestimo> obterTodos(){

       List<Livro_emprestimo> livro_emprestimos = livro_emprestimoRepository.obterTodos();

       return livro_emprestimos.stream()
       .map(livro_emprestimo -> new ModelMapper().map(livro_emprestimo, Livro_emprestimo.class))
       .collect(Collectors.toList());
    }

    public Livro_emprestimo adicionar(Livro_emprestimo livro_emprestimo){
        
        livro_emprestimo.setId(null);
        livro_emprestimo = livro_emprestimoRepository.adicionar(livro_emprestimo);
        livro_emprestimo.setId(livro_emprestimo.getId());
        return livro_emprestimo;
    }

    public Optional<Livro_emprestimo> deletar(Integer id){

        Optional<Livro_emprestimo> livro_emprestimo = livro_emprestimoRepository.obterPorId(id);
        if(livro_emprestimo.isEmpty()){
            throw new Error("Não foi possível deletar o livro_emprestimo com id:" + id + " - Livro_emprestimo não existe");
        }

        livro_emprestimoRepository.deletar(id);
        return livro_emprestimoRepository.obterPorId(id);
    }

    public Livro_emprestimo atualizar(Integer id, Livro_emprestimo livro_emprestimo){
        Optional<Livro_emprestimo> livro_emprestimoEncontrado = obterPorId(livro_emprestimo.getId());

        if(livro_emprestimoEncontrado.isEmpty()) {
            throw new InputMismatchException("Livro_emprestimo não encontrado");
        }

        livro_emprestimoRepository.adicionar(livro_emprestimo);
        return livro_emprestimo;
    }
}
