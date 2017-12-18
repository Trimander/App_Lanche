package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PagamentoActivity extends AppCompatActivity {
    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName = "/Download/carrinho.csv";
    String filePath = baseDir + fileName;

    private Button btnVoltar;
    private Button btnNext;
    private CheckBox cbCartao;
    private CheckBox cbDinheiro;
    private EditText etEndereco;
    private EditText etComplemento;

    String endereco;
    String complemento;
    String formaPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cbCartao = (CheckBox) findViewById(R.id.cbCartao);
        cbDinheiro = (CheckBox) findViewById(R.id.cbDinheiro);
        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etComplemento = (EditText) findViewById(R.id.etComplemento);

        voltar();
        escolhaPagamento();
        proximo();
    }

    public void voltar() {
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagamentoActivity.this, BebidasActivity.class);
                startActivity(intent);

            }
        });
    }

    public void proximo() {
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etEndereco.length() == 0){
                    Toast.makeText(PagamentoActivity.this, "Digite o endereço.", Toast.LENGTH_SHORT).show();
                }else if(formaPagamento == null){
                    Toast.makeText(PagamentoActivity.this, "Escolha a forma de Pagamento.", Toast.LENGTH_SHORT).show();
                }else{
                    endereco = String.valueOf(etEndereco.getText());
                    complemento = String.valueOf(etComplemento.getText());

                    String[] data = {"", endereco, complemento, formaPagamento};

                    writeCSV(filePath, data);

                    Intent intent = new Intent(PagamentoActivity.this, CarrinhoActivity.class);
                    startActivity(intent);
                    Toast.makeText(PagamentoActivity.this, "Funcionou", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void escolhaPagamento() {
        cbCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCartao.isChecked()){
                    cbDinheiro.setChecked(false);
                    formaPagamento = "Cartão";
                }else{
                    formaPagamento = null;
                }

            }

        });

        cbDinheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbDinheiro.isChecked()){
                    cbCartao.setChecked(false);
                    formaPagamento = "Dinheiro";
                }else{
                    formaPagamento = null;
                }

            }
        });


    }

    @NonNull
    private String[] readCSV(String filePath){
        String data = "";
        try{
            FileInputStream fis = new FileInputStream(new File(filePath));

            int temp;
            while ((temp = fis.read()) != -1){
                data += (char)temp;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return data.split(",");
    }

    private void writeCSV(String filePath, String[] data){
        String text = "";

        for(int i = 0;i<data.length - 1;i++){
            text += data[i] + ",";
        }

        text += data[data.length-1];

        try{
            FileOutputStream outputStream = new FileOutputStream(new File(filePath), true);
            outputStream.write(text.getBytes());
            outputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

