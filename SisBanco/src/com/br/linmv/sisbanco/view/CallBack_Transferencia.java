package com.br.linmv.sisbanco.view;

import com.br.linmv.sisbanco.model.Conta;

public interface CallBack_Transferencia {
    public void operacaoEfetuadaCall(double vlr, Conta Destino);
    
}
