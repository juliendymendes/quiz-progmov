package com.me.quiz.entidades;

public class QuizUsuario {
    private Usuario usuario;
    private Quiz quiz;

    public QuizUsuario(){}

    public QuizUsuario(Usuario u, Quiz q){
        this.usuario = u;
        this.quiz = q;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
