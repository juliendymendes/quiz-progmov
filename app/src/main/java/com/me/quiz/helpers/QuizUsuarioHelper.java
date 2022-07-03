package com.me.quiz.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.quiz.database.AppDatabase;
import com.me.quiz.entidades.Quiz;
import com.me.quiz.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class QuizUsuarioHelper {

    AppDatabase appDatabase;
    SQLiteDatabase db ;

    public QuizUsuarioHelper(Context context){
        this.appDatabase = new AppDatabase(context);
        this.db = appDatabase.getDb();
    }

    public void inserirQuizParaUsuario(Quiz quiz, Usuario usuario){
        db = appDatabase.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idUsuario", usuario.getId());
        cv.put("idQuiz", quiz.getId());

        db.insert("quiz_usuario", null, cv);
        db.close();
    }

    public ArrayList<Quiz> getQuizzesPorUsuario(Usuario usuario){

        ArrayList<Quiz> lista = new ArrayList<>();
        String[] args = {String.valueOf(usuario.getId())};

        Cursor cursor = appDatabase.getReadableDatabase().rawQuery("select * from quiz left join quiz_usuario where quiz_usuario.idUsuario = ? and quiz_usuario.idQuiz = quiz.idQuiz", args);
        
        while (cursor.moveToNext()){
            Quiz quiz = new Quiz();
            quiz.setId(cursor.getInt(0));
            quiz.setNomeQuiz(cursor.getString(1));
            quiz.setAcertos(cursor.getInt(2));
            quiz.setTempo(cursor.getLong(3));

            lista.add(quiz);
        }
        return lista;
    }
    


}
