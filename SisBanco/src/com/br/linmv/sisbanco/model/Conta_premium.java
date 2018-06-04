package com.br.linmv.sisbanco.model;

public class Conta_premium extends Conta {
    private String numero_cartao;

    public Conta_premium() {
        this.tipo = "Premium";
    }
    
    public String getNumero_cartao() {
        return numero_cartao;
    }

    public void setNumero_cartao(String numero_cartao) {
        this.numero_cartao = numero_cartao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
