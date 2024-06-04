package com.example.util;

import java.util.List;

import com.example.model.FaturamentoDetalhado;

public class ResultadoFaturamentoDetalhado {
    private List<FaturamentoDetalhado> faturamentoDetalhado;

    public ResultadoFaturamentoDetalhado(List<FaturamentoDetalhado> faturamentoDetalhado) {
        this.faturamentoDetalhado = faturamentoDetalhado;
    }

    public List<FaturamentoDetalhado> getFaturamentoDetalhado() {
        return faturamentoDetalhado;
    }

    public boolean isEmpty() {
        return faturamentoDetalhado.isEmpty();
    }

    @Override
    public String toString() {
        return "Faturamento Detalhado: " + faturamentoDetalhado;
    }
}