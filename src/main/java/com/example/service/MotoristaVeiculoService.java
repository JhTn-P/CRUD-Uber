package com.example.service;

import java.util.List;
import java.util.Scanner;

import com.example.DAO.MotoristaVeiculoDAO;
import com.example.DAO.MotoristasDAO;
import com.example.DAO.VeiculoDAO;
import com.example.model.MotoristaVeiculo;
import com.example.model.Motoristas;

public class MotoristaVeiculoService {
    private MotoristaVeiculoDAO motoristaVeiculoDAO;
    private MotoristasDAO motoristasDAO;
    private VeiculoDAO veiculoDAO;

    public MotoristaVeiculoService(MotoristaVeiculoDAO motoristaVeiculoDAO, MotoristasDAO motoristasDAO, VeiculoDAO veiculoDAO) {
        this.motoristaVeiculoDAO = motoristaVeiculoDAO;
        this.motoristasDAO = motoristasDAO;
        this.veiculoDAO = veiculoDAO;
    }

    public void gerenciarMotoristasVeiculos(Scanner scanner) {
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
                    inserirMotoristaVeiculo(scanner);
                    break;
                case 2:
                    alterarMotoristaVeiculo(scanner);
                    break;
                case 3:
                    listarMotoristaVeiculo();
                    break;
                case 4:
                    deletarMotoristaVeiculo(scanner);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void inserirMotoristaVeiculo(Scanner scanner) {
        System.out.print("CPF do Motorista: ");
        long cpf_motorista = scanner.nextLong();
        scanner.nextLine();

        if (!motoristasDAO.verificarMotorista(cpf_motorista)) {
            System.out.println("Erro: CPF não encontrado na tabela PESSOAS.");
            return;
        }

        System.out.print("Placa do veículo: ");
        String placa_veiculo = scanner.nextLine();
        if(!veiculoDAO.existeVeiculo(placa_veiculo)) {
            System.out.println("Erro: placa não encontrada.");
            return;
        }

        motoristaVeiculoDAO.inserirMotoristaVeiculo(cpf_motorista, placa_veiculo);
    }

    public void alterarMotoristaVeiculo(Scanner scanner) {
        System.out.print("CPF do Motorista: ");
        long cpf_motorista = scanner.nextLong();
        scanner.nextLine();

        if (!motoristaVeiculoDAO.verificarMotoristaVeiculo(cpf_motorista)) {
            System.out.println("Erro: Motorista não encontrado.");
            return;
        }

        System.out.print("Novo CPF: ");
        long novo_cpf = scanner.nextLong();
        scanner.nextLine();
        if(!motoristasDAO.verificarMotorista(novo_cpf)){
            System.out.println("Motorista não encontrado");
            return;
        }

        System.out.print("Nova placa: ");
        String nova_placa = scanner.nextLine();
        if (!veiculoDAO.existeVeiculo(nova_placa)){
            System.out.println("Erro: placa nao encontrada");
        }

        motoristaVeiculoDAO.alterarMotoristaVeiculo(novo_cpf, nova_placa);
    }

    public void listarMotoristaVeiculo() {
        System.out.println("Listagem de Motoristas e seus veículos:");
        List<MotoristaVeiculo> motoristaVeiculos = motoristaVeiculoDAO.listarMotoristaVeiculos();
        for (MotoristaVeiculo motoristaVeiculo : motoristaVeiculos) {
            System.out.println(motoristaVeiculo);
        }

    }

    public void deletarMotoristaVeiculo(Scanner scanner) {
        System.out.print("CPF do Motorista: ");
        long cpf_motorista = scanner.nextLong();
        scanner.nextLine();

        if (!motoristaVeiculoDAO.verificarMotoristaVeiculo(cpf_motorista)) {
            System.out.println("Erro: Motorista não encontrado.");
            return;
        } else{
            System.out.println("Motorista deletado com sucesos.");
        }

        motoristaVeiculoDAO.deletarMotoristaVeiculo(cpf_motorista);
    }
}
