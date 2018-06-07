package com.br.linmv.sisbanco.model;

public class Conta_Poupanca extends Conta{
    public double taxa_rendimento = 0.5;

    public Conta_Poupanca() {
        this.tipo = "Poupança";
        setCodigo(incremento++);
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
    public int getCodigo() {
        return super.getCodigo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTipo(String tipo) {
        super.setTipo(tipo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTipo() {
        return super.getTipo(); //To change body of generated methods, choose Tools | Templates.
    }
    
         
}


