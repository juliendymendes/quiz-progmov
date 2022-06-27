package com.me.quiz.servicos;


import com.me.quiz.entidades.Questao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://the-trivia-api.com/api/";
    @GET("questions")
    Call<List<Questao>> getQuestoes();
}
