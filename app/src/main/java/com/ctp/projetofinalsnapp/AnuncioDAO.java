package com.ctp.projetofinalsnapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by JeanCarlos on 20/09/2016.
 */

public class AnuncioDAO {
    private SQLiteDatabase db;
    private CreateDB createDB;

    public AnuncioDAO(Context context) {
        createDB = new CreateDB(context);
    }

    public long registrarProdutoDAO(Anuncio anuncio, String usertext) {
        ContentValues values;
        long result;

        db = createDB.getWritableDatabase();

        values = new ContentValues();
        values.put("produto", anuncio.getProduto());
        values.put("tamanho", anuncio.getTamanho());
        values.put("preco", anuncio.getPreco());
        values.put("nomeUser",usertext);

        result = db.insert("anuncio", null, values);

        db.close();
        return result;
    }

    public Cursor carregarAnunciosDAO() {
        Cursor cursor;
        String[] fields = {"_id", "nomeUser", "produto", "tamanho", "preco"};

        db = createDB.getReadableDatabase();

        cursor = db.query("anuncio", fields ,null,null,null,null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregarMeusAnunciosDAO(String username) {
        Cursor cursor;
        String[] fields = {"_id", "nomeUser", "produto", "tamanho", "preco"};
        String where = "nomeUser = '"+ username +"'";

        db = createDB.getReadableDatabase();

        cursor = db.query("anuncio", fields, where,null,null,null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Anuncio bucarAnuncioPorId(int id) {
        Cursor cursor;
        String [] fields = {"_id","nomeUser", "produto", "tamanho", "preco"};
        String where = "_id = "+ id;

        db = createDB.getReadableDatabase();

        cursor = db.query("anuncio",fields,where,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Anuncio anuncio = new Anuncio();
        anuncio.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        anuncio.setProduto(cursor.getString(cursor.getColumnIndexOrThrow("produto")));
        anuncio.setTamanho(cursor.getString(cursor.getColumnIndexOrThrow("tamanho")));
        anuncio.setPreco(cursor.getDouble(cursor.getColumnIndexOrThrow("preco")));

        db.close();
        return anuncio;
    }

    public long alterarAnuncioDAO(Anuncio anuncio){
        ContentValues values;
        String where = "_id = "+ anuncio.getId();
        long result;

        db = createDB.getWritableDatabase();

        values = new ContentValues();
        values.put("produto",anuncio.getProduto());
        values.put("tamanho", anuncio.getTamanho());
        values.put("preco", anuncio.getPreco());

        result = db.update("anuncio",values,where,null);

        db.close();
        return result;
    }

    public int removerAnuncioDAO(Anuncio anuncio) {
        String where = "_id ="+ anuncio.getId();

        db = createDB.getWritableDatabase();

        int result = db.delete("anuncio", where, null);

        db.close();
        return result;
    }
}

