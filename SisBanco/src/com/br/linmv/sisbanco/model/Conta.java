package com.br.linmv.sisbanco.model;

public class Conta {
    protected static int incremento;
    private int codigo;

    public static int getIncremento() {
        return incremento;
    }

    public static void setIncremento(int incremento) {
        Conta.incremento = incremento;
    }
    private double saldo;
    private boolean inativo;
    protected String tipo;

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



