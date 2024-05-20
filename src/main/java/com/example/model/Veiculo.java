package com.example.model;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int ano_fabric;
    private int capacidade_pass;
    private String cor;
    private String tipo_combust;
    private int potencia_motor;

    public Veiculo(String placa, String marca, String modelo, int ano_fabric, int capacidade_pass, String cor,
                   String tipo_combust, int potencia_motor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano_fabric = ano_fabric;
        this.capacidade_pass = capacidade_pass;
        this.cor = cor;
        this.tipo_combust = tipo_combust;
        this.potencia_motor = potencia_motor;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano_fabric=" + ano_fabric +
                ", capacidade_pass=" + capacidade_pass +
                ", cor='" + cor + '\'' +
                ", tipo_combust='" + tipo_combust + '\'' +
                ", potencia_motor=" + potencia_motor +
                '}';
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno_fabric() {
        return ano_fabric;
    }

    public void setAno_fabric(int ano_fabric) {
        this.ano_fabric = ano_fabric;
    }

    public int getCapacidade_pass() {
        return capacidade_pass;
    }

    public void setCapacidade_pass(int capacidade_pass) {
        this.capacidade_pass = capacidade_pass;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo_combust() {
        return tipo_combust;
    }

    public void setTipo_combustivel(String tipo_combust) {
        this.tipo_combust = tipo_combust;
    }

    public int getPotencia_motor() {
        return potencia_motor;
    }

    public void setPotencia_motor(int potencia_motor) {
        this.potencia_motor = potencia_motor;
    }
}
