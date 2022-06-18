package com.me.quiz.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.me.quiz.entidades.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Insert
    void inserirUsuario(Usuario usuario);

    @Update
    void atualizarUsuario(Usuario usuario);

    @Delete
    void deletarUsuario(Usuario usuario);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE id= :id")
    Usuario getUsuario(int id);



}
