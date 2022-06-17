package com.me.quiz.telas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.quiz.R;
import com.me.quiz.databinding.FragmentQuizBinding;


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
    }
}
