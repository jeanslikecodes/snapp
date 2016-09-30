package com.ctp.projetofinalsnapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyAccountActivity extends AppCompatActivity {

    User user;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        EditText txtNome = (EditText) findViewById(R.id.txtNomeMC);
        TextView txtSenha = (TextView) findViewById(R.id.txtSenhaMC);

        user = new User();
        userDAO = new UserDAO(getBaseContext());
        user = userDAO.bucarUsuarioPorNome(UserDAO.usuario);

        txtNome.setText(user.getUsername());
        txtSenha.setText(user.getSenha());
    }

    // MÉTODO QUE ALTERA CONTA DO USUÁRIO
    public void alteraConta(View v) {
        EditText txtNome = (EditText) findViewById(R.id.txtNomeMC);
        EditText txtNovaSenha = (EditText) findViewById(R.id.txtNSenhaMC);

        user.setUsername(txtNome.getText().toString());
        user.setSenha(txtNovaSenha.getText().toString());
        UserDAO.usuario = txtNome.getText().toString();

        long result = userDAO.alterarUsuarioDAO(user);

        if (result > 0) {
            Toast.makeText(getApplicationContext(), "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
