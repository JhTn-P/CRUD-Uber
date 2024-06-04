package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.example.DAO.MotoristaVeiculoDAO;
import com.example.DAO.MotoristasDAO;
import com.example.DAO.PassageiroDAO;
import com.example.DAO.PessoasDAO;
import com.example.DAO.ProprietarioDAO;
import com.example.DAO.TipoPagtoDAO;
import com.example.DAO.VeiculoDAO;
import com.example.DAO.ViagemDAO;
import com.example.connection.ConnectionFactory;
import com.example.model.ViagemDetalhes;
import com.example.service.FaturamentoService;
import com.example.service.MotoristaVeiculoService;
import com.example.service.MotoristasService;
import com.example.service.PassageiroService;
import com.example.service.PessoasService;
import com.example.service.PropietarioService;
import com.example.service.TipoPgtoService;
import com.example.service.VeiculoService;
import com.example.service.ViagemDetalhesService;
import com.example.service.ViagemService;
import com.example.util.ResultadoBuscaViagens;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Connection conexao = null;
        try {
            // Estabelecimento da conexão com o banco de dados
            conexao = ConnectionFactory.getConnection();

            // Instanciando os DAOs
            ProprietarioDAO proprietarioDAO = new ProprietarioDAO(conexao);
            VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
            PessoasDAO pessoasDAO = new PessoasDAO(conexao);
            MotoristasDAO motoristasDAO = new MotoristasDAO(conexao);
            PassageiroDAO passageiroDAO = new PassageiroDAO(conexao);
            MotoristaVeiculoDAO motoristaVeiculoDAO = new MotoristaVeiculoDAO(conexao);
            ViagemDAO viagemDAO = new ViagemDAO(conexao);
            TipoPagtoDAO tipoPagtoDAO = new TipoPagtoDAO(conexao);
            // Adicione aqui outros DAOs conforme necessário

            // Instanciando os serviços
            PropietarioService propietarioService = new PropietarioService(proprietarioDAO, pessoasDAO);
            PassageiroService passageiroService = new PassageiroService(passageiroDAO, pessoasDAO);
            VeiculoService veiculoService = new VeiculoService(veiculoDAO);
            PessoasService pessoasService = new PessoasService(pessoasDAO);
            MotoristasService motoristasService = new MotoristasService(motoristasDAO, pessoasDAO);
            MotoristaVeiculoService motoristaVeiculoService = new MotoristaVeiculoService(motoristaVeiculoDAO,
                    motoristasDAO, veiculoDAO);
            ViagemService viagemService = new ViagemService(viagemDAO, passageiroDAO, motoristaVeiculoDAO, veiculoDAO);
            TipoPgtoService tipoPgtoService = new TipoPgtoService(tipoPagtoDAO);
            ViagemDetalhesService viagemDetalhesService = new ViagemDetalhesService(viagemDAO);
            FaturamentoService faturamentoService = new FaturamentoService(viagemDAO);
            // Adicione aqui outros serviços conforme necessário

            while (true) {
                System.out.println("Escolha a tabela:");
                System.out.println("1 - Veículos");
                System.out.println("2 - Pessoas");
                System.out.println("3 - Passageiros");
                System.out.println("4 - Motorista");
                System.out.println("5 - Proprietários");
                System.out.println("6 - Viagem");
                System.out.println("7 - Motorista do veículo");
                System.out.println("8 - Tipo de pagamento");
                System.out.println("9 - Buscar viagens por marca e data");
                System.out.println("10 - Faturamentos");
                System.out.println("11 - Faturamento detalhado");
                System.out.println("0 - Sair");

                int escolhaTabela = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                if (escolhaTabela == 0) {
                    break;
                }

                switch (escolhaTabela) {
                    case 1:
                        veiculoService.gerenciarVeiculos(scanner);
                        break;
                    case 2:
                        pessoasService.gerenciarPessoas(scanner);
                        break;

                    case 3:
                        passageiroService.gerenciarPassageiro(scanner);
                        break;

                    case 4:
                        motoristasService.gerenciarMotoristas(scanner);
                        break;

                    case 5:
                        propietarioService.gerenciarPropietario(scanner);
                        break;

                    case 6:
                        viagemService.gerenciarViagem(scanner);
                        break;

                    case 7:
                        motoristaVeiculoService.gerenciarMotoristasVeiculos(scanner);
                        break;

                    case 8:
                        tipoPgtoService.gerenciarPassageiro(scanner);
                        break;

                    case 9:
                        viagemDetalhesService.buscarViagensPorMarcaEData(scanner);
                        break;

                    case 10:
                        faturamentoService.buscarMaioresFaturamentosPorMes(scanner);
                        break;

                    case 11:
                        faturamentoService.buscarFaturamentoDetalhadoPorMes(scanner);
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão com o banco de dados");
                    e.printStackTrace();
                }
            }
            scanner.close();
        }
    }
}