package com.me.quiz.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.quiz.database.AppDatabase;
import com.me.quiz.entidades.Quiz;
import com.me.quiz.entidades.Usuario;

public class QuizHelper {

    AppDatabase appDatabase;
    SQLiteDatabase db ;

    public QuizHelper(Context context){
        this.appDatabase = new AppDatabase(context);
        this.db = appDatabase.getDb();

    }

    public void inserirQuiz(Quiz quiz){
        db = appDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", quiz.getNomeQuiz());
        cv.put("acertos", quiz.getAcertos());
        cv.put("tempo", quiz.getTempo());

        db.insert("quiz", null, cv);
        db.close();
    }

    public Quiz getQuizPorId(int id){
        Quiz quiz = new Quiz();
        String[] args = {String.valueOf(id)};
        Cursor cursor = appDatabase.getReadableDatabase().rawQuery("SELECT * FROM quiz WHERE idQuiz = ? LIMIT 1", args);


        while (cursor.moveToNext()){
            quiz.setId(cursor.getInt(0));
            quiz.setNomeQuiz(cursor.getString(1));
            quiz.setAcertos(cursor.getInt(2));
            quiz.setTempo(cursor.getLong(3));
        }
        return quiz;

    }

    public Quiz getQuizPorNome(String nome){
        Quiz quiz = new Quiz();
        String[] args = {String.valueOf(nome)};
        Cursor cursor = appDatabase.getReadableDatabase().rawQuery("SELECT * FROM quiz WHERE nome LIKE ? LIMIT 1", args);


        while (cursor.moveToNext()){
            quiz.setId(cursor.getInt(0));
            quiz.setNomeQuiz(cursor.getString(1));
            quiz.setAcertos(cursor.getInt(2));
            quiz.setTempo(cursor.getLong(3));
        }
        return quiz;

    }

    public void atualizarQuiz(Quiz quiz){
        db = appDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", quiz.getNomeQuiz());
        cv.put("acertos", quiz.getAcertos());
        cv.put("tempo", quiz.getTempo());

        String[] args = {String.valueOf(quiz.getId())};
        db.update("quiz", cv, "idQuiz=?", args);
        db.close();
    }

}
