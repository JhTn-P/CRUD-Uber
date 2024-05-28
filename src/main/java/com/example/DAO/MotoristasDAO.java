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

     // Construtor que recebe uma conexão como parâmetro
    public MotoristasDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Construtor padrão que utiliza a ConnectionFactory para obter uma conexão
    public MotoristasDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para inserir um motorista
    public void inserirMotorista(Motoristas motorista) {
        // Definição da query SQL para inserção de dados na tabela 'motoristas'
        String sql = "INSERT INTO motoristas (cpf_motorista, cnh, banco_mot, agencia_mot, conta_mot) VALUES (?, ?, ?, ?, ?)";
        // PrepareStatement são métodos utizados para preparar a query SQL para ser executada
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            /* Preenchimento dos parâmetros da query com os valores do objeto Motoristas recebido como argumento
            Cada setLong, setString... é utilizado para atribuir valor ao parâmetro da query, cada um correspondendo a um placeholder '?' na query, sendo preenchido na mesma oredem em que foram definidos.
            Por exemplo nesse método inserirMotorista é declarado (cpf_motorista, cnh, banco_mot...) e logo em seguida o placeholder '?' na mesma quantidade de atributos que foram criados. Quando for atribuir valores a query utilizando o setX é necessário colocar na mesma ordem.
            */
            statement.setLong(1, motorista.getCpf_motorista());
            statement.setString(2, motorista.getCnh());
            statement.setInt(3, motorista.getBanco_mot());
            statement.setInt(4, motorista.getAgencia_mot());
            statement.setInt(5, motorista.getConta_mot());
            statement.executeUpdate(); // Executa a inserção ou o método desajado (update, delete)
        } catch (SQLException e) {
            System.out.println("Erro ao inserir motorista:");
            e.printStackTrace();
        }
    }

    // Método para alterar um motorista
    public void alterarMotorista(long cpfMotorista, Motoristas motoristaAtualizado) {
         // Definição da query SQL para atualização dos dados do motorista com base no CPF
        String sql = "UPDATE motoristas SET cnh = ?, banco_mot = ?, agencia_mot = ?, conta_mot = ? WHERE cpf_motorista = ?";
        // PrepareStatement são métodos utizados para preparar a query SQL para ser executada
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            // Preenchimento dos parâmetros da query com os novos valores do motorista
            statement.setString(1, motoristaAtualizado.getCnh());
            statement.setInt(2, motoristaAtualizado.getBanco_mot());
            statement.setInt(3, motoristaAtualizado.getAgencia_mot());
            statement.setInt(4, motoristaAtualizado.getConta_mot());
            // Definição do valor do parâmetro WHERE da query, que é o CPF do motorista a ser alterado
            statement.setLong(5, cpfMotorista);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar motorista:");
            e.printStackTrace();
        }
    }

    // Método para listar todos os motoristas
    public List<Motoristas> listarMotoristas() {
        // Lista que armazenará os objetos Motoristas recuperados do banco de dados
        List<Motoristas> motoristas = new ArrayList<>();
        // Query SQL para selecionar todos os registros da tabela 'motoristas'
        String sql = "SELECT * FROM motoristas";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            // Execução da query e obtenção do resultado como um conjunto de resultados (ResultSet)

            // Iteração sobre os resultados obtidos
            while (resultSet.next()) {
                // Extração dos dados de cada linha do resultado
                long cpfMotorista = resultSet.getLong("cpf_motorista");
                String cnh = resultSet.getString("cnh");
                int bancoMot = resultSet.getInt("banco_mot");
                int agenciaMot = resultSet.getInt("agencia_mot");
                int contaMot = resultSet.getInt("conta_mot");

                // Criação de um objeto Motoristas com os dados extraídos e adicionado à lista de motoristas
                Motoristas motorista = new Motoristas(cpfMotorista, cnh, bancoMot, agenciaMot, contaMot);
                motoristas.add(motorista);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar motoristas:");
            e.printStackTrace();
        }

        // Retorna a lista de motorista
        return motoristas;
    }

    // Método para deletar um motorista
    public void deletarMotorista(long cpfMotorista) {
        // Definição da query SQL para deletar motorista cadastrado para na tabela
        String sql = "DELETE FROM motoristas WHERE cpf_motorista = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            // Definição do valor do parâmetro da query, no caso a chave primária
            statement.setLong(1, cpfMotorista);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar motorista:");
            e.printStackTrace();
        }
    }

    // Método para verificar se um motorista existe pelo CPF
    public boolean verificarMotorista(long cpfMotorista) {
        // Query SQL para contar o número de registros na tabela 'motoristas' com o CPF especificado
        String sql = "SELECT COUNT(*) FROM motoristas WHERE cpf_motorista = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            // Definição do valor do parâmetro da query como o CPF do motorista a ser verificado
            statement.setLong(1, cpfMotorista);
            // Execução da query e obtem o resultado como um conjunto de resultados (ResultSet)
            try (ResultSet resultSet = statement.executeQuery()) {
                // Verifica se há pelo menos um resultado no ResultSet, no caso é para ter 1 ou 0 resultados pois é uma chave primária como parâmetro
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