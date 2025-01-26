package com.example.consultorio.repository;

import com.example.consultorio.model.Livro_emprestimo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class Livro_emprestimoRepository {
    private List<Livro_emprestimo> livro_emprestimos = new ArrayList<Livro_emprestimo>();
    private Integer ultimoId = 0;

    public Optional<Livro_emprestimo> obterPorId(Integer id){
                return livro_emprestimos
                        .stream()
                        .filter(livro_emprestimo -> livro_emprestimo.getId() == id)
                        .findFirst();
            }

    public List<Livro_emprestimo> obterTodos(){
        return livro_emprestimos;
    }

    public Livro_emprestimo adicionar(Livro_emprestimo livro_emprestimo){
            ultimoId++;
    
            livro_emprestimo.setId(ultimoId);
            livro_emprestimos.add(livro_emprestimo);
            return livro_emprestimo;
        }

    public Optional<Livro_emprestimo> deletar(Integer id){
        Optional<Livro_emprestimo> livroEncontrado = obterPorId(id);

        if(livroEncontrado.isEmpty()) {
            throw new InputMismatchException("Livro não encontrado");
        }

        return livroEncontrado;
    }

    public Livro_emprestimo atualizar(Livro_emprestimo livro_emprestimo){
        Optional<Livro_emprestimo> livro_emprestimoEncontrado = obterPorId(livro_emprestimo.getId());

        if(livro_emprestimoEncontrado.isEmpty()) {
            throw new Error("Usuario não encontrado");
        }

        deletar(livro_emprestimo.getId());
        livro_emprestimos.add(livro_emprestimo);
        return livro_emprestimo;
    }
}
