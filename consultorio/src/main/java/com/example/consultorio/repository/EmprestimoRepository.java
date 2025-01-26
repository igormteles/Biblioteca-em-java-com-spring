package com.example.consultorio.repository;

import com.example.consultorio.model.Emprestimo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class EmprestimoRepository {

    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    private Integer ultimoId = 0;

    public Optional<Emprestimo> obterPorId(Integer id){
        return emprestimos
                .stream()
                .filter(emprestimo -> emprestimo.getEmp_id() == id)
                .findFirst();
    }

    public List<Emprestimo> obterTodos(){
        return emprestimos;
    }

    public Emprestimo adicionar(Emprestimo emprestimo){
        
        ultimoId++;

        emprestimo.setEmp_id(ultimoId);
        emprestimos.add(emprestimo);

        return emprestimo;
    }

    public void deletar(Integer id){
        emprestimos.removeIf(emprestimo -> emprestimo.getEmp_id() == id);
    }

    public Emprestimo atualizar(Emprestimo emprestimo){
        Optional<Emprestimo> emprestimoEncontrado = obterPorId(emprestimo.getEmp_id());

        if(emprestimoEncontrado.isEmpty()) {
            throw new InputMismatchException("Usuario não encontrado");
        }

        deletar(emprestimo.getEmp_id());
        emprestimos.add(emprestimo);
        return emprestimo;
    }
}
