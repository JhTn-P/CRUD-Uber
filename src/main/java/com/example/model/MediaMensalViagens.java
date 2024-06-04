package com.example.model;

public class MediaMensalViagens {

    private int ano;
    private int mes;
    private String sexo;
    private int totalViagens;
    private double mediaViagens;
    
    public MediaMensalViagens() {
    }

    public MediaMensalViagens(int ano, int mes, String sexo, int totalViagens, double mediaViagens) {
        this.ano = ano;
        this.mes = mes;
        this.sexo = sexo;
        this.totalViagens = totalViagens;
        this.mediaViagens = mediaViagens;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTotalViagens() {
        return totalViagens;
    }

    public void setTotalViagens(int totalViagens) {
        this.totalViagens = totalViagens;
    }

    public double getMediaViagens() {
        return mediaViagens;
    }

    public void setMediaViagens(double mediaViagens) {
        this.mediaViagens = mediaViagens;
    }

    @Override
    public String toString() {
        return "Ano: " + ano + ", Mês: " + mes + ", Sexo: " + sexo + ", Total de Viagens: " + totalViagens + ", Média de Viagens: " + mediaViagens;
    }
    
    
}
