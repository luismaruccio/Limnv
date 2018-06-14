package com.br.linmv.sisbanco.controller;

import java.util.List;

public interface Operacoes {
    public void Inserir(Object objeto, List Lista);
    public void Remover(Object objeto, List Lista);
    public void Editar(Object objeto, List Lista, int Posicao);
    public boolean Validar_Inativo(Object objeto);
    public void Inativar(Object objeto, List Lista, int Posicao);
    
}
