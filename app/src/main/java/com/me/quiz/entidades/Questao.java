package com.me.quiz.entidades;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Questao {

    @SerializedName("category")
    private String categoria;
    @SerializedName("id")
    private String id;
    @SerializedName("correctAnswer")
    private String respostaCorreta;
    @SerializedName("incorrectAnswer")
    private ArrayList<String> respostasIncorretas;
    @SerializedName("question")
    private String questao;
    @SerializedName("tags")
    private ArrayList<String> tags;
    @SerializedName("type")
    private String tipo;
    @SerializedName("difficulty")
    private String dificuldade;

    public Questao(String categoria, String id, String respostaCorreta,
                   ArrayList<String> respostasIncorretas, String questao, ArrayList<String> tags,
                   String tipo, String dificuldade) {
        this.categoria = categoria;
        this.id = id;
        this.respostaCorreta = respostaCorreta;
        this.respostasIncorretas = respostasIncorretas;
        this.questao = questao;
        this.tags = tags;
        this.tipo = tipo;
        this.dificuldade = dificuldade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public ArrayList<String> getRespostasIncorretas() {
        return respostasIncorretas;
    }

    public void setRespostasIncorretas(ArrayList<String> respostasIncorretas) {
        this.respostasIncorretas = respostasIncorretas;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }
}
