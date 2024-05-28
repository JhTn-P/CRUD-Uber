package com.example.service;

import java.util.List;
import java.util.Scanner;

import com.example.DAO.TipoPagtoDAO;
import com.example.model.TipoPgto;
import com.example.model.passageiro;

public class TipoPgtoService {
    private TipoPagtoDAO tipoPagtoDAO;

    public TipoPgtoService(TipoPagtoDAO tipoPagtoDAO) {
        this.tipoPagtoDAO = tipoPagtoDAO;
    }
    
    public void gerenciarPassageiro(Scanner scanner) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir tipo de pagamento");
            System.out.println("2 - Alterar tipo de pagamento");
            System.out.println("3 - Listar tipo de pagamento");
            System.out.println("4 - Deletar tipo de pagamento");
            System.out.println("0 - Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    inserirPagto(scanner);
                    break;
                case 2:
                    alterarPagto(scanner);
                    break;
                case 3:
                    listarPagamentos();
                    break;
                case 4:
                    excluirPagamento(scanner);
                    break;
                case 0:
                    return; // Voltar ao menu de seleção de tabela
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void inserirPagto(Scanner scanner) {
        System.out.println("Insira os dados de pagamento: ");

        System.out.print("Código de pagamento: ");
        int cod_pagto = scanner.nextInt();
        if(tipoPagtoDAO.verificarPagamento(cod_pagto)){
            System.out.println("Erro no código de pagamento!");
            return;
        }

        scanner.nextLine();
        System.out.print("Descrição pagamento: ");
        String desc_pagto = scanner.nextLine();
        while (desc_pagto.length() > 20) {
            System.out.println("O campo deve conter menos que 20 caracteres.");
            desc_pagto = scanner.nextLine();
        }
        
        tipoPagtoDAO.inserirPagto(cod_pagto, desc_pagto);
    }

    public void alterarPagto(Scanner scanner) {
        System.out.print("Código de pagamento para ser alterado: ");
        int cod_pagto = scanner.nextInt();
        if(!tipoPagtoDAO.verificarPagamento(cod_pagto)){
            System.out.println("Erro no código de pagamento!");
            return;
        }

        scanner.nextLine();
        System.out.print("Descrição pagamento: ");
        String desc_pagto = scanner.nextLine();
        while (desc_pagto.length() > 20) {
            System.out.println("O campo deve conter menos que 20 caracteres.");
            desc_pagto = scanner.nextLine();
        }
        
        tipoPagtoDAO.editarPagto(cod_pagto, desc_pagto);
    }

    public void listarPagamentos() {
        System.out.println("Lista de Passageiros:");
        List<TipoPgto> listarPagamentos = tipoPagtoDAO.listarPagamentos();
        for (TipoPgto listaPagamento : listarPagamentos) {
            System.out.println(listarPagamentos);
        }
    }

    public void excluirPagamento(Scanner scanner) {
        System.out.println("Digite o código de pagamento que deseja excluir:");
        int cod_pagto = scanner.nextInt();
        tipoPagtoDAO.excluirPagamento(cod_pagto);
    }
}
