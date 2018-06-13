package com.br.linmv.sisbanco.controller;

import com.br.linmv.sisbanco.model.Conta;
import com.br.linmv.sisbanco.model.Extrato;
import com.br.linmv.sisbanco.model.Lancamentos;
import com.br.linmv.sisbanco.model.Listas;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Operacoes_Contas implements Operacoes, Operacoes_Bancarias {

    private Listas l = new Listas();

    @Override
    public void Inserir(Object objeto, List lista) {
        Conta c = (Conta) objeto;
        l.Inserir_Lista(c, lista);
    }

    @Override
    public void Remover(Object objeto, List Lista) {
        Lista.remove(objeto);
    }

    @Override
    public void Editar(Object objeto, List Lista, int Posicao) {
        Lista.set(Posicao, objeto);
    }

    @Override
    public boolean Validar_Inativo(Object objeto, List Lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Inativar(Object objeto, List Lista, int Posicao) {
        ((Conta) objeto).setInativo(true);
        Lista.set(Posicao, objeto);
    }

    @Override
    public double Consulta_Saldo(Conta Origem) {
        return Origem.getSaldo();
    }

    public int BuscarCod(List lista, int Cod) {
        for (int i = 0; i < lista.size(); i++) {
            Conta c = (Conta) lista.get(i);

            if (Cod == c.getCodigo()) {
                return i;
            }
        }
        return -1;
    }

    public Conta GetConta(List lista, int posicao) {
        return (Conta) lista.get(posicao);
    }

    @Override
    public void Depositar(Conta Origem, double vlr) {
        double SaldoAtual = Origem.getSaldo();
        Origem.setSaldo((SaldoAtual + vlr));
    }

    @Override
    public void Saque(Conta Origem, double vlr) {
        double SaldoAtual = Origem.getSaldo();
        Origem.setSaldo((SaldoAtual - vlr));
    }

    @Override
    public boolean Valida_Saldo(Conta Origem, double vrl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Transferir(double vlr, Conta Origem, Conta remetente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Extrato getExtrato(Conta Origem) {
        return Origem.getExtratos();    
    }
    
    public Extrato inserirLancamentos(String op, double vlr_op, double vlr_atual, Extrato ext){
        DateFormat fData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = new Date();
        String dtStr = fData.format(d);
        
        Lancamentos lanc = new Lancamentos(dtStr, op, vlr_op, vlr_atual);
        ext.Push(lanc);
        
        return ext;
    }
}
