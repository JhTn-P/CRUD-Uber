package com.example.service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.DAO.MotoristaVeiculoDAO;
import com.example.DAO.MotoristasDAO;
import com.example.DAO.PassageiroDAO;
import com.example.DAO.VeiculoDAO;
import com.example.DAO.ViagemDAO;
import com.example.model.Viagem;
import com.example.model.passageiro;

public class ViagemService {
    private ViagemDAO viagemDAO;
    private PassageiroDAO passageiroDAO;
    private MotoristaVeiculoDAO motoristaVeiculoDAO;
    private VeiculoDAO veiculoDAO;
    private List<Viagem> viagens;

    public ViagemService(ViagemDAO viagemDAO, PassageiroDAO passageiroDAO, MotoristaVeiculoDAO motoristaVeiculoDAO,
            VeiculoDAO veiculoDAO) {
        this.viagemDAO = viagemDAO;
        this.passageiroDAO = passageiroDAO;
        this.motoristaVeiculoDAO = motoristaVeiculoDAO;
        this.veiculoDAO = veiculoDAO;
        this.viagens = viagemDAO.listarViagem();
    }

    public void gerenciarViagem(Scanner scanner) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir viagem");
            System.out.println("2 - Alterar viagem");
            System.out.println("3 - Listar viagem");
            System.out.println("4 - Deletar viagem");
            System.out.println("0 - Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    inserirViagem(scanner);
                    break;
                case 2:
                    alterarViagem(scanner);
                    break;
                case 3:
                    listarViagem();
                    break;
                case 4:
                    excluirViagem(scanner);
                    break;
                case 0:
                    return; // Voltar ao menu de seleção de tabela
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void inserirViagem(Scanner scanner) {
        System.out.println("Dados da viagem:");

        System.out.print("CPF do passageiro: ");
        long cpf_pass_viag = scanner.nextLong();
        if (!passageiroDAO.existePassageiro(cpf_pass_viag)) {
            System.out.println("CPF não encontrado.");
            return;
        }

        scanner.nextLine();
        System.out.print("CPF do motorista: ");
        long cpf_mot_viag = scanner.nextLong();
        if (!motoristaVeiculoDAO.verificarMotoristaVeiculo(cpf_mot_viag)) {
            System.out.println("Motorista não encontrado.");
            return;
        }

        scanner.nextLine();
        System.out.print("Placa do veículo: ");
        String placa_veic_viag = scanner.nextLine();
        if (!veiculoDAO.existeVeiculo(placa_veic_viag)) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Local para buscar: ");
        String local_orig_viag = scanner.nextLine();

        System.out.print("Local de destino: ");
        String local_dest_viag = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.print("Início da viagem: ");
        String dateString = scanner.nextLine();
        java.util.Date dt_hora_inicio = null;
        try {
            dt_hora_inicio = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato yyyy-MM-dd.");
            e.printStackTrace();
            return; // Saia do método se a data for inválida
        }

        // Converta java.util.Date para java.sql.Date
        Date sqlDate = new Date(dt_hora_inicio.getTime());

        System.out.print("Fim da viagem: ");
        String dateString2 = scanner.nextLine();
        java.util.Date dt_hora_fim = null;
        try {
            dt_hora_fim = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato yyyy-MM-dd.");
            e.printStackTrace();
            return; // Saia do método se a data for inválida
        }

        // Converta java.util.Date para java.sql.Date
        Date sqlDate2 = new Date(dt_hora_fim.getTime());

        System.out.print("Quantidade de passageiros: ");
        int qtde_pass = scanner.nextInt();
        if (qtde_pass < 1 || qtde_pass > 4) {
            System.out.println("Valor inválido.");
            return;
        }

        scanner.nextLine();
        System.out.print("Forma de pagamento (DINHEIRO, CARTÃO ou POSTERIORMENTE): ");
        String forma_pagto = scanner.nextLine().toUpperCase();
        while (!forma_pagto.equals("DINHEIRO") && !forma_pagto.equals("CARTÃO")
                && !forma_pagto.equals("POSTERIORMENTE")) {
            System.out.println("Forma de pagamento inválida. Por favor, insira DINHEIRO, CARTÃO ou POSTERIORMENTE.");
            System.out.print("Forma de pagamento (DINHEIRO, CARTÃO ou POSTERIORMENTE): ");
            forma_pagto = scanner.nextLine().toUpperCase();
        }

        System.out.print("Valor da corrida: ");
        double valor_pagto = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Cancelamento pelo motorista (S/N): ");
        String cancelam_mot = scanner.nextLine().toUpperCase();
        while (!cancelam_mot.equals("S") && !cancelam_mot.equals("N")) {
            System.out.println("Responda só com S ou N.");
            System.out.println("Cancelamento pelo motorista (S/N): ");
            cancelam_mot = scanner.nextLine().toUpperCase();
        }

        System.out.print("Cancelamento pelo passageiro (S/N): ");
        String cancelam_pass = scanner.nextLine().toUpperCase();
        while (!cancelam_pass.equals("S") && !cancelam_pass.equals("N")) {
            System.out.println("Responda só com S ou N.");
            System.out.print("Cancelamento pelo passageiro (S/N): ");
            cancelam_mot = scanner.nextLine().toUpperCase();
        }

        viagemDAO.inserirViagem(cpf_pass_viag, cpf_mot_viag, placa_veic_viag, local_orig_viag, local_dest_viag, sqlDate,
                sqlDate2, qtde_pass, forma_pagto, valor_pagto, cancelam_mot, cancelam_pass);
    }

    public void alterarViagem(Scanner scanner) {
        // Solicite e valide os dados da viagem que você deseja alterar
        System.out.print("CPF do passageiro da viagem que deseja alterar: ");
        long cpfPassageiro = scanner.nextLong();
        System.out.print("CPF do motorista da viagem que deseja alterar: ");
        long cpfMotorista = scanner.nextLong();
        scanner.nextLine(); // Consumindo a quebra de linha
        System.out.print("Placa do veículo da viagem que deseja alterar: ");
        String placaVeiculo = scanner.nextLine();
        System.out.print("Data de início da viagem que deseja alterar (formato: dd-MM-yyyy): ");
        String dataInicioString = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dataInicio = null;
        try {
            dataInicio = dateFormat.parse(dataInicioString);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato dd-MM-yyyy.");
            e.printStackTrace();
            return;
        }

        // Converta java.util.Date para java.sql.Date
        Date sqlDateInicio = new Date(dataInicio.getTime());

        // Se a viagem existe, prossiga com a lógica para alterá-la
        System.out.println("Viagem encontrada. Insira os novos dados da viagem:");

        // Solicite e valide os novos dados da viagem

        // Solicite e valide o novo local de origem
        System.out.print("Novo local para buscar: ");
        String novoLocalOrigem = scanner.nextLine();

        // Solicite e valide o novo local de destino
        System.out.print("Novo local de destino: ");
        String novoLocalDestino = scanner.nextLine();

        // Solicite e valide a nova data de fim da viagem
        System.out.print("Nova data de fim da viagem (formato: dd-MM-yyyy): ");
        String novaDataFimString = scanner.nextLine();
        java.util.Date novaDataFim = null;
        try {
            novaDataFim = dateFormat.parse(novaDataFimString);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato dd-MM-yyyy.");
            e.printStackTrace();
            return;
        }

        // Converta java.util.Date para java.sql.Date
        Date novaSqlDateFim = new Date(novaDataFim.getTime());

        // Solicite e valide a nova quantidade de passageiros
        System.out.print("Nova quantidade de passageiros: ");
        int novaQtdePassageiros = scanner.nextInt();
        if (novaQtdePassageiros < 1 || novaQtdePassageiros > 4) {
            System.out.println("Valor inválido.");
            return;
        }

        scanner.nextLine(); // Consumindo a quebra de linha

        // Solicite e valide a nova forma de pagamento
        System.out.print("Nova forma de pagamento (DINHEIRO, CARTÃO ou POSTERIORMENTE): ");
        String novaFormaPagamento = scanner.nextLine().toUpperCase();
        while (!novaFormaPagamento.equals("DINHEIRO") && !novaFormaPagamento.equals("CARTÃO")
                && !novaFormaPagamento.equals("POSTERIORMENTE")) {
            System.out.println("Forma de pagamento inválida. Por favor, insira DINHEIRO, CARTÃO ou POSTERIORMENTE.");
            System.out.print("Nova forma de pagamento (DINHEIRO, CARTÃO ou POSTERIORMENTE): ");
            novaFormaPagamento = scanner.nextLine().toUpperCase();
        }

        // Solicite e valide o novo valor da corrida
        System.out.print("Novo valor da corrida: ");
        double novoValorPagamento = scanner.nextDouble();

        scanner.nextLine(); // Consumindo a quebra de linha

        // Solicite e valide o novo cancelamento pelo motorista
        System.out.print("Novo cancelamento pelo motorista (S/N): ");
        String novoCancelamentoMotorista = scanner.nextLine().toUpperCase();
        while (!novoCancelamentoMotorista.equals("S") && !novoCancelamentoMotorista.equals("N")) {
            System.out.println("Responda só com S ou N.");
            System.out.print("Novo cancelamento pelo motorista (S/N): ");
            novoCancelamentoMotorista = scanner.nextLine().toUpperCase();
        }

        // Solicite e valide o novo cancelamento pelo passageiro
        System.out.print("Novo cancelamento pelo passageiro (S/N): ");
        String novoCancelamentoPassageiro = scanner.nextLine().toUpperCase();
        while (!novoCancelamentoPassageiro.equals("S") && !novoCancelamentoPassageiro.equals("N")) {
            System.out.println("Responda só com S ou N.");
            System.out.print("Novo cancelamento pelo passageiro (S/N): ");
            novoCancelamentoPassageiro = scanner.nextLine().toUpperCase();
        }

        // Converta java.util.Date para java.sql.Date
        Date novaSqlDateInicio = null;
        novaSqlDateInicio = new Date(sqlDateInicio.getTime());

        // Chame o método no DAO para atualizar a viagem com os novos dados
        viagemDAO.alterarViagem(cpfPassageiro, cpfMotorista, placaVeiculo, novoLocalOrigem, novoLocalDestino,
                novaSqlDateInicio, novaSqlDateFim, novaQtdePassageiros, novaFormaPagamento, novoValorPagamento,
                novoCancelamentoMotorista, novoCancelamentoPassageiro);
    }

    public void listarViagem() {
        System.out.println("Lista de viagens:");
        List<Viagem> viagens = viagemDAO.listarViagem();
        for (Viagem viagem : viagens) {
            System.out.println(viagem);
        }
    }

    public void excluirViagem(Scanner scanner) {
        System.out.println("Digite o CPF do passageiro que deseja excluir:");
        long cpf_pass_viag = scanner.nextLong();
        System.out.println("Digite o CPF do motorista:");
        long cpf_mot_viag = scanner.nextLong();
        scanner.nextLine(); // Consuma a quebra de linha pendente

        System.out.println("Digite a placa do veículo:");
        String placa_veic_viag = scanner.nextLine();

        System.out.println("Digite a data de início da viagem (formato: dd-MM-yyyy):");
        String dataInicioString = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dt_hora_inicio = null;
        try {
            dt_hora_inicio = dateFormat.parse(dataInicioString);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato dd-MM-yyyy.");
            e.printStackTrace();
            return;
        }

        // Converta java.util.Date para java.sql.Date
        java.sql.Date sqlDateInicio = new java.sql.Date(dt_hora_inicio.getTime());

        // Agora você pode chamar o método no DAO para excluir a viagem
        viagemDAO.excluirViagem(cpf_pass_viag, cpf_mot_viag, placa_veic_viag, sqlDateInicio);
    }

    public void adicionarViagem(Viagem viagem) {
        viagens.add(viagem);
    }

    
}