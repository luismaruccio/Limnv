package com.br.linmv.sisbanco.model;

import javax.swing.JOptionPane;


public class Extrato {
    private Lancamentos Extrato[];
    private int Topo;
    private final int Maximo = 50;
    
    
    public Extrato(){
        this.Topo = 0;
        this.Extrato = new Lancamentos[Maximo];
    }
    
    public boolean Verifica_Vazia(){   
        return (this.Topo == 0);
    }
    
    public boolean Verifica_Cheia(){
        return (this.Topo == Maximo);
    }
    
    public void Push(Lancamentos lanc){
        
        if (Verifica_Cheia())
            JOptionPane.showMessageDialog(null, "O seu extrato atingiu o limite! \n \n Entre em contato com o gerente!", "Erro...", JOptionPane.ERROR_MESSAGE);
        else{
          this.Extrato[this.Topo] = lanc;
          this.Topo++;           
        }  
    }
    
    public Lancamentos Pop(){
        if (Verifica_Vazia()){
            JOptionPane.showMessageDialog(null, "Não há lançamentos!", "Erro...", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        else{
            this.Topo--;
            return this.Extrato[this.Topo];
        } 
    }
}

