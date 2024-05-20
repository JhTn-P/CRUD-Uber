package com.example.service;

import java.util.List;
import java.util.Scanner;

import com.example.DAO.VeiculoDAO;
import com.example.model.Veiculo;

public class VeiculoService {
    private VeiculoDAO veiculoDAO;

    public VeiculoService(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    public void gerenciarVeiculos(Scanner scanner) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir Veículo");
            System.out.println("2 - Alterar Veículo");
            System.out.println("3 - Listar Veículo");
            System.out.println("4 - Deletar Veículo");
            System.out.println("0 - Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    inserirVeiculo(scanner);
                    break;
                case 2:
                    alterarVeiculo(scanner);
                    break;
                case 3:
                    listarVeiculos();
                    break;
                case 4:
                    deletarVeiculo(scanner);
                    break;
                case 0:
                    return; // Voltar ao menu de seleção de tabela
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void inserirVeiculo(Scanner scanner) {
        // Implementar lógica para inserir veículo
        // Exemplo:
        System.out.println("Insira os dados do veículo:");

        System.out.print("Placa (máximo de 7 caracteres): ");
        String placa = scanner.nextLine();
        if (placa.length() > 7) {
            System.out.println("Limite de caracteres excedido.");
            return;
        } else if (placa.trim().isEmpty()) {
            System.out.println("Campo obrigatório.");
            return;
        }

        System.out.print("Marca (máximo de 30 caracteres): ");
        String marca = scanner.nextLine();
        if (marca.length() > 30) {
            System.out.println("Limite de caracteres excedido.");
            return;
        } else if (marca.trim().isEmpty()) {
            System.out.println("Campo obrigatório.");
            return;
        }

        System.out.print("Modelo (máximo de 30 caracteres): ");
        String modelo = scanner.nextLine();
        if (modelo.length() > 30) {
            System.out.println("Limite de caracteres excedido.");
            return;
        } else if (modelo.trim().isEmpty()) {
            System.out.println("Campo obrigatório.");
            return;
        }

        System.out.print("Ano de Fabricação (4 dígitos): ");
        int anoFabric = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        try {
            if (anoFabric < 1886 || anoFabric > 2024) {
                System.out.println("Ano inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido para o ano de fabricação. Insira apenas 4 dígitos.");
            return;
        }

        System.out.print("Capacidade de Passageiros (1 a 4 pessoas): ");
        int capacidadePass = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        try {
            if (capacidadePass < 1 || capacidadePass > 4) {
                System.out.println("A capacidade de passageiros deve ser um número entre 1 e 4.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido para a capacidade de passageiros. Insira um número entre 1 e 4.");
            return;
        }

        System.out.print("Cor (máximo de 30 caracteres): ");
        String cor = scanner.nextLine();
        if (cor.length() > 30) {
            System.out.println("Limite de caracteres excedido.");
            return;
        } else if (cor.trim().isEmpty()) {
            System.out.println("Campo obrigatório.");
            return;
        }

        System.out.print("Tipo de Combustível: ");
        String tipoCombust = scanner.nextLine().toUpperCase(); // Convertendo para maiúsculas para simplificar a
        // validação
        if (!"GADF".contains(tipoCombust) && tipoCombust.length() > 1) {
            System.out.println("O tipo de combustível deve ser G, A, D ou F.");
            return;
        }

        System.out.print("Potência do Motor: ");
        int potenciaMotor = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        // Chamando o método inserirVeiculo do veiculoDAO com os dados fornecidos pelo
        // usuário
        veiculoDAO.inserirVeiculo(placa, marca, modelo, anoFabric, capacidadePass, cor, tipoCombust, potenciaMotor);
    }

    public void alterarVeiculo(Scanner scanner) {
        System.out.println("Digite a placa do veículo que deseja alterar:");
        String placa = scanner.nextLine();

        // Verificar se o veículo com a placa fornecida existe antes de prosseguir
        if (!veiculoDAO.existeVeiculo(placa)) {
            System.out.println("Veículo com placa " + placa + " não encontrado.");
            return;
        }

        System.out.println("Insira os novos dados do veículo:");

        System.out.print("Placa (máximo 7 caracteres): ");
        String novaPlaca = scanner.nextLine();
        if (novaPlaca.length() > 7) {
            System.out.println("A placa deve ter no máximo 7 caracteres.");
            return;
        }

        System.out.print("Nova Marca (máximo 30 caracteres): ");
        String novaMarca = scanner.nextLine();
        if (novaMarca.length() > 30) {
            System.out.println("A marca deve ter no máximo 30 caracteres.");
            return;
        }

        System.out.print("Novo Modelo (máximo 30 caracteres): ");
        String novoModelo = scanner.nextLine();
        if (novoModelo.length() > 30) {
            System.out.println("O modelo deve ter no máximo 30 caracteres.");
            return;
        }

        System.out.print("Novo Ano de Fabricação (4 dígitos): ");
        int novoAnoFabric = scanner.nextInt();
        try {
            if (novoAnoFabric < 1886 || novoAnoFabric > 2024) {
                System.out.println("O ano digitado é inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido para o ano de fabricação. Insira apenas 4 dígitos.");
            return;
        }

        System.out.print("Nova Capacidade de Passageiros (1 a 4): ");
        int novaCapacidadePass = scanner.nextInt();
        scanner.nextLine();
        try {
            if (novaCapacidadePass < 1 || novaCapacidadePass > 4) {
                System.out.println("A capacidade de passageiros deve ser um número entre 1 e 4.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido para a capacidade de passageiros. Insira um número entre 1 e 4.");
            return;
        }

        System.out.print("Nova Cor (máximo 30 caracteres): ");
        String novaCor = scanner.nextLine();
        if (novaCor.length() > 30) {
            System.out.println("Limite de caracteres excedido.");
            return;
        } else if (novaCor.trim().isEmpty()) {
            System.out.println("Campo obrigatório.");
            return;
        }

        System.out.print("Novo Tipo de Combustível (G, A, D ou F): ");
        String novoTipoCombust = scanner.nextLine().toUpperCase();
        if (!"GADF".contains(novoTipoCombust)) {
            System.out.println("O tipo de combustível deve ser G, A, D ou F.");
            return;
        }

        System.out.print("Nova Potência do Motor: ");
        int novaPotenciaMotor = scanner.nextInt();

        veiculoDAO.atualizarVeiculo(placa, novaMarca, novoModelo, novoAnoFabric, novaCapacidadePass, novaCor,
                novoTipoCombust, novaPotenciaMotor);
    }

    public void listarVeiculos() {
        System.out.println("Lista de veículos:");
        List<Veiculo> veiculos = veiculoDAO.listarVeiculos();
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo);
        }

    }

    public void deletarVeiculo(Scanner scanner) {
        System.out.println("Digite a placa do veículo que deseja excluir:");
        String placa = scanner.nextLine();
        veiculoDAO.excluirVeiculo(placa);
    }
}
