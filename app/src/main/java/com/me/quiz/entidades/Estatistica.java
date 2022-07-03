package com.me.quiz.entidades;

public class Estatistica {
    private String areaConhecimento;
    private int qtdAcertos;
    private Long tempo;

    public Estatistica(String areaConhecimento, int qtdAcertos, Long tempo) {
        this.areaConhecimento = areaConhecimento;
        this.qtdAcertos = qtdAcertos;
        this.tempo = tempo;
    }

    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public int getQtdAcertos() {
        return qtdAcertos;
    }

    public void setQtdAcertos(int qtdAcertos) {
        this.qtdAcertos = qtdAcertos;
    }

    public Long getTempo() {
        return tempo;
    }

    public void setTempo(Long tempo) {
        this.tempo = tempo;
    }
}
