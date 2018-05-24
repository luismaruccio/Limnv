package com.br.linmv.sisbanco.model;

import java.util.List;

public class Listas {
           
    public void Inserir_Lista(Object objeto, List Lista){
        Lista.add(objeto);        
    }
    
    public Object Get_Object(int i, List Lista){
       return Lista.get(i); 
    }
    
    public int Get_Tamanho_Lista(List Lista){
        return Lista.size();
    }
    
    
}
