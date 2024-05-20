package com.example.service;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.example.DAO.PessoasDAO;
import com.example.DAO.MotoristasDAO;
import com.example.model.Motoristas;

public class MotoristasService {
    private MotoristasDAO motoristasDAO;
    private PessoasDAO pessoasDAO;

    public MotoristasService(MotoristasDAO motoristasDAO, PessoasDAO pessoasDAO) {
        this.motoristasDAO = motoristasDAO;
        this.pessoasDAO = pessoasDAO;
    }

    public void gerenciarMotoristas(Scanner scanner) {
        while (true) {
            System.out.println("Escolha a operação:");
            System.out.println("1 - Inserir Motorista");
            System.out.println("2 - Alterar Motorista");
            System.out.println("3 - Listar Motoristas");
            System.out.println("4 - Deletar Motorista");
            System.out.println("0 - Voltar");

            int escolhaOperacao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (escolhaOperacao == 0) {
                break;
            }

            switch (escolhaOperacao) {
                case 1:
                    inserirMotorista(scanner);
                    break;
                case 2:
                    alterarMotorista(scanner);
                    break;
                case 3:
                    listarMotoristas();
                    break;
                case 4:
                    deletarMotorista(scanner);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void inserirMotorista(Scanner scanner) {
        System.out.print("CPF do Motorista: ");
        long cpfMotorista = scanner.nextLong();
        scanner.nextLine();

        if (!pessoasDAO.verificarPessoa(cpfMotorista)) {
            System.out.println("Erro: CPF não encontrado na tabela PESSOAS.");
            return;
        }

        System.out.print("CNH: ");
        String cnh = scanner.nextLine();
        if (!cnh.matches("\\d+")) {
            System.out.println("CNH inválida. Deve conter apenas números.");
            return;
        }

        System.out.print("Banco: ");
        int banco = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Agência: ");
        int agencia = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Conta: ");
        int conta = scanner.nextInt();
        scanner.nextLine();

        Motoristas motorista = new Motoristas(cpfMotorista, cnh, banco, agencia, conta);
        motoristasDAO.inserirMotorista(motorista);
    }

    public void alterarMotorista(Scanner scanner) {
        System.out.print("CPF do Motorista: ");
        long cpfMotorista = scanner.nextLong();
        scanner.nextLine();

        if (!motoristasDAO.verificarMotorista(cpfMotorista)) {
            System.out.println("Erro: Motorista não encontrado.");
            return;
        }

        System.out.print("Nova CNH: ");
        String cnh = scanner.nextLine();
        if (!cnh.matches("\\d+")) {
            System.out.println("CNH inválida. Deve conter apenas números.");
            return;
        }

        System.out.print("Novo Banco: ");
        int banco = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nova Agência: ");
        int agencia = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nova Conta: ");
        int conta = scanner.nextInt();
        scanner.nextLine();

        Motoristas motoristaAtualizado = new Motoristas(cpfMotorista, cnh, banco, agencia, conta);
        motoristasDAO.alterarMotorista(cpfMotorista, motoristaAtualizado);
    }

    public void listarMotoristas() {
        System.out.println("Listagem de Motoristas:");
        List<Motoristas> motoristas = motoristasDAO.listarMotoristas();
        for (Motoristas motorista : motoristas) {
            System.out.println(motorista);
        }

    }

    public void deletarMotorista(Scanner scanner) {
        System.out.print("CPF do Motorista: ");
        long cpfMotorista = scanner.nextLong();
        scanner.nextLine();

        if (!motoristasDAO.verificarMotorista(cpfMotorista)) {
            System.out.println("Erro: Motorista não encontrado.");
            return;
        } else{
            System.out.println("Motorista deletado com sucesos.");
        }

        motoristasDAO.deletarMotorista(cpfMotorista);
    }
}
