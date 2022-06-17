package com.me.quiz.telas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.quiz.R;
import com.me.quiz.adapters.AreasConhecimentoAdapter;
import com.me.quiz.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private final String[] data = new String[10];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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


        int i = 0;
        while(i < 10){
            data[i] = "Filmes";
            i++;
        }

        RecyclerView recyclerView = binding.recycleViewHome;

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AreasConhecimentoAdapter adapter = new AreasConhecimentoAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
}