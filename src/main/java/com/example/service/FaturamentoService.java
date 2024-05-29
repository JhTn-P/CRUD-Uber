package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.DAO.ViagemDAO;
import com.example.util.ResultadoFaturamentoViagens;
import com.example.model.Faturamento;

public class FaturamentoService {
    private ViagemDAO viagemDAO;

    public FaturamentoService(ViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    public ResultadoFaturamentoViagens buscarMaioresFaturamentosPorMes(int ano, int mes) {
        List<Faturamento> faturamento = viagemDAO.buscarMaioresFaturamentosPorMes(ano, mes);
        return new ResultadoFaturamentoViagens(faturamento);
    }

    public void buscarMaioresFaturamentosPorMes(Scanner scanner) {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Mês: ");
        int mes = scanner.nextInt();
        scanner.nextLine();

        ResultadoFaturamentoViagens resultado = buscarMaioresFaturamentosPorMes(ano, mes);

        if(resultado.isEmpty()) {
            System.out.println("Não foram encontrados corridas neste mês.");
        } else {
            System.out.println("Faturamentos: ");
            for(Faturamento faturamento : resultado.getFaturamento()){
                System.out.println(faturamento);
            }
        }
    }
}
