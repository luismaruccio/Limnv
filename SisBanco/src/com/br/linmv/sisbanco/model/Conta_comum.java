package com.br.linmv.sisbanco.model;

public class Conta_comum extends Conta{
    private double limite_saque;

    public double getLimite_saque() {
        return limite_saque;
    }

    public void setLimite_saque(double limite_saque) {
        this.limite_saque = limite_saque;
    }

    @Override
    public void setInativo(boolean inativo) {
        super.setInativo(inativo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInativo() {
        return super.isInativo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSaldo(double saldo) {
        super.setSaldo(saldo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getSaldo() {
        return super.getSaldo(); //To change body of generated methods, choose Tools | Templates.
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


