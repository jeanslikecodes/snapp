package com.ctp.projetofinalsnapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    // MÉTODO QUE REGISTRA O USUÁRIO
    public void cadastraUsuario(View v) {
        EditText txtUser = (EditText) findViewById(R.id.txtUsername);
        EditText txtSenha = (EditText) findViewById(R.id.txtSenha);
        EditText txtConfSenha = (EditText) findViewById(R.id.txtConfirmaSenha);

        if (txtConfSenha.getText().toString().equals(txtSenha.getText().toString())) {

            User user = new User();
            UserDAO userDAO = new UserDAO(getBaseContext());

            user.setUsername(txtUser.getText().toString());
            user.setSenha(txtSenha.getText().toString());

            long result = userDAO.registrarUsuarioDAO(user);

            if (result > 0) {
                Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.logosnapp).setContentTitle("Usuário adicionado!").setContentText(user.getUsername()+" adcionado com sucesso!");

                Intent redirectIntent = new Intent(this, LoginActivity.class);
                redirectIntent.putExtra("ID", String.valueOf(result));

                PendingIntent pendente = PendingIntent.getActivity(this, 0, redirectIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                notifyBuilder.setContentIntent(pendente);
                NotificationManager myManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                myManager.notify((int) result, notifyBuilder.build());

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(getApplicationContext(),"As senhas não coincidem!",Toast.LENGTH_LONG).show();
        }
    }
}
