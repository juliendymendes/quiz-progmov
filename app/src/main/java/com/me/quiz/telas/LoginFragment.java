package com.me.quiz.telas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.me.quiz.R;
import com.me.quiz.databinding.FragmentLoginBinding;
import com.me.quiz.entidades.Usuario;
import com.me.quiz.helpers.UsuarioHelper;
import com.me.quiz.utils.Md5Hash;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    UsuarioHelper usuarioHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioHelper = new UsuarioHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnEntrar.setOnClickListener(view1 -> {
            login();
        });



    }

    private void login(){
        String email = binding.edEmail.getText().toString();
        String senha = binding.edSenha.getText().toString();
        senha = Md5Hash.md5(senha);

        int id = usuarioHelper.login(email, senha);
        if(id != -1){
            Bundle bundle = new Bundle();
            bundle.putInt("idUsuario", id);
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment, bundle);
        }else{
            Toast.makeText(getContext(), "Email ou senha incorretos. Tente novamente.", Toast.LENGTH_LONG).show();
        }




    }



}