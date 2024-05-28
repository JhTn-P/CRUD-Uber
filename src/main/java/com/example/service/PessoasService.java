package com.example.service;

import java.util.List;
import java.util.Scanner;

import com.example.DAO.PessoasDAO;
import com.example.model.Pessoas;

public class PessoasService {
    private PessoasDAO pessoasDAO;

    public PessoasService (PessoasDAO pessoasDAO) {
        this.pessoasDAO = pessoasDAO;
    }

    public void gerenciarPessoas(Scanner scanner) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir pessoa");
            System.out.println("2 - Alterar pessoa");
            System.out.println("3 - Listar pessoa");
            System.out.println("4 - Deletar pessoa");
            System.out.println("0 - Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    inserirPessoa(scanner);
                    break;
                case 2:
                    editarPessoa(scanner);
                    break;
                case 3:
                    listarPessoas();
                    break;
                case 4:
                    excluirPessoa(scanner);
                    break;
                case 0:
                    return; // Voltar ao menu de seleção de tabela
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public void inserirPessoa(Scanner scanner) {
        System.out.print("CPF (11 caracteres numéricos): ");
        long cpf_pessoa = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if(nome.length() > 50 || nome.matches(".*\\d.*") || nome.trim().isEmpty()) {
            System.out.println("Nome inválido.");
            return;
        }

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        if(endereco.length() > 50){
            System.out.println("Endereço inválido.");
            return;
        }

        System.out.print("Telefone: ");
        long telefone = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Sexo (F/M): ");
        String sexo = scanner.nextLine().toUpperCase();
        if(!sexo.matches("[FM]")){
            System.out.println("Campo inválido.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if(email.length() > 30) {
            System.out.println("Email inválido.");
            return;
        }

        Pessoas pessoas = new Pessoas(cpf_pessoa, nome, endereco, telefone, sexo, email);
        pessoasDAO.inserirPessoa(pessoas);
    }

    public void editarPessoa(Scanner scanner) {
        System.out.print("CPF: ");
        long cpfPessoa = scanner.nextLong();
        scanner.nextLine();

        if(pessoasDAO.verificarPessoa(cpfPessoa)) {
            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            if (nome.length() > 50 || nome.matches(".*\\d.*") || nome.trim().isEmpty()) {
                System.out.println("Nome inválido. Deve ter no máximo 50 caracteres e não conter números.");
                return;
            }
    
            System.out.print("Novo Endereço: ");
            String endereco = scanner.nextLine();
            if (endereco.length() > 50) {
                System.out.println("Endereço inválido.");
                return;
            }
    
            System.out.print("Novo Telefone: ");
            long telefone = scanner.nextLong();
            scanner.nextLine();
    
            System.out.print("Novo Sexo (F/M): ");
            String sexo = scanner.nextLine().toUpperCase();
            if (!sexo.matches("[FM]")) {
                System.out.println("Inválido.");
                return;
            }
    
            System.out.print("Novo Email: ");
            String email = scanner.nextLine();
            if (email.length() > 30) {
                System.out.println("Email inválido.");
                return;
            }
            Pessoas novaPessoa = new Pessoas(cpfPessoa, nome, endereco, telefone, sexo, email);
            pessoasDAO.editarPessoa(cpfPessoa, novaPessoa);
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    public void listarPessoas() {
        List<Pessoas> listaPessoas = pessoasDAO.listarPessoas();
        for (Pessoas pessoa : listaPessoas) {
            System.out.println(pessoa);
        }
    }

    public void excluirPessoa(Scanner scanner) {
        System.out.print("CPF: ");
        long cpfPessoa = scanner.nextLong();

        pessoasDAO.excluirPessoa(cpfPessoa);
    }
}