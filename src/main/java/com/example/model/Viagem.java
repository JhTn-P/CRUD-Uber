package com.example.model;

import java.sql.Date;

import javax.xml.crypto.Data;

public class Viagem {
    private long cpf_pass_viag;
    private long cpf_mot_viag;
    private String placa_veic_viag;
    private String local_orig_viag;
    private String local_dest_viag;
    private Date dt_hora_inicio;
    private Date dt_hora_fim;
    private int qtde_pass;
    private String forma_pagto;
    private double valor_pagto;
    private String cancelam_mot;
    private String cancelam_pass;
    
    public Viagem() {
    }

    public Viagem(long cpf_pass_viag, long cpf_mot_viag, String placa_veic_viag, String local_orig_viag,
            String local_dest_viag, Date dt_hora_inicio, Date dt_hora_fim, int qtde_pass, String forma_pagto,
            double valor_pagto, String cancelam_mot, String cancelam_pass) {
        this.cpf_pass_viag = cpf_pass_viag;
        this.cpf_mot_viag = cpf_mot_viag;
        this.placa_veic_viag = placa_veic_viag;
        this.local_orig_viag = local_orig_viag;
        this.local_dest_viag = local_dest_viag;
        this.dt_hora_inicio = dt_hora_inicio;
        this.dt_hora_fim = dt_hora_fim;
        this.qtde_pass = qtde_pass;
        this.forma_pagto = forma_pagto;
        this.valor_pagto = valor_pagto;
        this.cancelam_mot = cancelam_mot;
        this.cancelam_pass = cancelam_pass;
    }

    @Override
    public String toString() {
        return "Dados da viagem: " +
        "cpf passageiro: " + cpf_pass_viag +
        ", cpf motorista: " + cpf_mot_viag +
        ", placa do carro: " + placa_veic_viag +
        ", origem: " + local_orig_viag + 
        ", destino: " + local_dest_viag +
        ", hora início: " + dt_hora_inicio +
        ", hora fim: " + dt_hora_fim +
        ", quantidade de passageiros: " + qtde_pass +
        ", forma de pagamento: " + forma_pagto +
        ", valor do pagamento: " + valor_pagto +
        ", cancelamento da viagem pelo motorista: " + cancelam_mot +
        ", cancelamento da viagem pelo passageiro: " + cancelam_pass; 
    }

    public long getCpf_pass_viag() {
        return cpf_pass_viag;
    }

    public void setCpf_pass_viag(long cpf_pass_viag) {
        this.cpf_pass_viag = cpf_pass_viag;
    }

    public long getCpf_mot_viag() {
        return cpf_mot_viag;
    }

    public void setCpf_mot_viag(long cpf_mot_viag) {
        this.cpf_mot_viag = cpf_mot_viag;
    }

    public String getPlaca_veic_viag() {
        return placa_veic_viag;
    }

    public void setPlaca_veic_viag(String placa_veic_viag) {
        this.placa_veic_viag = placa_veic_viag;
    }

    public String getLocal_orig_viag() {
        return local_orig_viag;
    }

    public void setLocal_orig_viag(String local_orig_viag) {
        this.local_orig_viag = local_orig_viag;
    }

    public String getLocal_dest_viag() {
        return local_dest_viag;
    }

    public void setLocal_dest_viag(String local_dest_viag) {
        this.local_dest_viag = local_dest_viag;
    }

    public Date getDt_hora_inicio() {
        return dt_hora_inicio;
    }

    public void setDt_hora_inicio(Date dt_hora_inicio) {
        this.dt_hora_inicio = dt_hora_inicio;
    }

    public Date getDt_hora_fim() {
        return dt_hora_fim;
    }

    public void setDt_hora_fim(Date dt_hora_fim) {
        this.dt_hora_fim = dt_hora_fim;
    }

    public int getQtde_pass() {
        return qtde_pass;
    }

    public void setQtde_pass(int qtde_pass) {
        this.qtde_pass = qtde_pass;
    }

    public String getForma_pagto() {
        return forma_pagto;
    }

    public void setForma_pagto(String forma_pagto) {
        this.forma_pagto = forma_pagto;
    }

    public double getValor_pagto() {
        return valor_pagto;
    }

    public void setValor_pagto(double valor_pagto) {
        this.valor_pagto = valor_pagto;
    }

    public String getCancelam_mot() {
        return cancelam_mot;
    }

    public void setCancelam_mot(String cancelam_mot) {
        this.cancelam_mot = cancelam_mot;
    }

    public String getCancelam_pass() {
        return cancelam_pass;
    }

    public void setCancelam_pass(String cancelam_pass) {
        this.cancelam_pass = cancelam_pass;
    }
    
}