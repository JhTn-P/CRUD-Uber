package com.example.service;

import com.example.DAO.PessoasDAO;
import com.example.DAO.ProprietarioDAO;
import com.example.model.Proprietario;
import com.example.model.passageiro;

import java.util.List;
import java.util.Scanner;

public class PropietarioService {

    ProprietarioDAO propietarioDAO = new ProprietarioDAO();
    PessoasDAO pessoasDAO = new PessoasDAO();

    public PropietarioService(ProprietarioDAO propietarioDAO, PessoasDAO pessoasDAO) {
        this.propietarioDAO = propietarioDAO;
        this.pessoasDAO = pessoasDAO;
    }
    public void gerenciarPropietario(Scanner scanner) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir proprietario");
            System.out.println("2 - Alterar proprietario");
            System.out.println("3 - Listar proprietario");
            System.out.println("4 - Deletar proprietario");
            System.out.println("0 - Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    inserirProprietario(scanner);
                    break;
                case 2:
                    alterarProprietario(scanner);
                    break;
                case 3:
                    listarProprietario();
                    break;
                case 4:
                    deletarProprietario(scanner);
                    break;
                case 0:
                    return; // Voltar ao menu de seleção de tabela
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void inserirProprietario(Scanner scanner) {

        System.out.println("Insira os dados do Propietario:");

        System.out.print("Cpf (máximo de 11 números): ");
        long cpf_prop = scanner.nextLong();
        
        if (!pessoasDAO.verificarPessoa(cpf_prop)) {
            System.out.println("Erro: CPF não encontrado na tabela PESSOAS.");
            return;
        }

            System.out.print("CNH (máximo de 15 caracteres): ");
            String cnh_prop = scanner.next();

            while(cnh_prop.length() > 15) {
                System.out.println("Limite de caracteres excedido.");
                System.out.print("CNH (máximo de 15 caracteres): ");
                cnh_prop = scanner.next();
            }

            System.out.print("Banco Propietario : ");
            int banco_prop = scanner.nextInt();

            System.out.print("Agencia: ");
            int agencia_prop = scanner.nextInt();

            System.out.print("Conta:");
            int conta_prop = scanner.nextInt();
            propietarioDAO.inserirProprietario(cpf_prop, cnh_prop, banco_prop, agencia_prop, conta_prop);
        
    }

    public void alterarProprietario(Scanner scanner) {
        System.out.println("Digite o cpf do Propietario o qual deseja modificar:");
        long cpf_prop = scanner.nextLong();

        // Verificar se o veículo com a placa fornecida existe antes de prosseguir
        if (!propietarioDAO.existeProprietario(cpf_prop)) {
            System.out.println("Propietario com o cpf: " + cpf_prop + " não encontrado.");

        }
        else {
            System.out.println("Insira os novos dados do Propietario:");


            System.out.print("Nova cnh do propietario (máximo 15 caracteres): ");
            String cnh_prop = scanner.next();
            if (cnh_prop.length() > 15) {
                System.out.println("A cnh deve ter no máximo 15 caracteres.");

            }

            System.out.print("Banco do Propietario : ");
            int banco_prop = scanner.nextInt();


            System.out.print("Agencia: ");
            int agencia_prop = scanner.nextInt();

            System.out.print("Conta Propietario: ");
            int conta_prop = scanner.nextInt();
            propietarioDAO.atualizarProprietario(cpf_prop, cnh_prop, banco_prop, agencia_prop, conta_prop);
        }
    }
    public void listarProprietario() {
        System.out.println("Lista de Propietarios:");
        List<Proprietario> propietarios = propietarioDAO.listarPropritario();
        for (Proprietario proprietario : propietarios) {
            System.out.println(proprietario);
        }

    }
    public void deletarProprietario(Scanner scanner) {
        System.out.println("Digite o CPF do propietario que deseja excluir:");
        long cpf_prop = scanner.nextLong();
        propietarioDAO.excluirProprietario(cpf_prop);
    }
}
