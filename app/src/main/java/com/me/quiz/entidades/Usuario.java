package com.me.quiz.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;

    public String email;

    public String senha;

    @ColumnInfo( name = "qtd_acertos")
    public int qtdAcertos;

    public Usuario(String nome, String email, String senha, int qtdAcertos) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.qtdAcertos = qtdAcertos;
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
        this.senha = senha;
    }

    public int getQtdAcertos() {
        return qtdAcertos;
    }

    public void setQtdAcertos(int qtdAcertos) {
        this.qtdAcertos = qtdAcertos;
    }

    @Override
    public String toString() {
        return "id=" + id + "- nome= " + nome;
    }
}
