package com.br.linmv.sisbanco.controller;

import com.br.linmv.sisbanco.model.Conta;

public interface Operacoes_Bancarias {
    public double Consulta_Saldo();
    public void Depositar(double vlr);
    public void Saque(double vlr);
    public boolean Valida_Saldo(double vrl);
    public void Transferir(double vlr, Conta remetente);     
}
