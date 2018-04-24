package com.csgamer.han.app2.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DaoAdapter extends SQLiteOpenHelper {

    public static final String BANCO = "SGBD";

    public static final int VERSAO = 1;

    private static final String queryDelete[] = {
            "DROP TABLE IF EXISTS carro;"
    };

    private static final String query[] = {
            "CREATE TABLE IF NOT EXISTS carro ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome VARCHAR(80) NOT NULL,"
                    + "cidade VARCHAR(100) NOT NULL,"
                    + "telefone VARCHAR(30) NOT NULL,"
                    + "modelo VARCHAR(30) NOT NULL,"
                    + "ano VARCHAR(20) NOT NULL,"
                    + "cor VARCHAR(30) NOT NULL,"
                    + "lugares VARCHAR(4) NOT NULL,"
                    + "motivo VARCHAR(60) NOT NULL"
                    + ");"
    };

    public DaoAdapter(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i = 0; i < query.length; i++) db.execSQL(query[i]);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i = 0; i < queryDelete.length; i++) db.execSQL(queryDelete[i]);
        onCreate(db);
    }

    public boolean queryExecute(String query, Object[] args){

        boolean status = true;
        try {
            getWritableDatabase().execSQL(query, args);
        } catch(SQLException e){
            Log.e("Please Wait", "DaoAdapter queryExecute: " + e.getMessage());
            status = false;
        }
        close();

        return status;
    }

    public long queryInsertLastId(String table, ContentValues values){
        /*
         * table => nome da tabela
         * values => valores da tabela (nome, telefone, rg...)
         */
        long status = -1;
        try {
            status = getWritableDatabase().insert(table, null, values);
        } catch(SQLException e){
            Log.e("Please Wait", "DaoAdapter queryInsertLastId: " + e.getMessage());
        }
        close();

        return status;
    }

    public ObjetoBanco queryConsulta(String query, String[] args){

        Cursor c = null;
        try {
            c = getReadableDatabase().rawQuery(query, args);
        } catch(SQLException e){
            Log.e("Please Wait", "DaoAdapter queryConsulta: " + e.getMessage());
        }

        ObjetoBanco ob = null;

        if(c != null){
            ob = new ObjetoBanco();
            ob.setDados(c);
        }
        if(!c.isClosed()) c.close();
        close();

        return ob;
    }
}
