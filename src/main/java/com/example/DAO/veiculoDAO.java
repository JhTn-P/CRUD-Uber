package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.connection.ConnectionFactory;
import com.example.model.Veiculo;

public class VeiculoDAO {
    private Connection conexao;

    public VeiculoDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirVeiculo(String placa, String marca, String modelo, int ano_fabric, int capacidade_pass,
            String cor, String tipo_combust, int potencia_motor) {
        String sql = "INSERT INTO veiculos (placa, marca, modelo, ano_fabric, capacidade_pass, cor, tipo_combust, potencia_motor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, placa);
            statement.setString(2, marca);
            statement.setString(3, modelo);
            statement.setInt(4, ano_fabric);
            statement.setInt(5, capacidade_pass);
            statement.setString(6, cor);
            statement.setString(7, tipo_combust);
            statement.setInt(8, potencia_motor);
            statement.executeUpdate();
            System.out.println("Veículo inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir veículo:");
            e.printStackTrace();
        }
    }

    public List<Veiculo> listarVeiculos() {
        List<Veiculo> listarVeiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String placa = resultSet.getString("placa");
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                int ano_fabric = resultSet.getInt("ano_fabric");
                int capacidade_pass = resultSet.getInt("capacidade_pass");
                String cor = resultSet.getString("cor");
                String tipo_combust = resultSet.getString("tipo_combust");
                int potencia_motor = resultSet.getInt("potencia_motor");
                Veiculo veiculo = new Veiculo(placa, marca, modelo, ano_fabric, capacidade_pass, cor, tipo_combust,
                        potencia_motor);
                listarVeiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos:");
            e.printStackTrace();
        }
        return listarVeiculos;
    }

    public void atualizarVeiculo(String placa, String nova_marca, String novo_modelo, int novo_ano_fabric,
            int nova_capacidade_pass, String nova_cor, String novo_tipo_combust, int nova_potencia_motor) {
        String sql = "UPDATE veiculos SET marca = ?, modelo = ?, ano_fabric = ?, capacidade_pass = ?, cor = ?, tipo_combust = ?, potencia_motor = ? WHERE placa = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, nova_marca);
            statement.setString(2, novo_modelo);
            statement.setInt(3, novo_ano_fabric);
            statement.setInt(4, nova_capacidade_pass);
            statement.setString(5, nova_cor);
            statement.setString(6, novo_tipo_combust);
            statement.setInt(7, nova_potencia_motor);
            statement.setString(8, placa);
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Veículo atualizado com sucesso!");
            } else {
                System.out.println("Veículo com placa " + placa + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo:");
            e.printStackTrace();
        }
    }

    public void excluirVeiculo(String placa) {
        String sql = "DELETE FROM veiculos WHERE placa = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, placa);
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Veículo excluído com sucesso!");
            } else {
                System.out.println("Veículo com placa " + placa + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir veículo:");
            e.printStackTrace();
        }
    }

    public boolean existeVeiculo(String placa) {
        String sql = "SELECT COUNT(*) AS total FROM veiculos WHERE placa = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, placa);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int total = resultSet.getInt("total");
                    return total > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do veículo:");
            e.printStackTrace();
        }
        return false;
    }
}
