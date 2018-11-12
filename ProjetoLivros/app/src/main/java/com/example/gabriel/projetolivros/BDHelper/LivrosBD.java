package com.example.gabriel.projetolivros.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gabriel.projetolivros.model.Livros;

import java.util.ArrayList;

/**
 * Created by gabriel on 18/10/18.
 */

public class LivrosBD extends SQLiteOpenHelper{

    private static final String DATABASE = "dbLivros";
    private static final int VERSION = 1;

    public LivrosBD (Context context){
        super(context, DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String livro = "CREATE TABLE livros(isbn INTEGER PRIMARY KEY NOT NULL, editoraId INTEGER, titulo TEXT NOT NULL, edicao INTEGER NOT NULL, autores TEXT NOT NULL);";
        db.execSQL(livro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String livro = "DROP TABLE IF EXISTS livros";
        db.execSQL(livro);
    }

    public void salvarLivro(Livros livro){
        ContentValues values = new ContentValues();

        values.put("isbn", livro.getIsbn());
        values.put("titulo", livro.getTitulo());
        values.put("edicao", livro.getEdicao());
        values.put("autores", livro.getAutores());

        getWritableDatabase().insert("livros", null, values);
    }

    public void alterarLivro(Livros livro){
        ContentValues values = new ContentValues();

        values.put("isbn", livro.getIsbn());
        values.put("titulo", livro.getTitulo());
        values.put("edicao", livro.getEdicao());
        values.put("autores", livro.getAutores());

        String[] args = {Integer.toString(livro.getIsbn())};
        getWritableDatabase().update("livros", values,"isbn=?", args);

    }

    public void deletarLivro(Livros livro){
        String[] args = {Integer.toString(livro.getIsbn())};
        getWritableDatabase().delete("livros", "isbn=?", args);
    }

    public ArrayList<Livros> getLista(){
        String []columns = {"isbn","titulo","edicao","autores"};
        Cursor cursor = getWritableDatabase().query("livros", columns, null, null, null, null, null, null);
        ArrayList<Livros> livros = new ArrayList<Livros>();

        while (cursor.moveToNext()){

            Livros livro = new Livros();
            livro.setIsbn(cursor.getInt(0));
            livro.setTitulo(cursor.getString(1));
            livro.setEdicao(cursor.getInt(2));
            livro.setAutores(cursor.getString(3));

            livros.add(livro);

        }

        return livros;
    }
}
