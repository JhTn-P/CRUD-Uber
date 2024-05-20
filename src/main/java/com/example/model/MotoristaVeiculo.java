package com.example.model;

public class MotoristaVeiculo {
    private long cpf_motorista;
    private String placa_veiculo;
    
    public MotoristaVeiculo() {
    }

    public MotoristaVeiculo(long cpf_motorista, String placa_veiculo) {
        this.cpf_motorista = cpf_motorista;
        this.placa_veiculo = placa_veiculo;
    }

    @Override
    public String toString() {
        return "Motorista do veículo: " + 
        "CPF: " + cpf_motorista + 
        ", Placa: " + placa_veiculo;
    }

    public long getCpf_motorista() {
        return cpf_motorista;
    }

    public void setCpf_motorista(long cpf_motorista) {
        this.cpf_motorista = cpf_motorista;
    }

    public String getPlaca_veiculo() {
        return placa_veiculo;
    }

    public void setPlaca_veiculo(String placa_veiculo) {
        this.placa_veiculo = placa_veiculo;
    }

    
}
