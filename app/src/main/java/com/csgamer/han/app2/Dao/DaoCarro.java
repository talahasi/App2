package com.csgamer.han.app2.Dao;

import android.content.ContentValues;
import android.content.Context;

import com.csgamer.han.app2.Domain.Carro;

import java.util.ArrayList;

public class DaoCarro {

    private Context context;
    private DaoAdapter banco;

    public DaoCarro(Context context) {
        this.context = context;

        banco = new DaoAdapter(context);

        }

    public long insert(Carro carro) {

        ContentValues values = new ContentValues();
        values.put("nome", carro.getNome());
        values.put("cidade", carro.getCidade());
        values.put("telefone", carro.getTelefone());
        values.put("modelo", carro.getModelo());
        values.put("ano", carro.getAno());
        values.put("cor", carro.getCor());
        values.put("lugares", carro.getLugares());
        values.put("motivo", carro.getMotivo());

        long result = banco.queryInsertLastId("carro", values);

        return result;
    }

    public boolean update(Carro carro) {
        Object[] args = {
               carro.getNome(),
                carro.getCidade(),
                carro.getTelefone(),
                carro.getModelo(),
                carro.getAno(),
                carro.getCor(),
                carro.getLugares(),
                carro.getMotivo(),
                carro.getId()
        };

        boolean result = banco.queryExecute("UPDATE carro SET nome = ?, cidade = ?, telefone = ?," +
                "modelo = ?, ano = ?, cor = ?, lugares = ?, motivo = ? WHERE id = ?;", args);

        return result;
    }

    public boolean delete(long id) {
        Object[] args = {id};
        boolean result = banco.queryExecute("DELETE FROM carro WHERE id = ?", args);

        return result;
    }

    public ArrayList<Carro> getTodos() {
        ArrayList<Carro> carros = new ArrayList<Carro>();
        ObjetoBanco ob = banco.queryConsulta("SELECT * FROM carro ORDER BY nome ASC", null);

        if (ob != null) {
            for (int i = 0; i < ob.size(); i++) {
                Carro carro = new Carro();
                carro.setId(ob.getLong(i, "id"));
                carro.setNome(ob.getString(i, "nome"));
                carro.setCidade(ob.getString(i, "cidade"));
                carro.setTelefone(ob.getString(i, "telefone"));
                carro.setModelo(ob.getString(i, "modelo"));
                carro.setAno(ob.getString(i, "ano"));
                carro.setCor(ob.getString(i, "cor"));
                carro.setLugares(ob.getString(i, "lugares"));
                carro.setMotivo(ob.getString(i, "motivo"));

                carros.add(carro);
            }
        }

        return carros;
    }

    public Carro getCarro(long id) {
        String[] args = {String.valueOf(id)};
        ObjetoBanco ob = banco.queryConsulta("SELECT * FROM carro WHERE id = ?", args);

        Carro carro = null;
        if (ob != null) {
            carro = new Carro();
            carro.setId(ob.getLong(0, "id"));
            carro.setNome(ob.getString(0, "nome"));
            carro.setCidade(ob.getString(0, "cidade"));
            carro.setTelefone(ob.getString(0, "telefone"));
            carro.setModelo(ob.getString(0, "modelo"));
            carro.setAno(ob.getString(0, "ano"));
            carro.setCor(ob.getString(0, "cor"));
            carro.setLugares(ob.getString(0, "lugares"));
            carro.setMotivo(ob.getString(0, "motivo"));
        }

        return carro;
    }
}
