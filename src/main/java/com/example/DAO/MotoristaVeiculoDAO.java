package com.example.DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.connection.ConnectionFactory;
import com.example.model.MotoristaVeiculo;
import com.example.model.Pessoas;

public class MotoristaVeiculoDAO {
    private Connection conexao;

    public MotoristaVeiculoDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MotoristaVeiculoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirMotoristaVeiculo(long cpf_motorista, String placa_veiculo) {
        String sql = "INSERT INTO motorista_veiculo(cpf_motorista, placa_veiculo) VALUES (?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_motorista);
            statement.setString(2, placa_veiculo);
            statement.executeUpdate();
            System.out.println("Motorista do veíulo inserido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir motorista.");
            e.printStackTrace();
        }
    }

    public void alterarMotoristaVeiculo(long novo_cpf, String nova_placa) {
        String sql = "UPDATE motorista_veiculo SET cpf_motorista = ?, placa_veiculo = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, novo_cpf);
            statement.setString(2, nova_placa);
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Motorista do veículo atualizado com sucesso!");
            } else {
                System.out.println("CPF do motorista não encontrado");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar motorista.");
            e.printStackTrace();
        }
    }

    public List<MotoristaVeiculo> listarMotoristaVeiculos() {
        List<MotoristaVeiculo> motoristaVeiculos = new ArrayList<>();
        String sql = "SELECT * FROM motorista_veiculo";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long cpf_motorista = resultSet.getLong("cpf_motorista");
                String placa_veiculo = resultSet.getString("placa_veiculo");
                MotoristaVeiculo motoristaVeiculo = new MotoristaVeiculo(cpf_motorista, placa_veiculo);
                motoristaVeiculos.add(motoristaVeiculo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar.");
            e.printStackTrace();
        }
        return motoristaVeiculos;
    }

    public void deletarMotoristaVeiculo(long cpf_motorista) {
        String sql = "DELETE FROM motorista_veiculo WHERE cpf_motorista = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setLong(1, cpf_motorista);
            int linhaAfetada = preparedStatement.executeUpdate();
            if(linhaAfetada > 0) {
                System.out.println("Exlusão do motorista concluída!");
            } else {
                System.out.println("O cpf: " + cpf_motorista + " não foi encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar excluir pessoa.");
            e.printStackTrace();
        }
    }

    public boolean verificarMotoristaVeiculo(long cpf_motorista) {
        String sql = "SELECT COUNT(*) FROM pessoas WHERE cpf_pessoa = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_motorista);
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
