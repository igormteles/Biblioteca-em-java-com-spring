package com.example.consultorio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro_emprestimo")
public class Livro_emprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private Integer livro_id;
    private Integer emp_id;

    public Livro_emprestimo() {
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getLivro_id() {
        return livro_id;
    }


    public void setLivro_id(Integer livro_id) {
        this.livro_id = livro_id;
    }


    public Integer getEmp_id() {
        return emp_id;
    }


    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }
}
