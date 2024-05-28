package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.DAO.ViagemDAO;
import com.example.model.ViagemDetalhes;
import com.example.util.ResultadoBuscaViagens;

public class ViagemDetalhesService {
    private ViagemDAO viagemDAO;

    public ViagemDetalhesService(ViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    public ResultadoBuscaViagens buscarViagensPorMarcaEData(String marca, LocalDate data) {
        List<ViagemDetalhes> viagens = viagemDAO.buscarViagensPorMarcaEData(marca, data);
        return new ResultadoBuscaViagens(viagens);
    }

    public void buscarViagensPorMarcaEData(Scanner scanner) {
        System.out.println("Digite a marca do veículo:");
        String marca = scanner.nextLine();
        System.out.println("Digite a data da viagem (formato: YYYY-MM-DD):");
        String dataString = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataString);

        ResultadoBuscaViagens resultado = buscarViagensPorMarcaEData(marca, data);

        if (resultado.isEmpty()) {
            System.out.println("Não foram encontradas viagens para a marca e data especificadas.");
        } else {
            System.out.println("Viagens encontradas:");
            for (ViagemDetalhes viagem : resultado.getViagens()) {
                System.out.println(viagem);
            }
        }
    }
}