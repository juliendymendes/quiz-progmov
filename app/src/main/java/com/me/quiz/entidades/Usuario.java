package com.me.quiz.entidades;



public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private int qtsAcertos;
    private byte[] foto = null;

    public Usuario(){}

    public Usuario( String nome, String email, String senha, int qtsAcertos) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

        //this.senha = Md5Hash.md5(senha);

        this.senha = senha;
    }

    public int getQtsAcertos() {
        return qtsAcertos;
    }

    public void setQtsAcertos(int qtsAcertos) {
        this.qtsAcertos += qtsAcertos;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "id= " + id + " - nome= " + nome;
    }
}
