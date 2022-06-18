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
        cv.put("idQuiz", quiz.getIdQuiz());

        db.insert("quiz", null, cv);
        db.close();
    }

    public Quiz getQuizPorId(int id){
        Quiz quiz = new Quiz();
        String[] args = {String.valueOf(id)};
        Cursor cursor = appDatabase.getReadableDatabase().rawQuery("SELECT * FROM quiz WHERE idQuiz = ? LIMIT 1", args);


        while (cursor.moveToNext()){
            quiz.setIdQuiz(cursor.getInt(0));
        }
        return quiz;

    }
}
