package com.example.model;

public class ViagemDetalhes {

    private String marca;
    private String placa;
    private String localOrigem;
    private String localDestino;
    private String nomeMotorista;
    private String nomePassageiro;
    
    public ViagemDetalhes() {
    }

    public ViagemDetalhes(String marca, String placa, String localOrigem, String localDestino, String nomeMotorista,
            String nomePassageiro, double faturamento) {
        this.marca = marca;
        this.placa = placa;
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.nomeMotorista = nomeMotorista;
        this.nomePassageiro = nomePassageiro;
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

    public String getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public void setNomePassageiro(String nomePassageiro) {
        this.nomePassageiro = nomePassageiro;
    }

    @Override
    public String toString() {
        return "Detalhes da viagem{" +
        "marca='" + marca + '\'' +
        ", placa='" + placa + '\'' +
        ", local de origem='" + localOrigem + '\'' +
        ", local de destino='" + localDestino + '\'' +
        ", nome do motorista='" + nomeMotorista + '\'' +
        ", nome do passageiro='" + nomePassageiro + '\'' +
        '}';
    }
}
