package com.example.DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.example.connection.ConnectionFactory;
import com.example.model.Pessoas;

public class PessoasDAO {
    private Connection conexao;

    public PessoasDAO(){
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserirPessoa(Pessoas pessoas){
        String sql = "INSERT INTO pessoas (cpf_pessoa, nome, endereco, telefone, sexo, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setLong(1, pessoas.getCpf_pessoa());
            statement.setString(2, pessoas.getNome());
            statement.setString(3, pessoas.getEndereco());
            statement.setLong(4, pessoas.getTelefone());
            statement.setString(5, pessoas.getSexo());
            statement.setString(6, pessoas.getEmail());
            statement.executeUpdate();
            System.out.println("Dados cadastrados!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário.");
            e.printStackTrace();
        }
    }

    public void editarPessoa(long cpfPessoa, Pessoas novaPessoa) {
        String sql = "UPDATE pessoas SET nome = ?, endereco = ?, telefone = ?, sexo = ?, email = ? WHERE cpf_pessoa = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, novaPessoa.getNome());
            statement.setString(2, novaPessoa.getEndereco());
            statement.setLong(3, novaPessoa.getTelefone());
            statement.setString(4, novaPessoa.getSexo());
            statement.setString(5, novaPessoa.getEmail());
            statement.setLong(6, novaPessoa.getCpf_pessoa());
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Atualização concluída!");
            } else {
                System.out.println("O CPF: " + cpfPessoa + " não foi encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro na atualização.");
            e.printStackTrace();
        }
    }

    public List<Pessoas> listarPessoas(){
        List<Pessoas> listarPessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long cpf_pessoa = resultSet.getLong("cpf_pessoa");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                long telefone = resultSet.getLong("telefone");
                String sexo = resultSet.getString("sexo");
                String email = resultSet.getString("email");
                Pessoas pessoas = new Pessoas(cpf_pessoa, nome, endereco, telefone, sexo, email);
                listarPessoas.add(pessoas);
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao listar tabela.");
            e.printStackTrace();
        }
        return listarPessoas;
    }

    public void excluirPessoa(String cpf_pessoa) {
        String sql = "DELETE FROM pessoas WHERE cpf_pessoa = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf_pessoa);
            int linhaAfetada = preparedStatement.executeUpdate();
            if(linhaAfetada > 0) {
                System.out.println("Exlusão de pessoa concluída!");
            } else {
                System.out.println("O cpf: " + cpf_pessoa + " não foi encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar excluir pessoa.");
            e.printStackTrace();
        }
    }
}