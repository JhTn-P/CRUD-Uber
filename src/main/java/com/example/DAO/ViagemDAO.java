package com.example.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.connection.ConnectionFactory;
import com.example.model.Faturamento;
import com.example.model.Motoristas;
import com.example.model.Veiculo;
import com.example.model.Viagem;
import com.example.model.ViagemDetalhes;
import com.example.model.passageiro;

public class ViagemDAO {
    private Connection conexao;

    public ViagemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public ViagemDAO() {
        try {
            this.conexao = ConnectionFactory.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserirViagem(long cpf_pass_viag, long cpf_mot_viag, String placa_veic_viag, String local_orig_viag,
            String local_dest_viag, Date dt_hora_inicio, Date dt_hora_fim, int qtde_pass, String forma_pgta,
            double valor_pgta, String cancelam_mot, String cancelam_pass) {
        String sql = "INSERT INTO viagem (cpf_pass_viag, cpf_mot_viag, placa_veic_viag, local_orig_viag, local_dest_viag, dt_hora_inicio, dt_hora_fim, qtde_pass, forma_pgta, valor_pgta, cancelam_mot,cancelam_pass) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_pass_viag);
            statement.setLong(2, cpf_mot_viag);
            statement.setString(3, placa_veic_viag);
            statement.setString(4, local_orig_viag);
            statement.setString(5, local_dest_viag);
            statement.setDate(6, dt_hora_inicio);
            statement.setDate(7, dt_hora_fim);
            statement.setInt(8, qtde_pass);
            statement.setString(9, forma_pgta);
            statement.setDouble(10, valor_pgta);
            statement.setString(11, cancelam_mot);
            statement.setString(12, cancelam_pass);
            statement.executeUpdate();
            System.out.println("Viagem inserida com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir motorista:");
            e.printStackTrace();
        }
    }

    public List<Viagem> listarViagem() {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT * FROM viagem";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long cpf_pass_viag = resultSet.getLong("cpf_pass_viag");
                long cpf_mot_viag = resultSet.getLong("cpf_mot_viag");
                String placa_veic_viag = resultSet.getString("placa_veic_viag");
                String local_orig_viag = resultSet.getString("local_orig_viag");
                String local_dest_viag = resultSet.getString("local_dest_viag");
                Date dt_hora_inicio = resultSet.getDate("dt_hora_inicio");
                Date dt_hora_fim = resultSet.getDate("dt_hora_fim");
                Integer qtde_pass = resultSet.getInt("qtde_pass");
                String forma_pgta = resultSet.getString("forma_pgta");
                Double valor_pgta = resultSet.getDouble("valor_pgta");
                String cancelam_mot = resultSet.getString("cancelam_mot");
                String cancelam_pass = resultSet.getString("cancelam_pass");

                Viagem viagem = new Viagem(cpf_pass_viag, cpf_mot_viag, placa_veic_viag, local_orig_viag,
                        local_dest_viag, dt_hora_inicio, dt_hora_fim, qtde_pass, forma_pgta, valor_pgta, cancelam_mot,
                        cancelam_pass);
                viagens.add(viagem);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar :");
            e.printStackTrace();
        }
        return viagens;
    }

