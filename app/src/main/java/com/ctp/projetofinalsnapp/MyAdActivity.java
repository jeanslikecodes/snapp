package com.ctp.projetofinalsnapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyAdActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ad);

        String nomeUsername = UserDAO.usuario;
        AnuncioDAO anuncioDAO = new AnuncioDAO(getBaseContext());

        final Cursor cursor = anuncioDAO.carregarMeusAnunciosDAO(nomeUsername);

        String[] columns = new String[] {"produto", "tamanho", "preco"};
        int[] idViews = new int[]{R.id.nomeMeusAnuncios,R.id.tamanhoMeusAnuncios, R.id.precoMeusAnuncios};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_itens_my_ad,cursor,columns,idViews,0);

        lista = (ListView)findViewById(R.id.listViewMA);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bancoId;
                cursor.moveToPosition(position);

                bancoId = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                Intent intent = new Intent(MyAdActivity.this, UpdateActivity.class);
                intent.putExtra("bancoId", bancoId);

                startActivity(intent);
                finish();
            }

        });
    }
}
