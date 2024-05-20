package com.example.service;

import com.example.DAO.passageiroDAO;
import com.example.model.passageiro;



import java.util.List;
import java.util.Scanner;

public class PassageiroService {
    private passageiroDAO passageiroDAO;

    public PassageiroService(passageiroDAO passageiroDAO) {
        this.passageiroDAO = passageiroDAO;
    }

    public void gerenciarPassageiro(Scanner scanner) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - INSERIR");
            System.out.println("2 - ALTERAR");
            System.out.println("3 - LISTAR");
            System.out.println("4 - DELETAR");
            System.out.println("5 - VOLTAR");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    inserirPassageiro(scanner);
                    break;
                case 2:
                    alterarPassageiro(scanner);
                    break;
                case 3:
                    listarPassageiro();
                    break;
                case 4:
                    deletarPassageiro(scanner);
                    break;
                case 5:
                    return; // Voltar ao menu de seleção de tabela
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void inserirPassageiro(Scanner scanner) {

        System.out.println("Insira os dados do Passageiro:");

        System.out.print("Cpf (máximo de 11 números): ");
        long cpf_passag = scanner.nextLong();
        if(!passageiroDAO.existePassageiro(cpf_passag)) {

            scanner.nextLine();
            System.out.print("Cartão de Crédito (máximo de 20 caracteres): ");
            String cartao_cred = scanner.nextLine();

            if (cartao_cred.length() > 20) {
                System.out.println("Limite de caracteres excedido.");
            }

            System.out.print("Bandeira do C3artão (máximo de 20 caracteres): ");
            String bandeira_cartao = scanner.nextLine();
            if (bandeira_cartao.length() > 20) {
                System.out.println("Limite de caracteres excedido.");
            }
            System.out.print("Cidade de Origem (máximo de 30 caracteres): ");
            String cidade_orig = scanner.nextLine();
            if (cidade_orig.length() > 30) {
                System.out.println("Limite de caracteres excedido.");

            }
            passageiroDAO.inserirPassageiro(cpf_passag, cartao_cred, bandeira_cartao, cidade_orig);
        }else System.out.print("Digite outro CPF, esse já existe!!! ");
    }

    public void alterarPassageiro(Scanner scanner) {
        System.out.println("Digite o cpf do Passageiro o qual deseja modificar:");
        long cpf_passag = scanner.nextLong();

        // Verificar se o veículo com a placa fornecida existe antes de prosseguir
        if (!passageiroDAO.existePassageiro(cpf_passag)) {
            System.out.println("Passageiro com o cpf: " + cpf_passag + " não encontrado.");

        }
        else {
            System.out.println("Insira os novos dados do Passageiro:");


            System.out.print("Nova Cartão de Crédito (máximo 20 caracteres): ");
            String cartao_cred = scanner.nextLine();
            if (cartao_cred.length() > 20) {
                System.out.println("A marca deve ter no máximo 20 caracteres.");

            }
            scanner.nextLine();
            System.out.print("Bandeira do Cartão (máximo 20 caracteres): ");
            String bandeira_cartao = scanner.nextLine();
            if (bandeira_cartao.length() > 20) {
                System.out.println("A bandeira do cartão deve ter no máximo 20 caracteres.");

            }

            System.out.print("Cidade de Origem (máximo 30 caracteres): ");
            String cidade_orig = scanner.nextLine();
            if (cidade_orig.length() > 30) {
                System.out.println("A cidade de origem deve ter no máximo 30 caracteres.");

            }
            passageiroDAO.atualizarPassageiro(cpf_passag, cartao_cred, bandeira_cartao, cidade_orig);
        }
    }

    public void listarPassageiro() {
        System.out.println("Lista de Passageiros:");
        List<passageiro> passasgeiros = passageiroDAO.listarPassageiro();
        for (passageiro passageiro : passasgeiros) {
            System.out.println(passageiro);
        }

    }

    public void deletarPassageiro(Scanner scanner) {
        System.out.println("Digite o CPF do passageiro que deseja excluir:");
        long cpf_passag = scanner.nextLong();
        passageiroDAO.excluirPassageiro(cpf_passag);
    }
}