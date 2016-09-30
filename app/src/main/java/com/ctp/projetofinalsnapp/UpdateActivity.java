package com.ctp.projetofinalsnapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    Anuncio anuncio;
    AnuncioDAO anuncioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        EditText txtProd = (EditText) findViewById(R.id.idProdutoUpdate);
        EditText txtTam = (EditText) findViewById(R.id.idTamanhoUp);
        EditText txtPre = (EditText) findViewById(R.id.idPrecoUpdate);

        anuncio = new Anuncio();
        String bancoId = this.getIntent().getStringExtra("bancoId");
        anuncioDAO = new AnuncioDAO(getBaseContext());

        anuncio = anuncioDAO.bucarAnuncioPorId(Integer.parseInt(bancoId));

        txtProd.setText(anuncio.getProduto());
        txtTam.setText(anuncio.getTamanho());
        txtPre.setText(anuncio.getPreco().toString());
    }

    // MÉTODO QUE ALTERA DADOS DO ANUNCIO
    public void alterarAnuncio(View v) {
        EditText txtProd = (EditText) findViewById(R.id.idProdutoUpdate);
        EditText txtTam = (EditText) findViewById(R.id.idTamanhoUp);
        EditText txtPre = (EditText) findViewById(R.id.idPrecoUpdate);

        anuncio.setProduto(txtProd.getText().toString());
        anuncio.setTamanho(txtTam.getText().toString());
        anuncio.setPreco(Double.valueOf(txtPre.getText().toString()));

        long result = anuncioDAO.alterarAnuncioDAO(anuncio);

        if (result > 0) {
            Toast.makeText(getApplicationContext(), "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    // MÉTODO QUE REMOVE ANUNCIO
    public void removerAnuncio(View v) {
        int result = anuncioDAO.removerAnuncioDAO(anuncio);

        if (result > 0) {
            Toast.makeText(getApplicationContext(), "Removido com sucesso!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
