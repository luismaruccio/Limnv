package com.br.linmv.sisbanco.controller;

import com.br.linmv.sisbanco.model.Cliente;
import com.br.linmv.sisbanco.model.Conta;
import com.br.linmv.sisbanco.model.Listas;
import java.util.ArrayList;
import java.util.List;

public class Operacoes_Clientes implements Operacoes, Operacoes_Bancarias{
     private Listas l = new Listas();

    @Override
    public void Inserir(Object objeto, List lista) {
        Cliente c = (Cliente)objeto;
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
        ((Cliente)objeto).setInativo(true);
        Lista.set(Posicao, objeto);        
    }

    @Override
    public double Consulta_Saldo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Depositar(double vlr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Saque(double vlr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Valida_Saldo(double vrl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Transferir(double vlr, Conta remetente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int BuscarCod(List lista, int Cod) {
         for(int i = 0; i < lista.size(); i++)
         {
             Cliente c = (Cliente)lista.get(i);
             
             if(Cod == c.getCodigo())
                 return i;
         }
        return -1;
    }
   
    public Cliente GetCliente(List lista, int posicao){
    
        return (Cliente)lista.get(posicao);
        
    }  
}
