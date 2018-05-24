/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.linmv.sisbanco.view;

import com.br.linmv.sisbanco.model.Cliente;

/**
 *
 * @author luism
 */
public interface CallBack {
    public void clienteCadastradoCall(Cliente cliente);
    public void clienteEditadoCall(Cliente cliente);
}
