package com.example.model;

public class Faturamento {

    private String marca;
    private String placa;
    private double faturamento;
    
    public Faturamento() {
    }

    public Faturamento(String marca, String placa, double faturamento) {
        this.marca = marca;
        this.placa = placa;
        this.faturamento = faturamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }

    @Override
    public String toString() {
        return "Maiores Faturamentos por veículo: " + 
        "Marca: " + marca + 
        ", placa: " + placa +
        ", faturamento: " + faturamento;
    }
}