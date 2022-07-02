package com.me.quiz.telas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.me.quiz.R;
import com.me.quiz.databinding.FragmentPerfilBinding;
import com.me.quiz.entidades.Usuario;
import com.me.quiz.helpers.UsuarioHelper;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private UsuarioHelper usuarioHelper;
    private EditText edtNome, edtEmail, edtSenha;
    Usuario usuario, altUsuario;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usuarioHelper = new UsuarioHelper(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void onResume(){super.onResume();}
}

