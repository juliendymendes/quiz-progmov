package com.me.quiz.entidades;

import com.me.quiz.utils.Md5Hash;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private int qtsAcertos;

    public Usuario(){}

    public Usuario( String nome, String email, String senha, int qtsAcertos) {
        this.nome = nome;
        this.email = email;
        this.senha = Md5Hash.md5(senha);
        this.qtsAcertos = qtsAcertos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = Md5Hash.md5(senha);
    }

    public int getQtsAcertos() {
        return qtsAcertos;
    }

    public void setQtsAcertos(int qtsAcertos) {
        this.qtsAcertos = qtsAcertos;
    }

    @Override
    public String toString() {
        return "id= " + id + " - nome= " + nome;
    }
}
