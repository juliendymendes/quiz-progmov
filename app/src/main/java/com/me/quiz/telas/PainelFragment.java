package com.me.quiz.telas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.me.quiz.R;
import com.me.quiz.adapters.AreasConhecimentoAdapter;
import com.me.quiz.adapters.EstatisticasAdapter;
import com.me.quiz.databinding.FragmentPainelBinding;
import com.me.quiz.entidades.Estatistica;
import com.me.quiz.entidades.Quiz;
import com.me.quiz.entidades.UsuarioLogado;
import com.me.quiz.helpers.QuizUsuarioHelper;

import java.util.ArrayList;


public class PainelFragment extends Fragment {
    ArrayList<Estatistica> estatisticas;
    FragmentPainelBinding binding;
    RecyclerView recyclerView;

    QuizUsuarioHelper quizUsuarioHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizUsuarioHelper = new QuizUsuarioHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPainelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        estatisticas = new ArrayList<>();

        ArrayList<Quiz> quizzesUsuario = quizUsuarioHelper.getQuizzesPorUsuario(UsuarioLogado.getInstancia());
    
        if(quizzesUsuario.size() > 0){
            for (Quiz q :
                    quizzesUsuario) {
                estatisticas.add(new Estatistica(q.getNomeQuiz(), q.getAcertos(), q.getTempo()));
            }
        }else{
            binding.tvAindaNaoJogou.setVisibility(View.VISIBLE);
        }

        recyclerView = binding.recyclerViewPainel;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EstatisticasAdapter adapter = new EstatisticasAdapter(this,
                estatisticas);
        recyclerView.setAdapter(adapter);
    }
}