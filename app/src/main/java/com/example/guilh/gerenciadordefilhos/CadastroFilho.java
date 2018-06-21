package com.example.guilh.gerenciadordefilhos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.guilh.gerenciadordefilhos.Util.Database;
import com.example.guilh.gerenciadordefilhos.tabelas.*;

public class CadastroFilho extends AppCompatActivity {

    private EditText etNome;
    private EditText etDtaNasc;
    private EditText etAltura;
    private EditText etPeso;
    private EditText edtTamPe;


    private RadioButton rbMasc;
    private RadioButton rbFem;

    private Button btnCadastrar;

    private tableFilho tableFilho;
    private tableMedidas tableMedidas;
    private  Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filho);

        etNome = (EditText) findViewById(R.id.etNome);
        etDtaNasc = (EditText) findViewById(R.id.etDtaNasc);
        etAltura = (EditText) findViewById(R.id.etAltura);
        etPeso = (EditText) findViewById(R.id.etPeso);
        edtTamPe = (EditText) findViewById(R.id.edtTamPe);

        rbFem = (RadioButton) findViewById(R.id.rbFem);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        db = new Database(getApplicationContext());

        tableFilho = new tableFilho();
        tableMedidas = new tableMedidas();

        btnCadastrar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                tableFilho.setNome(etNome.getText().toString());
                tableFilho.setData_nasc(etDtaNasc.getText().toString());
                tableFilho.setSexo(rbFem.isChecked() ? "Feminino" : "Masculino");
                tableMedidas.setAltura(Float.parseFloat(etAltura.getText().toString()));
                tableMedidas.setPeso(Float.parseFloat(etPeso.getText().toString()));
                tableMedidas.setTam_pe(Integer.parseInt(edtTamPe.getText().toString()));
                tableFilho.insert(db.getReadableDatabase());
                tableFilho.selectMaxId(db.getReadableDatabase());
                tableMedidas.setFilho_id(tableFilho.getId());
                tableMedidas.insert(db.getReadableDatabase());
            }
        });

    }


}
