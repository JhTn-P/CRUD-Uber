package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.connection.ConnectionFactory;
import com.example.model.Pessoas;
import com.example.model.TipoPgto;

public class TipoPagtoDAO {
    private Connection conexao;

    public TipoPagtoDAO(){
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TipoPagtoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirPagto(int cod_pagto, String desc_pagto){
        String sql = "INSERT INTO tipo_pgto (cod_pagto, desc_pagto) VALUES (?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, cod_pagto);
            statement.setString(2, desc_pagto);
            
            statement.executeUpdate();
            System.out.println("Dados cadastrados!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pagamento.");
            e.printStackTrace();
        }
    }

    public void editarPagto(int cod_pagto, String desc_pagto) {
        String sql = "UPDATE tipo_pgto SET desc_pagto = ? WHERE cod_pagto = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, desc_pagto);
            statement.setInt(2, cod_pagto);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Atualização concluída!");
            } else {
                System.out.println("O código: " + cod_pagto + " não foi encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro na atualização.");
            e.printStackTrace();
        }
    }

    public List<TipoPgto> listarPagamentos(){
        List<TipoPgto> listarPagamentos = new ArrayList<>();
        String sql = "SELECT * FROM tipo_pgto";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int cod_pagto = resultSet.getInt("cod_pagto");
                String desc_pagto = resultSet.getString("desc_pagto");
                TipoPgto tipoPgto = new TipoPgto(cod_pagto, desc_pagto);
                listarPagamentos.add(tipoPgto);
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao listar tabela.");
            e.printStackTrace();
        }
        return listarPagamentos;
    }

    public void excluirPagamento(int cod_pagto) {
        String sql = "DELETE FROM tipo_pgto WHERE cod_pagto = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, cod_pagto);
            int linhaAfetada = preparedStatement.executeUpdate();
            if(linhaAfetada > 0) {
                System.out.println("Exlusão de pessoa concluída!");
            } else {
                System.out.println("O cpf: " + cod_pagto + " não foi encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar excluir pessoa.");
            e.printStackTrace();
        }
    }

    public boolean verificarPagamento(long cod_pagto) {
        String sql = "SELECT COUNT(*) FROM tipo_pgto WHERE cod_pagto = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cod_pagto);
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
