package com.me.quiz.telas;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.me.quiz.R;

import com.me.quiz.databinding.FragmentHomeBinding;
import com.me.quiz.entidades.Usuario;
import com.me.quiz.entidades.UsuarioLogado;
import com.me.quiz.helpers.UsuarioHelper;
import com.me.quiz.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {
    FragmentPerfilBinding binding;
    private UsuarioHelper usuarioHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usuarioHelper = new UsuarioHelper(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvEditarFoto.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_perfilFragment_to_tirarFoto);
        });
        Bitmap bitmap = getArguments().getParcelable("SEU_BITMAP");

        binding.imgFotoPerfil.setImageBitmap(bitmap);
        binding.edNome.setText(UsuarioLogado.getInstancia().getNome());
        binding.edEmail.setText(UsuarioLogado.getInstancia().getEmail());
        binding.edSenha.setText(UsuarioLogado.getInstancia().getSenha());

    }

}


