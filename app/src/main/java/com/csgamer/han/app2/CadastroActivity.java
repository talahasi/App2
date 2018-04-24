package com.csgamer.han.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.csgamer.han.app2.Dao.DaoCarro;
import com.csgamer.han.app2.Domain.Carro;

public class CadastroActivity extends AppCompatActivity {

    private long id = 0l;

    private EditText edtProprietario;
    private EditText edtCidade;
    private EditText edtFone;
    private EditText edtModelo;
    private EditText edtCor;
    private EditText edtLugares;
    private EditText edtMotivo;
    private EditText edtAno;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setTitle("Cadastro do veiculo");

        edtProprietario = (EditText) findViewById(R.id.edtProprietario);
        edtCidade = (EditText) findViewById(R.id.edtCidade);
        edtFone = (EditText) findViewById(R.id.edtFone);
        edtModelo = (EditText) findViewById(R.id.edtModelo);
        edtCor = (EditText) findViewById(R.id.edtCor);
        edtLugares = (EditText) findViewById(R.id.edtLugares);
        edtMotivo = (EditText) findViewById(R.id.edtMotivo);
        edtAno = (EditText) findViewById(R.id.edtAno);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        Bundle b = getIntent().getExtras();
        if(b != null){
            id = b.getLong("id");
            DaoCarro daoCarro = new DaoCarro(this);
            //pegamos a pessoa do banco
            Carro carro = daoCarro.getCarro(id);
            //setamos os inputs com a informação da pessoa do banco
            edtProprietario.setText(carro.getNome());
            edtCidade.setText(carro.getCidade());
            edtFone.setText(carro.getTelefone());
            edtModelo.setText(carro.getModelo());
            edtCor.setText(carro.getCor());
            edtLugares.setText(carro.getLugares());
            edtMotivo.setText(carro.getMotivo());
            edtAno.setText(carro.getAno());
            setTitle("Alterar dados dado veiculo");
        }


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carro c = new Carro();
                c.setId(id);

                c.setNome(edtProprietario.getText().toString());
                c.setCidade(edtCidade.getText().toString());
                c.setTelefone(edtFone.getText().toString());
                c.setModelo(edtModelo.getText().toString());
                c.setCor(edtCor.getText().toString());
                c.setLugares(edtLugares.getText().toString());
                c.setMotivo(edtMotivo.getText().toString());
                c.setAno(edtAno.getText().toString());


                DaoCarro daoCarro = new DaoCarro(CadastroActivity.this);
                if (id == 0l) {
                    //inserindo no banco
                    if (daoCarro.insert(c) > 0) {
                        Toast.makeText(CadastroActivity.this, "Sucess!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CadastroActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //alterando no banco
                    if (daoCarro.update(c)) {
                        Toast.makeText(CadastroActivity.this, "Sucess!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CadastroActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                }

                finish();
            }
        });
    }

    }







