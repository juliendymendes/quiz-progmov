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

import com.me.quiz.R;
import com.me.quiz.adapters.AreasConhecimentoAdapter;
import com.me.quiz.adapters.EstatisticasAdapter;
import com.me.quiz.databinding.FragmentPainelBinding;
import com.me.quiz.entidades.Estatistica;


public class PainelFragment extends Fragment {
    Estatistica[] estatisticas = new Estatistica[5];
    FragmentPainelBinding binding;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        int i = 0;
        while(i < 5){
            estatisticas[i] = new Estatistica("Filme", 37, "1min");
            i++;
        }

        recyclerView = binding.recyclerViewPainel;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EstatisticasAdapter adapter = new EstatisticasAdapter(this,
                estatisticas);
        recyclerView.setAdapter(adapter);
    }
}