    public void alterarViagem(long cpf_pass_viag, long cpf_mot_viag, String placa_veic_viag, String local_orig_viag,
            String local_dest_viag, Date dt_hora_inicio, Date dt_hora_fim, int qtde_pass, String forma_pgta,
            double valor_pgta, String cancelam_mot, String cancelam_pass) {
        String sql = "UPDATE viagem SET local_orig_viag = ?, local_dest_viag = ?, dt_hora_fim = ?, qtde_pass = ?, forma_pgta = ?, valor_pgta = ?, cancelam_mot = ?, cancelam_pass = ? WHERE cpf_pass_viag = ? AND cpf_mot_viag = ? AND placa_veic_viag = ? AND dt_hora_inicio = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, local_orig_viag);
            statement.setString(2, local_dest_viag);
            statement.setDate(3, dt_hora_fim);
            statement.setInt(4, qtde_pass);
            statement.setString(5, forma_pgta);
            statement.setDouble(6, valor_pgta);
            statement.setString(7, cancelam_mot);
            statement.setString(8, cancelam_pass);
            statement.setLong(9, cpf_pass_viag);
            statement.setLong(10, cpf_mot_viag);
            statement.setString(11, placa_veic_viag);
            statement.setDate(12, dt_hora_inicio);

            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Viagem atualizada com sucesso!");
            } else {
                System.out.println("Viagem não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar viagem:");
            e.printStackTrace();
        }
    }

    public void excluirViagem(long cpf_pass_viag, long cpf_mot_viag, String placa_veic_viag, Date dt_hora_inicio) {
        String sql = "DELETE FROM viagem WHERE cpf_pass_viag = ? AND cpf_mot_viag = ? AND placa_veic_viag = ? AND dt_hora_inicio = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_pass_viag);
            statement.setLong(2, cpf_mot_viag);
            statement.setString(3, placa_veic_viag);
            statement.setDate(4, dt_hora_inicio);
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Passageiro excluido com sucesso!");
            } else {
                System.out.println("Passageiro com o cpf: " + cpf_pass_viag + " não foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do passageiro:");
            e.printStackTrace();
        }
    }

    public boolean existeViagem(long cpf_pass_viag, long cpf_mot_viag, String placa_veic_viag, Date dt_hora_inicio) {
        String sql = "SELECT COUNT(*) AS total FROM viagem WHERE cpf_pass_viag = ? AND cpf_mot_viag = ? AND placa_veic_viag = ? AND dt_hora_incio = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, cpf_pass_viag);
            statement.setLong(2, cpf_mot_viag);
            statement.setString(3, placa_veic_viag);
            statement.setDate(4, dt_hora_inicio);
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

    public List<ViagemDetalhes> buscarViagensPorMarcaEData(String marca, LocalDate data) {
        List<ViagemDetalhes> viagens = new ArrayList<>();
        String sql = "SELECT v.marca, v.placa, vg.local_orig_viag, vg.local_dest_viag, p1.nome AS nome_motorista, p2.nome AS nome_passageiro "
                +
                "FROM veiculos v " +
                "JOIN viagem vg ON v.placa = vg.placa_veic_viag " +
                "JOIN motoristas m ON vg.cpf_mot_viag = m.cpf_motorista " +
                "JOIN pessoas p1 ON m.cpf_motorista = p1.cpf_pessoa " +
                "JOIN passageiros p ON vg.cpf_pass_viag = p.cpf_passag " +
                "JOIN pessoas p2 ON p.cpf_passag = p2.cpf_pessoa " +
                "WHERE v.marca = ? AND vg.dt_hora_inicio = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, marca);
            stmt.setObject(2, data);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ViagemDetalhes viagem = new ViagemDetalhes();
                viagem.setMarca(rs.getString("marca"));
                viagem.setPlaca(rs.getString("placa"));
                viagem.setLocalOrigem(rs.getString("local_orig_viag"));
                viagem.setLocalDestino(rs.getString("local_dest_viag"));
                viagem.setNomeMotorista(rs.getString("nome_motorista"));
                viagem.setNomePassageiro(rs.getString("nome_passageiro"));
                viagens.add(viagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viagens;
    }

    public List<Faturamento> buscarMaioresFaturamentosPorMes(int ano, int mes) {
        List<Faturamento> viagens = new ArrayList<>();
        String dataInicio = String.format("%04d-%02d-01", ano, mes); // Primeiro dia do mês
        String sql = "SELECT v.marca, v.placa, SUM(vg.valor_pgta) AS total_valor_pgta " +
                "FROM veiculos v " +
                "JOIN viagem vg ON v.placa = vg.placa_veic_viag " +
                "JOIN motoristas m ON vg.cpf_mot_viag = m.cpf_motorista " +
                "JOIN pessoas p1 ON m.cpf_motorista = p1.cpf_pessoa " +
                "JOIN passageiros p ON vg.cpf_pass_viag = p.cpf_passag " +
                "JOIN pessoas p2 ON p.cpf_passag = p2.cpf_pessoa " +
                "WHERE vg.dt_hora_inicio >= ?::DATE " +
                "  AND vg.dt_hora_inicio < (?::DATE + INTERVAL '1 MONTH') " +
                "GROUP BY v.marca, v.placa " +
                "ORDER BY total_valor_pgta DESC " +
                "LIMIT 20";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setObject(1, dataInicio);
            stmt.setObject(2, dataInicio);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Faturamento viagem = new Faturamento();
                viagem.setMarca(rs.getString("marca"));
                viagem.setPlaca(rs.getString("placa"));
                viagem.setFaturamento(rs.getDouble("total_valor_pgta"));

                viagens.add(viagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viagens;
    }
}