package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Definição das constantes para a URL do banco de dados, usuário e senha
    private static final String URL = "jdbc:postgresql://localhost:5432/CMP1611-Aline-Renan-Jhonatan";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    // Método estático que retorna uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
         // DriverManager.getConnection() tenta estabelecer uma conexão com o banco usando as credenciais fornecidas
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Teste de conexão com o banco de dados.
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } else {
                System.out.println("Falha ao estabelecer conexão com o banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao tentar conectar ao banco de dados:");
            e.printStackTrace();
        }
    }
}