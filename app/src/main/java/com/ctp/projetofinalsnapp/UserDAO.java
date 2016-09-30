package com.ctp.projetofinalsnapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;

/**
 * Created by JeanCarlos on 13/09/2016.
 */

public class UserDAO {

    public static String usuario;
    public static int id;
    private SQLiteDatabase db;
    private CreateDB createDB;

    public UserDAO(Context context) {
        createDB = new CreateDB(context);
    }

    public long registrarUsuarioDAO(User user) {
        ContentValues values;
        long result;

        db = createDB.getWritableDatabase();

        values = new ContentValues();
        values.put("user", user.getUsername());
        values.put("senha", user.getSenha());

        result = db.insert("usuario", null, values);

        db.close();
        return result;
    }

    public boolean verificarUsuarioDAO(String username, String senha) {
        Cursor cursor;
        String [] fields = {"_id","user","senha"};
        String where = "user = '"+ username +"' and senha = '"+ senha +"'";

        db = createDB.getReadableDatabase();

        cursor = db.query("usuario",fields,where,null,null,null,null,null);

        if(cursor != null){
            if(cursor.getCount() > 0)
                return true;
        }

        db.close();
        return false;
    }

    public User bucarUsuarioPorNome(String nome) {
        Cursor cursor;
        String [] fields = {"_id", "user", "senha"};
        String where = "user = '"+ nome +"'";

        db = createDB.getReadableDatabase();

        cursor = db.query("usuario",fields,where,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow("user")));
        user.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));

        db.close();
        return user;
    }

    public long alterarUsuarioDAO(User user){
        ContentValues values;
        String where = "_id = "+ user.getId();
        long result;

        db = createDB.getWritableDatabase();

        values = new ContentValues();
        values.put("user",user.getUsername());
        values.put("senha", user.getSenha());

        result = db.update("usuario",values,where,null);

        db.close();
        return result;
    }
}
