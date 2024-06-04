package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.DAO.ViagemDAO;
import com.example.util.ResultadoFaturamentoDetalhado;
import com.example.util.ResultadoFaturamentoViagens;
import com.example.util.ResultadoMediaMensalViagens;
import com.example.model.Faturamento;
import com.example.model.FaturamentoDetalhado;
import com.example.model.MediaMensalViagens;

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

    public ResultadoFaturamentoDetalhado buscarFaturamentoDetalhadoPorMes(int ano, int mes) {
        List<FaturamentoDetalhado> faturamentoDetalhado = viagemDAO.buscarFaturamentoDetalhadoPorMes(ano, mes);
        return new ResultadoFaturamentoDetalhado(faturamentoDetalhado);
    }

    public void buscarFaturamentoDetalhadoPorMes(Scanner scanner) {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o mês: ");
        int mes = scanner.nextInt();
        scanner.nextLine();

        ResultadoFaturamentoDetalhado resultado = buscarFaturamentoDetalhadoPorMes(ano, mes);

        if (resultado.isEmpty()) {
            System.out.println("Não foram encontrados faturamentos detalhados para este mês.");
        } else {
            System.out.println("Faturamentos Detalhados:");
            for (FaturamentoDetalhado faturamento : resultado.getFaturamentoDetalhado()) {
                System.out.println(faturamento);
            }
        }
    }

    public ResultadoMediaMensalViagens buscarMediaMensalViagensPorSexo() {
        List<MediaMensalViagens> mediasMensais = viagemDAO.buscarMediaMensalViagensPorSexo();
        return new ResultadoMediaMensalViagens(mediasMensais);
    }

    public void buscarMediaMensalViagensPorSexo(Scanner scanner) {
        ResultadoMediaMensalViagens resultado = buscarMediaMensalViagensPorSexo();

        if (resultado.isEmpty()) {
            System.out.println("Não foram encontradas médias mensais de viagens para os sexos.");
        } else {
            System.out.println("Médias Mensais de Viagens por Sexo:");
            for (MediaMensalViagens media : resultado.getMediasMensais()) {
                System.out.println(media);
            }
        }
    }
}