package com.example.model;

public class FaturamentoDetalhado {
    private String nomeProprietario;
    private String placaVeiculo;
    private String tipoPagamento;
    private double valorTotalFaturado;
    private double valorMedioFaturamento;
    
    public FaturamentoDetalhado() {
    }

    public FaturamentoDetalhado(String nomeProprietario, String placaVeiculo, String tipoPagamento,
            double valorTotalFaturado, double valorMedioFaturamento) {
        this.nomeProprietario = nomeProprietario;
        this.placaVeiculo = placaVeiculo;
        this.tipoPagamento = tipoPagamento;
        this.valorTotalFaturado = valorTotalFaturado;
        this.valorMedioFaturamento = valorMedioFaturamento;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValorTotalFaturado() {
        return valorTotalFaturado;
    }

    public void setValorTotalFaturado(double valorTotalFaturado) {
        this.valorTotalFaturado = valorTotalFaturado;
    }

    public double getValorMedioFaturamento() {
        return valorMedioFaturamento;
    }

    public void setValorMedioFaturamento(double valorMedioFaturamento) {
        this.valorMedioFaturamento = valorMedioFaturamento;
    }

    @Override
    public String toString() {
        return "Faturamento Detalhado: " +
                "nome do proprietario " + nomeProprietario  +
                ", placa do veículo " + placaVeiculo  +
                ", tipo de pagamento " + tipoPagamento  +
                ", valor total faturado " + valorTotalFaturado +
                ", valor medio de faturamento " + valorMedioFaturamento;
    }

}
