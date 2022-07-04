package com.me.quiz.telas;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PerfilFragment extends Fragment {
    FragmentPerfilBinding binding;
    private UsuarioHelper usuarioHelper;
    byte[] foto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usuarioHelper = new UsuarioHelper(getContext());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvEditarFoto.setOnClickListener(view1 -> {
            tirarFoto();
        });

        if(UsuarioLogado.getInstancia().getFoto() != null){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(UsuarioLogado.getInstancia().getFoto());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            binding.imgFotoPerfil.setImageBitmap(bitmap);
        }

        binding.edNome.setText(UsuarioLogado.getInstancia().getNome());
        binding.edEmail.setText(UsuarioLogado.getInstancia().getEmail());
        binding.edSenha.setText(UsuarioLogado.getInstancia().getSenha());

        binding.btnSalvar.setOnClickListener(view1 -> {
            salvarAlterações();
        });

    }

    private void salvarAlterações(){
        EditText edNome = binding.edNome;
        EditText edEmail = binding.edEmail;
        EditText edSenha = binding.edSenha;

        String nome = edNome.getText().toString();
        String email = edEmail.getText().toString();
        String senha = edSenha.getText().toString();

        if(nome.equals("") || email.equals("") || senha.equals("")) {
            Toast.makeText(getContext(), "Os campos não podem ser vazios.", Toast.LENGTH_SHORT).show();
        }else{
            UsuarioLogado.getInstancia().setNome(nome);
            UsuarioLogado.getInstancia().setEmail(email);
            UsuarioLogado.getInstancia().setSenha(senha);
            if(foto != null){
                UsuarioLogado.getInstancia().setFoto(foto);
            }

            usuarioHelper.atualizarUsuario(UsuarioLogado.getInstancia());
            Toast.makeText(getContext(), "Alterações salvas com sucesso!", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(this).navigate(R.id.action_perfilFragment_to_homeFragment);
        }


    }


    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            foto = bytes.toByteArray();
            UsuarioLogado.getInstancia().setFoto(foto);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(UsuarioLogado.getInstancia().getFoto());
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            binding.imgFotoPerfil.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}


