package com.me.quiz.telas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.quiz.R;
import com.me.quiz.databinding.FragmentResultadoPositivoBinding;
import com.me.quiz.entidades.UsuarioLogado;


public class ResultadoPositivoFragment extends Fragment {
    FragmentResultadoPositivoBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentResultadoPositivoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        assert bundle != null;
        int acertos = bundle.getInt("acertos");
        Long tempo = bundle.getLong("tempo");
        String categoria = bundle.getString("categoria");

        binding.tvTempo.setText(getString(R.string.tempo_em_numero, tempo));

        binding.tvNumeroAcertos.setText(getString(R.string.numero_acertos, acertos));
        Bundle b = new Bundle();
        b.putString("categoria", categoria);
        binding.btnTenteDenovo.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_resultadoPositivoFragment_to_quizFragment, b);
        });

        binding.tvVoltarAoInicio.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_resultadoPositivoFragment_to_homeFragment);
        });

    }
}