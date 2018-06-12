package com.br.linmv.sisbanco.model;

public class Lancamentos {
    private String data_hora;
    private String operacao;
    private double valor_operacao;
    private double saldo_final;

    public Lancamentos(String data_hora, String operacao, double valor_operacao, double saldo_final) {
        this.data_hora = data_hora;
        this.operacao = operacao;
        this.valor_operacao = valor_operacao;
        this.saldo_final = saldo_final;
    }
    
    public String getData_hora() {
        return data_hora;
    }

    public String getOperacao() {
        return operacao;
    }

    public double getValor_operacao() {
        return valor_operacao;
    }

    public double getSaldo_final() {
        return saldo_final;
    }
  
}
