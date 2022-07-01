package com.me.quiz.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.quiz.database.AppDatabase;
import com.me.quiz.entidades.Quiz;
import com.me.quiz.entidades.Usuario;

import java.util.ArrayList;

public class UsuarioHelper{

    AppDatabase appDatabase;
    SQLiteDatabase db ;

    public UsuarioHelper(Context context){
        this.appDatabase = new AppDatabase(context);
        this.db = appDatabase.getDb();
    }


    public void inserirUsuario(Usuario usuario){
        db = appDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", usuario.getNome());
        cv.put("email", usuario.getEmail());
        cv.put("senha", usuario.getSenha());
        cv.put("qtdAcertos", usuario.getQtsAcertos());

        db.insert("usuario", null, cv);
        db.close();
    }

    public Usuario getUsuarioPorId(int id){
        Usuario usuario = new Usuario();
        String[] args = {String.valueOf(id)};
        Cursor cursor = appDatabase.getReadableDatabase().rawQuery("SELECT * FROM usuario WHERE idUsuario = ? LIMIT 1", args);


        while (cursor.moveToNext()){
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            usuario.setQtsAcertos(cursor.getInt(4));
        }
        return usuario;

    }

    public ArrayList<Usuario> getTodosUsuarios(){

        ArrayList<Usuario> lista = new ArrayList<>();

        Cursor cursor = appDatabase.getReadableDatabase().rawQuery("SELECT * FROM usuario",null);

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
            usuario.setSenha(cursor.getString(3));
            usuario.setQtsAcertos(cursor.getInt(4));

            lista.add(usuario);
        }
        return lista;
    }


    public void atualizarUsuario(Usuario usuario){
        db = appDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", usuario.getNome());
        cv.put("email", usuario.getEmail());
        cv.put("senha", usuario.getSenha());
        cv.put("qtdAcertos", usuario.getQtsAcertos());
        String[] args = {String.valueOf(usuario.getId())};
        db.update("usuario", cv, "idJogador=?", args);
        db.close();
    }

    public void deletarUsuario(int idUsuario){
        db = appDatabase.getWritableDatabase();
        String[] arg = {String.valueOf(idUsuario)};
        db.delete("usuario", "idUsuario = ? " , arg);
    }

    public int login(String email, String senha){

        String[] args = new String[2];
        args[0] = email;
        args[1] = senha;
        Cursor cursor = appDatabase.getReadableDatabase().rawQuery("SELECT idUsuario FROM usuario WHERE email = ? AND senha = ? LIMIT 1", args);
        if(cursor.moveToFirst()){
            return cursor.getInt(0);
        }else{
            return -1;
        }


    }



}
