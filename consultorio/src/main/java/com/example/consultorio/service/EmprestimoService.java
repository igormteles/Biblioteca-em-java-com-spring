package com.example.consultorio.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consultorio.model.Emprestimo;
import com.example.consultorio.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Optional<Emprestimo> obterPorId(Integer id){
        
        Optional<Emprestimo> emprestimo = emprestimoRepository.obterPorId(id);
        if(emprestimo.isEmpty()){
            throw new Error("Emprestimo com id: " + id + " não encontrado");
        }
        Emprestimo emp = new ModelMapper().map(emprestimo.get(), Emprestimo.class);
        return Optional.of(emp);
    }

    public List<Emprestimo> obterTodos(){

       List<Emprestimo> emprestimos = emprestimoRepository.obterTodos();

       return emprestimos.stream()
       .map(emprestimo -> new ModelMapper().map(emprestimo, Emprestimo.class))
       .collect(Collectors.toList());
    }

    public Emprestimo adicionar(Emprestimo emprestimo){
        
        emprestimo.setEmp_id(null);

        emprestimo = emprestimoRepository.adicionar(emprestimo);
        emprestimo.setEmp_id(emprestimo.getEmp_id());
        return emprestimo;
    }

    public void deletar(Integer id){

        Optional<Emprestimo> emprestimo = emprestimoRepository.obterPorId(id);

        if(emprestimo.isEmpty()){
            throw new Error("Não foi possível deletar o produto com id:" + id + " - Produto não existe");
        }
        emprestimoRepository.deletar(id);
    }

    public Emprestimo atualizar(Integer id, Emprestimo emprestimo){
        Optional<Emprestimo> emprestimoEncontrado = obterPorId(emprestimo.getEmp_id());

        if(emprestimoEncontrado.isEmpty()) {
            throw new InputMismatchException("emprestimo não encontrado");
        }

        emprestimoRepository.adicionar(emprestimo);
        return emprestimo;
    }
}