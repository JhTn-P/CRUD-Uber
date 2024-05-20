package com.example.model;

public class Proprietario {
    private long cpf_prop;
    private String cnh_prop;
    private int banco_prop;
    private int agencia_prop;
    private int conta_prop;


    @Override
    public String toString() {
        return "Proprietario{" +
                "cpf_prop=" + cpf_prop +
                ", cnh_prop='" + cnh_prop + '\'' +
                ", banco_prop=" + banco_prop +
                ", agencia_prop=" + agencia_prop +
                ", conta_prop=" + conta_prop +
                '}';
    }

    public Proprietario(long cpf_prop, String cnh_prop, int banco_prop, int agencia_prop, int conta_prop) {
        this.cpf_prop = cpf_prop;
        this.cnh_prop = cnh_prop;
        this.banco_prop = banco_prop;
        this.agencia_prop = agencia_prop;
        this.conta_prop = conta_prop;
    }

    public long getCpf_prop() {
        return cpf_prop;
    }

    public void setCpf_prop(long cpf_prop) {
        this.cpf_prop = cpf_prop;
    }

    public String getCnh_prop() {
        return cnh_prop;
    }

    public void setCnh_prop(String cnh_prop) {
        this.cnh_prop = cnh_prop;
    }

    public int getBanco_prop() {
        return banco_prop;
    }

    public void setBanco_prop(int banco_prop) {
        this.banco_prop = banco_prop;
    }

    public int getAgencia_prop() {
        return agencia_prop;
    }

    public void setAgencia_prop(int agencia_prop) {
        this.agencia_prop = agencia_prop;
    }

    public int getConta_prop() {
        return conta_prop;
    }

    public void setConta_prop(int conta_prop) {
        this.conta_prop = conta_prop;
    }
}
