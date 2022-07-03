package com.me.quiz.telas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.me.quiz.R;
import com.me.quiz.adapters.AreasConhecimentoAdapter;
import com.me.quiz.databinding.FragmentHomeBinding;
import com.me.quiz.entidades.Quiz;
import com.me.quiz.entidades.UsuarioLogado;
import com.me.quiz.helpers.QuizHelper;
import com.me.quiz.helpers.UsuarioHelper;



public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private UsuarioHelper usuarioHelper;
    private QuizHelper quizHelper;

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioHelper = new UsuarioHelper(getContext());
        quizHelper = new QuizHelper(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.perfil.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_perfilFragment);
        });

        binding.cvEstatisticas.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_painelFragment);
        });


        binding.tvBoasVindas.setText(getString(R.string.boas_vindas, UsuarioLogado.getInstancia().getNome()));
        binding.tvEmail.setText(getString(R.string.email_home, UsuarioLogado.getInstancia().getEmail()));
        binding.tvAcertos.setText(getString(R.string.acertos_num, UsuarioLogado.getInstancia().getQtsAcertos()));

        String[] categorias = getResources().getStringArray(R.array.categorias);
        for (String c:
             categorias) {
            Quiz quiz = new Quiz(c, 0, Long.parseLong("0"));
            quizHelper.inserirQuiz(quiz);
        }

        recyclerView = binding.recycleViewHome;

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AreasConhecimentoAdapter adapter = new AreasConhecimentoAdapter(this,
                getResources().getStringArray(R.array.categorias));
        recyclerView.setAdapter(adapter);
    }
}