package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BebidasActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private Button btnNext;
    private Button btnVoltar;
    private Button btnAdicionar;
    private EditText etQuantidade;

    private TextView tvPreco;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addListenerOnButton();
        calculaTotal();
        voltar();
        proximo();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(BebidasActivity.this,
                        "Itens selecionados : " +
                                "\nTamanho : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nBebida : " + String.valueOf(spinner2.getSelectedItem()) +
                                "\nQuantidade : " + String.valueOf(etQuantidade.getText())+
                                "\nPreço : " + String.valueOf(String.valueOf(total)),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void calculaTotal(){
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);
        tvPreco = (TextView) findViewById(R.id.tvPreco);

        etQuantidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(etQuantidade.length() == 0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.00));
                }else{
                    total = Integer.parseInt(etQuantidade.getText().toString()) * 3;
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

    }

    public void voltar(){
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BebidasActivity.this, CardapioActivity.class);
                startActivity(intent);
            }
        });
    }

    public void proximo(){
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BebidasActivity.this, PagamentoActivity.class);
                startActivity(intent);
            }
        });

    }
}
