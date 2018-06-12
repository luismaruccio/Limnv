package com.br.linmv.sisbanco.model;

import javax.swing.JOptionPane;


public class Extrato {
    private Object Extrato[];
    private int Topo;
    private final int Maximo = 50;
    
    
    public Extrato(){
        this.Topo = -1;
        this.Extrato = new Object[Maximo];
    }
    
    public boolean Verifica_Vazia(){   
        return (this.Topo == -1);
    }
    
    public boolean Verifica_Cheia(){
        return (this.Topo == Maximo);
    }
    
    public void Push(Object obj){
        
        if (Verifica_Cheia())
            JOptionPane.showMessageDialog(null, "O seu extrato atingiu o limite! \n \n Entre em contato com o gerente!", "Erro...", JOptionPane.ERROR_MESSAGE);
        else{
          this.Extrato[this.Topo] = obj;
          this.Topo++;           
        }  
    }
    
    public Object Pop(){
        if (Verifica_Vazia()){
            JOptionPane.showMessageDialog(null, "Não há lançamentos!", "Erro...", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        else{
            this.Topo--;
            return this.Extrato[this.Topo + 1];
        } 
    }
}

