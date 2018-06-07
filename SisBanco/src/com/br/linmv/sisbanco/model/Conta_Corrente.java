package com.br.linmv.sisbanco.model;

public class Conta_Corrente extends Conta {
    private double taxa_transacao = 0.8;
    private int op_gratuitas = 10;

    public Conta_Corrente() {
        this.tipo = "Corrente";
        setCodigo(incremento++);
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void setCodigo(int codigo) {
        super.setCodigo(codigo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCodigo() {
        return super.getCodigo(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
