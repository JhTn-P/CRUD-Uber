package com.example.model;

public class Pessoas {

    private long cpf_pessoa;
    private String nome;
    private String endereco;
    private long telefone;
    private String sexo;
    private String email;

    //Método para exibir informações da pessoa;
    @Override
    public String toString() {
        return "Pessoa: " +
        "cpf " + cpf_pessoa +
        ", nome " + nome +
        ", endereco " + endereco + 
        ", telefone " + telefone +
        ", sexo " + sexo +
        ", email " + email;
    }

    public Pessoas() {
    }

    public Pessoas(long cpf_pessoa, String nome, String endereco, long telefone, String sexo, String email) {
        this.cpf_pessoa = cpf_pessoa;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.sexo = sexo;
        this.email = email;
    }

    public long getCpf_pessoa() {
        return cpf_pessoa;
    }

    public void setCpf_pessoa(long cpf_pessoa) {
        this.cpf_pessoa = cpf_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}