package com.ctp.projetofinalsnapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageView = (ImageView) findViewById(R.id.logoLogin);

        imageView.setImageResource(R.drawable.logosnapp);
    }

    // MÉTODO QUE CHAMA ACTIVITY DE REGISTRO
    public void registrarConta(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    // MÉTODO QUE FAZ A VERFICAÇÃO DO NOME E SENHA DO USUARIO
    public void verificacaoUsuario (View v) {
        UserDAO userDAO = new UserDAO(this.getApplicationContext());

        EditText txtUser = (EditText) findViewById(R.id.txtUserLogin);
        EditText txtSenha = (EditText) findViewById(R.id.txtSenhaLogin);

        // TRIM - REMOVE ESPAÇO EM BRANCO DAS STRINGS, NO COMEÇO E FIM
        if (txtSenha.getText().toString().trim().equals("") || txtUser.getText().toString().trim().equals("")){

            Toast.makeText(getApplicationContext(), "Digite algum valor nos campos!", Toast.LENGTH_LONG).show();

        } else  {
            boolean acesso = userDAO.verificarUsuarioDAO(txtUser.getText().toString().trim(), txtSenha.getText().toString().trim());

            UserDAO.usuario = txtUser.getText().toString();

                if (acesso) {
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);

                    this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário e/ou senha incorretos!", Toast.LENGTH_LONG).show();
                }
        }

    }
}
