package com.example.util;

import java.util.List;

import com.example.model.Faturamento;

public class ResultadoFaturamentoViagens {
    private List<Faturamento> faturamento;

    public ResultadoFaturamentoViagens(List<Faturamento> faturamento) {
        this.faturamento = faturamento;
    }

    public List<Faturamento> getFaturamento() {
        return faturamento;
    }

    public boolean isEmpty(){
        return faturamento.isEmpty();
    }

    @Override
    public String toString() {
        return "Faturamento do mês: " + faturamento;
    }
}
