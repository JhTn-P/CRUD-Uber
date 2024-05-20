package com.example.model;

public class TipoPgto {
    private int cod_pagto;
    private String desc_pagto;
    
    public TipoPgto() {
    }

    public TipoPgto(int cod_pagto, String desc_pagto) {
        this.cod_pagto = cod_pagto;
        this.desc_pagto = desc_pagto;
    }

    @Override
    public String toString() {
        return "Dados pagamentos:" + 
        "Código: " + cod_pagto + 
        ", descrição: " + desc_pagto;
    }

    public int getCod_pagto() {
        return cod_pagto;
    }

    public void setCod_pagto(int cod_pagto) {
        this.cod_pagto = cod_pagto;
    }

    public String getDesc_pagto() {
        return desc_pagto;
    }

    public void setDesc_pagto(String desc_pagto) {
        this.desc_pagto = desc_pagto;
    }
}
