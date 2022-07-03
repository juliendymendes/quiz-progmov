package com.me.quiz.telas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.me.quiz.R;
import com.me.quiz.databinding.FragmentQuizBinding;
import com.me.quiz.entidades.Questao;
import com.me.quiz.entidades.Quiz;
import com.me.quiz.entidades.Usuario;
import com.me.quiz.entidades.UsuarioLogado;
import com.me.quiz.helpers.QuizHelper;
import com.me.quiz.helpers.QuizUsuarioHelper;
import com.me.quiz.helpers.UsuarioHelper;
import com.me.quiz.servicos.RetrofitClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuizFragment extends Fragment {
    FragmentQuizBinding binding;
    int indexQuestaoAtual = 0;
    int acertos;
    Questao questaoAtual;
    Date date;
    String categoria;

    ArrayList<String> respostasAleatorias = new ArrayList();
    ArrayList<Quiz> quizzesUsuario =  new ArrayList<>();
    ArrayList<Integer> idQuizzesUsuarios = new ArrayList();

    Long tempoInicio;
    Long tempoFim;
    Long tempoFinal;

    QuizUsuarioHelper quizUsuarioHelper;
    QuizHelper quizHelper;
    UsuarioHelper usuarioHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizUsuarioHelper = new QuizUsuarioHelper(getContext());
        quizHelper = new QuizHelper(getContext());
        usuarioHelper = new UsuarioHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        date = new Date();
        tempoInicio = System.currentTimeMillis();

        Bundle bundle = getArguments();
        assert bundle != null;
        categoria = bundle.getString("categoria");

        getQuestoesPorCategorias(categoria);

    }


    private void getQuestoesPorCategorias(String categoria) {
       Call<ArrayList<Questao>> call = RetrofitClient.getInstance().getApi().getQuestoesPorCategoria(categoria,10);
        call.enqueue(new Callback<ArrayList<Questao>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Questao>> call, @NonNull Response<ArrayList<Questao>> response) {
                ArrayList<Questao> questoes = response.body();
                assert questoes != null;

                questaoAtual = questoes.get(indexQuestaoAtual);
                geraRespostasAleatorias();
                carregaQuestao(questaoAtual);
                indexQuestaoAtual += 1;

                binding.btnProxima.setOnClickListener(view -> {
                    if(verificaResposta()){
                        if(indexQuestaoAtual < 10){

                            questaoAtual = questoes.get(indexQuestaoAtual);
                            geraRespostasAleatorias();
                            indexQuestaoAtual += 1;
                            carregaQuestao(questaoAtual);

                        }else{
                            navegaParaTelaResultados();
                        }
                    }

                });
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Questao>> call, Throwable t) {
                Toast.makeText(getContext(), "Ops, algo deu errado. Tente novamente.", Toast.LENGTH_LONG).show();
            }


            public void carregaQuestao(Questao questaoAtual){
                    binding.rgAlternativas.clearCheck();
                    
                    binding.tvDescricao.setText(questaoAtual.getQuestao());
                    binding.op1.setText(respostasAleatorias.get(0));
                    binding.op2.setText(respostasAleatorias.get(1));
                    binding.op3.setText(respostasAleatorias.get(2));
                    binding.op4.setText(respostasAleatorias.get(3));
            }

            public boolean verificaResposta(){
                if(binding.rgAlternativas.getCheckedRadioButtonId() != -1) {
                    RadioButton rb = getActivity().findViewById(binding.rgAlternativas.getCheckedRadioButtonId());

                    String resposta = rb.getText().toString();

                    if(resposta.equals(questaoAtual.getRespostaCorreta())){
                        acertos +=1;
                    }
                    return true;
                }else{
                    Toast.makeText(getContext(), "Selecione uma resposta!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

            public void navegaParaTelaResultados(){
                tempoFim = date.getTime();
                tempoFinal = (System.currentTimeMillis() - tempoInicio)/1000;

                Quiz quiz = new Quiz(questaoAtual.getCategoria(), acertos, tempoFinal);
                quizHelper.inserirQuiz(quiz);
                ArrayList<Quiz> quizzes = quizHelper.getQuizzesPorNome(questaoAtual.getCategoria());
                quizzesUsuario = quizUsuarioHelper.getQuizzesPorUsuario(UsuarioLogado.getInstancia());

                for (Quiz q :
                        quizzesUsuario) {
                    idQuizzesUsuarios.add(q.getId());
                }

               if(quizzesUsuario.size() > 0) {
                    for (Quiz q :
                            quizzes) {

                        if (!idQuizzesUsuarios.contains(q.getId())) {
                            quizUsuarioHelper.inserirQuizParaUsuario(q, UsuarioLogado.getInstancia());
                        }
                    }
                }else{
                   for (Quiz q :
                           quizzes) {
                       quizUsuarioHelper.inserirQuizParaUsuario(q, UsuarioLogado.getInstancia());
                       System.out.println(q.getNomeQuiz());
                   }

               }

                UsuarioLogado.getInstancia().setQtsAcertos(acertos);
                usuarioHelper.atualizarUsuario(UsuarioLogado.getInstancia());

                assert getParentFragment() != null;
                Bundle bundle = new Bundle();
                bundle.putInt("acertos", acertos);
                bundle.putLong("tempo", tempoFinal);
                bundle.putString("categoria", categoria);
                if(acertos >= 6){
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_quizFragment_to_resultadoPositivoFragment, bundle);
                }else {
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_quizFragment_to_resultadoNegativoFragment, bundle);
                }
            }

            public void geraRespostasAleatorias(){
                Random random = new Random();
                respostasAleatorias = questaoAtual.getRespostasIncorretas();
                respostasAleatorias.add(random.nextInt(4), questaoAtual.getRespostaCorreta());
            }



        });

    }
}
