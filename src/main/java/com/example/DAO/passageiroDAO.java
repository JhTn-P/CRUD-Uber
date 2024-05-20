package com.example.DAO;

import com.example.connection.ConnectionFactory;
import com.example.model.passageiro;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassageiroDAO {
    private Connection conexao;

    public PassageiroDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PassageiroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirPassageiro(long cpf_passag, String cartao_cred, String bandeira_cartao, String cidade_orig) {
        String sql = "INSERT INTO passageiros (cpf_passag, cartao_cred, bandeira_cartao, cidade_orig) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_passag);
            statement.setString(2, cartao_cred);
            statement.setString(3, bandeira_cartao);
            statement.setString(4, cidade_orig);
            statement.executeUpdate();
            System.out.println("Passageiro inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir passageiro:");
            e.printStackTrace();
        }
    }

    public List<passageiro> listarPassageiro() {
        List<passageiro> passageiros = new ArrayList<>();
        String sql = "SELECT * FROM passageiros";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long cpf_passag = resultSet.getLong("cpf_passag");
                String cartao_cred = resultSet.getString("cartao_cred");
                String bandeira_cartao = resultSet.getString("bandeira_cartao");
                String cidade_orig = resultSet.getString("cidade_orig");

                passageiro passageiro = new passageiro(cpf_passag,cartao_cred,bandeira_cartao,cidade_orig);
                passageiros.add(passageiro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar :");
            e.printStackTrace();
        }
        return passageiros;
    }

    public void atualizarPassageiro(long cpf_passag, String cartao_cred, String bandeira_cartao, String cidade_orig) {
        String sql = "UPDATE passageiros SET cartao_cred = ?, bandeira_cartao = ?, cidade_orig = ? WHERE cpf_passag = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, cartao_cred);
            statement.setString(2, bandeira_cartao);
            statement.setString(3, cidade_orig);
            statement.setLong(4, cpf_passag);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Passageiro atualizado com sucesso!");
            } else {
                System.out.println("Passageiro com o cpf: " + cpf_passag + " não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar passageiro:");
            e.printStackTrace();
        }
    }

    public void excluirPassageiro(long cpf_passag) {
        String sql = "DELETE FROM passageiros WHERE cpf_passag = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_passag);
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Passageiro excluido com sucesso!");
            } else {
                System.out.println("Passageiro com o cpf: " + cpf_passag + " não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar passageiro:");
            e.printStackTrace();
        }
    }

    public boolean existePassageiro(long cpf_passag) {
        String sql = "SELECT COUNT(*) AS total FROM passageiros WHERE cpf_passag = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_passag);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int total = resultSet.getInt("total");
                    return total > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do passageiro:");
            e.printStackTrace();
        }
        return false;
    }

}
