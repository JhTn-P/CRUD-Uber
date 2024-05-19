package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.connection.ConnectionFactory;
import com.example.model.Motoristas;

public class MotoristasDAO {
    private Connection conexao;

    public MotoristasDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public MotoristasDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para inserir um motorista
    public void inserirMotorista(Motoristas motorista) {
        String sql = "INSERT INTO motoristas (cpf_motorista, cnh, banco_mot, agencia_mot, conta_mot) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, motorista.getCpf_motorista());
            statement.setString(2, motorista.getCnh());
            statement.setInt(3, motorista.getBanco_mot());
            statement.setInt(4, motorista.getAgencia_mot());
            statement.setInt(5, motorista.getConta_mot());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir motorista:");
            e.printStackTrace();
        }
    }

    // Método para alterar um motorista
    public void alterarMotorista(long cpfMotorista, Motoristas motoristaAtualizado) {
        String sql = "UPDATE motoristas SET cnh = ?, banco_mot = ?, agencia_mot = ?, conta_mot = ? WHERE cpf_motorista = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, motoristaAtualizado.getCnh());
            statement.setInt(2, motoristaAtualizado.getBanco_mot());
            statement.setInt(3, motoristaAtualizado.getAgencia_mot());
            statement.setInt(4, motoristaAtualizado.getConta_mot());
            statement.setLong(5, cpfMotorista);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar motorista:");
            e.printStackTrace();
        }
    }

    // Método para listar todos os motoristas
    public List<Motoristas> listarMotoristas() {
        List<Motoristas> motoristas = new ArrayList<>();
        String sql = "SELECT * FROM motoristas";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long cpfMotorista = resultSet.getLong("cpf_motorista");
                String cnh = resultSet.getString("cnh");
                int bancoMot = resultSet.getInt("banco_mot");
                int agenciaMot = resultSet.getInt("agencia_mot");
                int contaMot = resultSet.getInt("conta_mot");

                Motoristas motorista = new Motoristas(cpfMotorista, cnh, bancoMot, agenciaMot, contaMot);
                motoristas.add(motorista);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar motoristas:");
            e.printStackTrace();
        }
        return motoristas;
    }

    // Método para deletar um motorista
    public void deletarMotorista(long cpfMotorista) {
        String sql = "DELETE FROM motoristas WHERE cpf_motorista = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpfMotorista);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar motorista:");
            e.printStackTrace();
        }
    }

    // Método para verificar se um motorista existe pelo CPF
    public boolean verificarMotorista(long cpfMotorista) {
        String sql = "SELECT COUNT(*) FROM motoristas WHERE cpf_motorista = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpfMotorista);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}