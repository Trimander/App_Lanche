package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CarrinhoActivity extends AppCompatActivity {
    private Button btnVoltar;
    private Button btnFinalizar;
    private TextView tvPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvPedido = (TextView) findViewById(R.id.tvPedido);

        voltar();
        finalizar();
        readFile();
    }

    public void voltar(){
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarrinhoActivity.this, PagamentoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void finalizar(){
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarrinhoActivity.this, StatusActivity.class);
                startActivity(intent);
            }
        });
    }

    public void readFile(){
        try{
            FileInputStream fin = openFileInput("Carrinho");
            InputStreamReader inputStream = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
            fin.close();
            inputStream.close();
            tvPedido.setText("Nome: " + stringBuilder.toString());

        }catch (java.io.IOException e){
            e.printStackTrace();
        }
    }

}