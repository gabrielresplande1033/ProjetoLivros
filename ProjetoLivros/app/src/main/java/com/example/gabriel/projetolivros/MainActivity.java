package com.example.gabriel.projetolivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.gabriel.projetolivros.BDHelper.LivrosBD;
import com.example.gabriel.projetolivros.model.Livros;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    LivrosBD bdHelper;
    ArrayList<Livros> listLivros;
    Livros livro;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FormLivros.class);
                startActivity(intent);
            }

        });

        lista = (ListView)findViewById(R.id.listLivros);
        registerForContextMenu(lista);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Livros livroEscolhido = (Livros) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, FormLivros.class);
                i.putExtra("livro-escolhido", livroEscolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                livro = (Livros) adapter.getItemAtPosition(position);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Livro");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                bdHelper = new LivrosBD((MainActivity.this));
                bdHelper.deletarLivro(livro);
                bdHelper.close();
                carregarListLivros();
                return true;
            }
        });
    }

    protected  void onResume(){
        super.onResume();
        carregarListLivros();
    }

    public void carregarListLivros(){
        bdHelper = new LivrosBD(MainActivity.this);
        listLivros = bdHelper.getLista();
        bdHelper.close();

        if(listLivros != null){
          adapter = new ArrayAdapter<Livros>(MainActivity.this, android.R.layout.simple_list_item_1, listLivros);
          lista.setAdapter(adapter);

        }
    }

}
