package com.ctp.projetofinalsnapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    // MÉTODO QUE CADASTRA PRODUTOS
    public void cadastrarProduto(View v) {
        EditText txtProduto = (EditText) findViewById(R.id.idProdutoInsert);
        EditText txtTamanho = (EditText) findViewById(R.id.idTamanhoInsert);
        EditText txtPreco = (EditText) findViewById(R.id.idPrecoInsert);

        Anuncio anuncio = new Anuncio();

        anuncio.setProduto(txtProduto.getText().toString());
        anuncio.setTamanho(txtTamanho.getText().toString());
        anuncio.setPreco(Double.parseDouble(txtPreco.getText().toString()));

        AnuncioDAO anuncioDAO = new AnuncioDAO(getBaseContext());
        String usuario = UserDAO.usuario;

        long result = anuncioDAO.registrarProdutoDAO(anuncio, usuario);

        if (result > 0) {
            Toast.makeText(getApplicationContext(),"Produto " + txtProduto.getText().toString() + " cadastrado com sucesso!",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
