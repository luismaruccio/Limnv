package com.br.linmv.sisbanco.model;

public class Conta {
    private int codigo;
    private double saldo;
    private boolean inativo;
    protected String tipo;
    protected Extrato extratos;

    public Extrato getExtratos() {
        return extratos;
    }

    public void setExtratos(Extrato extratos) {
        this.extratos = extratos;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
    
    
}



