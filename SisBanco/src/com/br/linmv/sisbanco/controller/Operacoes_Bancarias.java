package com.br.linmv.sisbanco.controller;

import com.br.linmv.sisbanco.model.Conta;

public interface Operacoes_Bancarias {
    public double Consulta_Saldo(Conta Origem);
    public void Depositar(Conta Origem, double vlr);
    public void Saque(Conta Origem, double vlr);
    public boolean Valida_Saldo(Conta Origem, double vrl);
    public void Transferir(double vlr,Conta Origem, Conta remetente);     
}
