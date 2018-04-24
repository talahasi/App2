package com.csgamer.han.app2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.csgamer.han.app2.Dao.DaoAdapter;
import com.csgamer.han.app2.Dao.DaoCarro;
import com.csgamer.han.app2.Domain.Carro;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int ACAO_ADICIONAR = 1111;
    private ListView list;
    private DaoCarro daoCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoAdapter daoAdapter = new DaoAdapter(this);
        daoAdapter.onCreate(daoAdapter.getWritableDatabase());

        list = (ListView) findViewById(R.id.list);
        daoCarro = new DaoCarro(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<String> values = new ArrayList<String>();
        final ArrayList<Carro> carros = daoCarro.getTodos(); //pegamos todas pessoas do banco

        if(carros.size() > 0) setTitle("Todos os veiculos");
        else setTitle("Não há veiculos cadastrados");

        //inserimos na lista de nomes que será exibida no listview
        for(Carro c : carros){
            values.add(c.getModelo());
        }
        //inserimos na lista de nomes que será exibida no listview

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text2, values);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro selecionada = carros.get(position);
                Intent i = new Intent(MainActivity.this, CadastroActivity.class);
                i.putExtra("id", selecionada.getId());
                startActivity(i);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Carro selecionada = carros.get(position);
                boolean test = daoCarro.delete(selecionada.getId());
                if(test){
                    Toast.makeText(MainActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                    onResume();
                } else{
                    Toast.makeText(MainActivity.this, "Erro!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ACAO_ADICIONAR, 0, "Novo");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case ACAO_ADICIONAR:
                Intent i = new Intent(this, CadastroActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
