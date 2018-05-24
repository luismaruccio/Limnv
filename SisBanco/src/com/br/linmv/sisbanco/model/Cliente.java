package com.br.linmv.sisbanco.model;

public class Cliente {
    private static int seq = 0;
    private int codigo;
    private String nome;
    private String cpf;
    private String senha;
    private boolean inativo;
    private Conta contas;
    
    public Cliente(String Nome, String Cpf, String Senha){
            this.inativo = false;
            this.codigo = seq++;
            this.cpf = Cpf;
            this.nome = Nome;
            this.senha = Senha;
    }

    public static int getSeq() {
        return seq;
    }

    public static void setSeq(int seq) {
        Cliente.seq = seq;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public Conta getContas() {
        return contas;
    }

    public void setContas(Conta contas) {
        this.contas = contas;
    }
}
