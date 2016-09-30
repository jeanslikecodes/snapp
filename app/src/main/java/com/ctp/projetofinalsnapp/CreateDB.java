package com.ctp.projetofinalsnapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JeanCarlos on 13/09/2016.
 */

public class CreateDB extends SQLiteOpenHelper {
    private static final String table = "usuario";
    private static final String table1 = "anuncio";
    private static final String DBNAME = "cadsnapp.db";

    public CreateDB(Context context) {
        super(context,DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = String.format("Create table %s(_id integer primary key autoincrement, user text, senha text)", table);
        String sql1 = String.format("Create table %s(_id integer primary key autoincrement, nomeUser text, produto text, tamanho text, preco float)", table1);
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ table);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ table1);
        onCreate(sqLiteDatabase);
    }



}
