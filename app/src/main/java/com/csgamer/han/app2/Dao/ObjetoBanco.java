package com.csgamer.han.app2.Dao;

import android.database.Cursor;

import java.util.ArrayList;

public class ObjetoBanco {

    private ArrayList<String> colunasName = new ArrayList<String>();
    private ArrayList<ArrayList<String>> dados = new ArrayList<ArrayList<String>>();



    public void setDados(Cursor c) {

        if (c.moveToFirst()) {

            for (int i = 0; i < c.getColumnCount(); i++) {
                colunasName.add(c.getColumnName(i));
            }


            do {
                ArrayList<String> linha = new ArrayList<String>();
                for (int i = 0; i < c.getColumnCount(); i++) {
                    linha.add(c.getString(i));
                }
                dados.add(linha);
            } while (c.moveToNext());
        }

        c.close();
    }


    private String getDados(int linha, String alias) {

        String ret = null;
        int coluna = -1;


        for (int i = 0; i < colunasName.size(); i++) {
            if (alias.equals(colunasName.get(i))) {
                coluna = i;
                break;
            }
        }

        if (coluna == -1) {
            return null;
        }

        ret = dados.get(linha).get(coluna);

        return ret;
    }


    public String getString(int linha, String alias) {
        return getDados(linha, alias);
    }

    public char getChar(int linha, String alias) {
        return getDados(linha, alias).charAt(0);
    }

    public int getInt(int linha, String alias) {
        return Integer.parseInt(getDados(linha, alias));
    }

    public long getLong(int linha, String alias) {
        return Long.parseLong(getDados(linha, alias));
    }

    public double getDouble(int linha, String alias) {
        return Double.parseDouble(getDados(linha, alias));
    }

    public short getShort(int linha, String alias) {
        return (short) getInt(linha, alias);
    }

    //retorna a quantidade de registros (linhas ou tuplas)
    public int size() {
        return dados.size();
    }
}

