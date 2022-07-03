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
import com.me.quiz.entidades.Usuario;
import com.me.quiz.helpers.UsuarioHelper;
import com.me.quiz.utils.Md5Hash;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private final String[] data = new String[10];
    private UsuarioHelper usuarioHelper;

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioHelper = new UsuarioHelper(getContext());
    }

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
            login();
        });

        binding.cvEstatisticas.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_painelFragment);
        });

        Bundle bundle = getArguments();
        assert bundle != null;
        int idUsuario = bundle.getInt("idUsuario");
        Usuario usuario = usuarioHelper.getUsuarioPorId(idUsuario);

        binding.tvBoasVindas.setText(getString(R.string.boas_vindas, usuario.getNome()));
        binding.tvEmail.setText(getString(R.string.email_home, usuario.getEmail()));
        binding.tvAcertos.setText(getString(R.string.acertos_num, usuario.getQtsAcertos()));
        binding.tvSenha.setText(usuario.getSenha());


        int i = 0;
        while (i < 10) {
            data[i] = "Filmes";
            i++;
        }

        recyclerView = binding.recycleViewHome;

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AreasConhecimentoAdapter adapter = new AreasConhecimentoAdapter(this,
                getResources().getStringArray(R.array.categorias));
        recyclerView.setAdapter(adapter);
    }

    private void login(){
        String email = binding.tvEmail.getText().toString();
        String senha = binding.tvSenha.getText().toString();
        senha = Md5Hash.md5(senha);

        int id = usuarioHelper.login(email, senha);
        Bundle bundle = new Bundle();
        bundle.putInt("idUsuario", id);
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_perfilFragment, bundle);

    }
}