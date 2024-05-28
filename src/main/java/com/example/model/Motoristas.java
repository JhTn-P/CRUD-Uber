package com.example.model;

public class Motoristas {

    private long cpf_motorista;
    private String cnh;
    private int banco_mot;
    private int agencia_mot;
    private int conta_mot;
   
    public Motoristas() {
    }

    public Motoristas(long cpf_motorista, String cnh, int banco_mot, int agencia_mot, int conta_mot) {
        this.cpf_motorista = cpf_motorista;
        this.cnh = cnh;
        this.banco_mot = banco_mot;
        this.agencia_mot = agencia_mot;
        this.conta_mot = conta_mot;
    }

    /* Método sobrescrito para retornar uma representação em string do objeto Motoristas,
    quando os dados da tabela motoristas for listado será exibido dessa maneira
    */ 
    @Override
    public String toString() {
        return"Motorista: " + 
        "cpf: " + cpf_motorista +
        ", cnh: " + cnh +
        ", banco: " + banco_mot +
        ", agencia: " + agencia_mot +
        ", conta: " + conta_mot;
    }

    //Getters e setters para acessar e modificar os atributos da tabela
    public long getCpf_motorista() {
        return cpf_motorista;
    }

    public void setCpf_motorista(long cpf_motorista) {
        this.cpf_motorista = cpf_motorista;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public int getBanco_mot() {
        return banco_mot;
    }

    public void setBanco_mot(int banco_mot) {
        this.banco_mot = banco_mot;
    }

    public int getAgencia_mot() {
        return agencia_mot;
    }

    public void setAgencia_mot(int agencia_mot) {
        this.agencia_mot = agencia_mot;
    }

    public int getConta_mot() {
        return conta_mot;
    }

    public void setConta_mot(int conta_mot) {
        this.conta_mot = conta_mot;
    }
}
