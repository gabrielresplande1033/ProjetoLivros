package com.example.gabriel.projetolivros.model;

import java.io.Serializable;

/**
 * Created by gabriel on 18/10/18.
 */

public class Livros implements Serializable{

    private int isbn;
    private int editoraId;
    private String titulo;
    private int edicao;
    private String autores;

    @Override
    public String toString(){
        return titulo.toString();
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(int editoraId) {
        this.editoraId = editoraId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

}
