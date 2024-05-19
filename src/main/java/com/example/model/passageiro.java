package com.example.model;

public class passageiro {
    private long cpf_passag;
    private String cartão_cred;
    private String bandeira_cartao;
    private String cidade_orig;


    public passageiro(long cpf_passag, String cartão_cred, String bandeira_cartao, String cidade_orig) {
        this.cpf_passag = cpf_passag;
        this.cartão_cred = cartão_cred;
        this.bandeira_cartao = bandeira_cartao;
        this.cidade_orig = cidade_orig;
    }



    @Override
    public String toString() {
        return "passageiro{" +
                "cpf_passag=" + cpf_passag +
                ", cartão_cred='" + cartão_cred + '\'' +
                ", bandeira_cartao='" + bandeira_cartao + '\'' +
                ", cidade_orig='" + cidade_orig + '\'' +
                '}';
    }

    public long getCpf_passag() {
        return cpf_passag;
    }

    public void setCpf_passag(int cpf_passag) {
        this.cpf_passag = cpf_passag;
    }

    public String getCartão_cred() {
        return cartão_cred;
    }

    public void setCartão_cred(String cartão_cred) {
        this.cartão_cred = cartão_cred;
    }

    public String getBandeira_cartao() {
        return bandeira_cartao;
    }

    public void setBandeira_cartao(String bandeira_cartao) {
        this.bandeira_cartao = bandeira_cartao;
    }

    public String getCidade_orig() {
        return cidade_orig;
    }

    public void setCidade_orig(String cidade_orig) {
        this.cidade_orig = cidade_orig;
    }


}
