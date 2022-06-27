package com.me.quiz.telas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.me.quiz.databinding.FragmentQuizBinding;
import com.me.quiz.entidades.Questao;
import com.me.quiz.servicos.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuizFragment extends Fragment {
    FragmentQuizBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rgAlternativas.setOnCheckedChangeListener((radioGroup, i) -> {
            System.out.println(i);
        });

        getQuestoes();
    }

    private void getQuestoes() {
        Call<List<Questao>> call = RetrofitClient.getInstance().getApi().getQuestoes();
        call.enqueue(new Callback<List<Questao>>() {
            @Override
            public void onResponse(Call<List<Questao>> call, Response<List<Questao>> response) {
                List<Questao> questoes = response.body();

               binding.tvDescricao.setText(questoes.get(0).getQuestao());
            }

            @Override
            public void onFailure(Call<List<Questao>> call, Throwable t) {
                Toast.makeText(getContext(), "Ops, algo deu errado. Tente novamente.", Toast.LENGTH_LONG).show();
            }

        });
    }
}
