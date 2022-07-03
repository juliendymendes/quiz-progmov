package com.me.quiz.servicos;


import com.me.quiz.entidades.Questao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://the-trivia-api.com/api/";
    @GET("questions")
    Call<List<Questao>> getQuestoes(@Query("limit") int limit);

    @GET("questions")
    Call<ArrayList<Questao>> getQuestoesPorCategoria(@Query("categories") String categoria, @Query("limit") int limite);
}
