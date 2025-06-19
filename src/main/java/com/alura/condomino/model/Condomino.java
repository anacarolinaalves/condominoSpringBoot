package com.alura.condomino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Condomino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String dddCelular;

    @NotBlank
    private String numeroCelular;

    @NotBlank
    private String email;

    @NotBlank
    private String bloco;

    @NotBlank
    private String apto;

    @NotBlank
    private String cpf;

    public Condomino() {
    }

    public Condomino(final Long id, final String nome, final String dddCelular, final String numeroCelular, final String email, final String bloco, final String apto, final String cpf) {
        this.id = id;
        this.nome = nome;
        this.dddCelular = dddCelular;
        this.numeroCelular = numeroCelular;
        this.email = email;
        this.bloco = bloco;
        this.apto = apto;
        this.cpf = cpf;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDddCelular() {
        return dddCelular;
    }

    public void setDddCelular(String dddCelular) {
        this.dddCelular = dddCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getApto() {
        return apto;
    }

    public void setApto(String apto) {
        this.apto = apto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}