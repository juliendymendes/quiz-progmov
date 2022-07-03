package com.me.quiz.telas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.me.quiz.R;
import com.me.quiz.databinding.FragmentCadastroBinding;
import com.me.quiz.entidades.Usuario;
import com.me.quiz.helpers.UsuarioHelper;


public class CadastroFragment extends Fragment {
    FragmentCadastroBinding binding;
    UsuarioHelper usuarioHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioHelper = new UsuarioHelper(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentCadastroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCadastrar.setOnClickListener(view1 -> {
            cadastrar();
        });

    }

    private void cadastrar(){
        EditText edNome = binding.edNome;
        EditText edEmail = binding.edEmail;
        EditText edSenha = binding.edSenha;

        String nome = edNome.getText().toString();
        String email = edEmail.getText().toString();
        String senha = edSenha.getText().toString();

        usuarioHelper.inserirUsuario(new Usuario(nome, email, senha, 0));
        Toast.makeText(getContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
        NavHostFragment.findNavController(this).navigate(R.id.action_cadastroFragment_to_loginFragment);

    }

}