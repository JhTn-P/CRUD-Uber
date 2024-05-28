package com.example.util;

import java.util.List;

import com.example.model.ViagemDetalhes;

public class ResultadoBuscaViagens {
    private List<ViagemDetalhes> viagens;

    public ResultadoBuscaViagens(List<ViagemDetalhes> viagens) {
        this.viagens = viagens;
    }

    public List<ViagemDetalhes> getViagens() {
        return viagens;
    }

    public boolean isEmpty() {
        return viagens.isEmpty();
    }

    @Override
    public String toString() {
        return "ResultadoBuscaViagens{" +
                "viagens=" + viagens +
                '}';
    }
}
