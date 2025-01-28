package com.example.consultorio.repository;

import com.example.consultorio.model.Livro_emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Livro_emprestimoRepository extends JpaRepository<Livro_emprestimo, Integer> {
    
}
