package com.example.consultorio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consultorio.model.Emprestimo;
import com.example.consultorio.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo adicionar(Emprestimo emprestimo){
        
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo atualizar(Integer id){
        Emprestimo emprestimoEncontrado = emprestimoRepository.findById(id).orElseThrow(() -> new RuntimeException("emprestimo não encontrado com ID " + id));

        return emprestimoRepository.save(emprestimoEncontrado);
    }

    public Optional<Emprestimo> obterPorId(Integer id){
        
        return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> obterTodos(){

       return emprestimoRepository.findAll();
    }

    public void deletarPorId(Integer id){
        emprestimoRepository.deleteById(id);
    }
}