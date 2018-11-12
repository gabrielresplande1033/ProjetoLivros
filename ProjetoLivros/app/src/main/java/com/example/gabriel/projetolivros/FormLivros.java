package com.example.gabriel.projetolivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gabriel.projetolivros.BDHelper.LivrosBD;
import com.example.gabriel.projetolivros.model.Livros;

public class FormLivros extends AppCompatActivity {

    EditText textIsbn, textTitulo, textEdicao, textAutores;
    Button btnCadMod;
    Livros editarLivro, livro;
    LivrosBD bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_livros);

        livro = new Livros();
        bdHelper = new LivrosBD(FormLivros.this);

        Intent intent = getIntent();
        editarLivro = (Livros) intent.getSerializableExtra("livro-escolhido");

        textIsbn = (EditText)findViewById(R.id.textIsbn);
        textTitulo = (EditText)findViewById(R.id.textTitulo);
        textEdicao = (EditText)findViewById(R.id.textEdicao);
        textAutores = (EditText)findViewById(R.id.textAutores);

        btnCadMod = (Button)findViewById(R.id.btnCadMod);

        if(editarLivro != null){
            btnCadMod.setText("Modificar");

            textIsbn.setText(editarLivro.getIsbn());
            textTitulo.setText(editarLivro.getTitulo());
            textEdicao.setText(editarLivro.getEdicao());
            textAutores.setText(editarLivro.getAutores());

            livro.setIsbn(editarLivro.getIsbn());

        }else{
            btnCadMod.setText("Cadastrar");
        }

        btnCadMod.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                livro.setIsbn(Integer.parseInt(textIsbn.getText().toString()));
                livro.setTitulo(textTitulo.getText().toString());
                livro.setEdicao(Integer.parseInt(textIsbn.getText().toString()));
                livro.setAutores(textAutores.getText().toString());

                if(btnCadMod.getText().toString().equals("Cadastrar")){
                    bdHelper.salvarLivro(livro);
                    bdHelper.close();
                }else{
                    bdHelper.alterarLivro(livro);
                    bdHelper.close();
                }

            }
        });
    }



}
