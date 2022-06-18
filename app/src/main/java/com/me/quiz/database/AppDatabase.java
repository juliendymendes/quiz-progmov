package com.me.quiz.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;
import com.me.quiz.dao.UsuarioDAO;
import com.me.quiz.entidades.Usuario;

@Database(entities = {Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "Quiz").allowMainThreadQueries().build();
        }

        INSTANCE.usuarioDAO().inserirUsuario(new Usuario("Amanda Silva", "amanda@gmail.com", "123", 0));

        return INSTANCE;
    }

    public abstract UsuarioDAO usuarioDAO();
}