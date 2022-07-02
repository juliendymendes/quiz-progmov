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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
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
import com.me.quiz.entidades.Usuario;
import com.me.quiz.helpers.UsuarioHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class PerfilFragment extends Fragment {

    private UsuarioHelper usuarioHelper;
    private EditText edtNome, edtEmail, edtSenha;
    Usuario usuario, altUsuario;
    private ImageView image;
    private static final int PERMISSION_REQUEST_CODE = 200;
    static final int GALLERY = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    byte foto[];
    private static final String IMAGE_DIRECTORY = "/camera2022";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioHelper = new UsuarioHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_perfil, container,false);
        image = (ImageView) rootView.findViewById(R.id.img_foto_perfil);
        return rootView;

    }
    @Override
    public void onResume() {
        super.onResume();
        if(checkPermission()) {
            // Success
        } else {
            requestPermission();
        }
    }
    public void tirarFoto(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
    public void carregarFoto(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(resizeImage(bitmap, 300, 600));
            saveImage(bitmap);
            Toast.makeText(getActivity(), "Imagem Salva!",
                    Toast.LENGTH_SHORT).show();
        }else if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                            getActivity().getContentResolver(),
                            contentURI);
                    // Salvando a imagem
                    String path = saveImage(bitmap);
                    Log.i("TAG","Path: " + path);
                    Toast.makeText(getActivity(), "Image Saved!",
                            Toast.LENGTH_SHORT).show();
                    // Print on screen
                    image.setImageBitmap(resizeImage(bitmap, 300, 600));
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public String saveImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(
                bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        foto = bytes.toByteArray();

        File directory = new File(Environment.getExternalStorageDirectory()
                + IMAGE_DIRECTORY);
        // Criando o diretório caso ele não exista!
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            File fileImage = new File(directory,
                    Calendar.getInstance().getTimeInMillis() +".jpg");
            fileImage.createNewFile();
            FileOutputStream fo = new FileOutputStream(fileImage);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{fileImage.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved:->" + fileImage.getAbsolutePath());
            return fileImage.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
    public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        Matrix m = new Matrix();
        m.setScale((float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
        m.postRotate(90, 150, 300);
        Bitmap output = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted",
                            Toast.LENGTH_SHORT).show();
                    // main logic
                } else {
                    Toast.makeText(getActivity(), "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}


