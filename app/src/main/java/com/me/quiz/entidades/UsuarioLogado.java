package com.me.quiz.entidades;

public class UsuarioLogado extends Usuario{
    private static Usuario instancia = null;

    public static void setInstancia(Usuario u){
        if(instancia == null){
            instancia = u;
        }
    }

    public static Usuario getInstancia(){
        return instancia;
    }




}
