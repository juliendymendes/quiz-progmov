package com.me.quiz.entidades;

public class Quiz {
    private int id;
    private String nomeQuiz;
    private int acertos;
    private Long tempo;

    public Quiz(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public Long getTempo() {
        return tempo;
    }

    public void setTempo(Long tempo) {
        this.tempo = tempo;
    }

    public Quiz(String nomeQuiz, int acertos, Long tempo) {

        this.nomeQuiz = nomeQuiz;
        this.acertos = acertos;
        this.tempo = tempo;
    }

    public String getNomeQuiz() {
        return nomeQuiz;
    }

    public void setNomeQuiz(String nomeQuiz) {
        this.nomeQuiz = nomeQuiz;
    }
}
