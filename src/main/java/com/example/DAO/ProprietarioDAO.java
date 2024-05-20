package com.example.DAO;

import com.example.connection.ConnectionFactory;

import com.example.model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO {
    private Connection conexao;

    public ProprietarioDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProprietarioDAO(Connection conexao) {
        this.conexao = conexao;
    }
    public void inserirProprietario(long cpf_prop, String cnh_prop, int banco_prop, int agencia_prop, int conta_prop) {
        String sql = "INSERT INTO proprietarios (cpf_prop, cnh_prop, banco_prop, agencia_prop, conta_prop) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_prop);
            statement.setString(2, cnh_prop);
            statement.setInt(3, banco_prop);
            statement.setInt(4, agencia_prop);
            statement.setInt(5, conta_prop);
            statement.executeUpdate();
            System.out.println("Proprietario inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Proprietario:");
            e.printStackTrace();
        }
    }
    public List<Proprietario> listarPropritario() {
        List<Proprietario> propietarios = new ArrayList<>();
        String sql = "SELECT * FROM proprietarios";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long cpf_prop = resultSet.getLong("cpf_prop");
                String cnh_prop = resultSet.getString("cnh_prop");
                int banco_prop = resultSet.getInt("banco_prop");
                int agencia_prop = resultSet.getInt("agencia_prop");
                int conta_prop = resultSet.getInt("conta_prop");
                Proprietario proprietario = new Proprietario(cpf_prop,cnh_prop,banco_prop,agencia_prop,conta_prop);
                propietarios.add(proprietario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar :");
            e.printStackTrace();
        }
        return propietarios;
    }
    public void atualizarProprietario(long cpf_prop, String cnh_prop, int banco_prop, int agencia_prop, int conta_prop) {
        String sql = "UPDATE proprietarios SET cnh_prop = ?, banco_prop = ?, agencia_prop = ?, conta_prop = ? WHERE cpf_prop = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, cnh_prop);
            statement.setInt(2, banco_prop);
            statement.setInt(3, agencia_prop);
            statement.setInt(4, conta_prop);
            statement.setLong(5, cpf_prop);
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Proprietario atualizado com sucesso!");
            } else {
                System.out.println("Proprietario com o cpf: " + cpf_prop + " não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Proprietario:");
            e.printStackTrace();
        }
    }
    public void excluirProprietario(long cpf_prop) {
        String sql = "DELETE FROM proprietarios WHERE cpf_prop = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_prop);
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Proprietario excluido com sucesso!");
            } else {
                System.out.println("Proprietario com o cpf: " + cpf_prop + " não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Proprietario:");
            e.printStackTrace();
        }
    }
    public boolean existeProprietario(long cpf_prop) {
        String sql = "SELECT COUNT(*) AS total FROM proprietarios WHERE cpf_prop = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_prop);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int total = resultSet.getInt("total");
                    return total > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do Proprietario:");
            e.printStackTrace();
        }
        return false;
    }
}
