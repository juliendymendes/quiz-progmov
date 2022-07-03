package com.me.quiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.me.quiz.entidades.Usuario;

public class AppDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quizapp.db";

    private static final String TABLE_CREATE_USUARIO = "CREATE TABLE IF NOT EXISTS usuario(idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome TEXT, email TEXT, senha TEXT, qtdAcertos INTEGER);";

    private static final String TABLE_CREATE_QUIZ = "CREATE TABLE IF NOT EXISTS quiz(idQuiz INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, acertos INTEGER, tempo DECIMAL);";

    private static final String TABLE_CREATE_QUIZ_USUARIO = "CREATE TABLE IF NOT EXISTS quiz_usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER , idQuiz INTEGER);";

    SQLiteDatabase db;

    public AppDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USUARIO);
        db.execSQL(TABLE_CREATE_QUIZ);
        db.execSQL(TABLE_CREATE_QUIZ_USUARIO);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS quiz");
        db.execSQL("DROP TABLE IF EXISTS quiz_usuario");
        this.onCreate(db);
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}