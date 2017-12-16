package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class PagamentoActivity extends AppCompatActivity {
    private Button btnVoltar;
    private Button btnNext;
    private CheckBox cbCartao;
    private CheckBox cbDinheiro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cbCartao = (CheckBox) findViewById(R.id.cbCartao);
        cbDinheiro = (CheckBox) findViewById(R.id.cbDinheiro);

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
                Intent intent = new Intent(PagamentoActivity.this, CarrinhoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void escolhaPagamento() {
        cbCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbDinheiro.setChecked(false);
            }
        });
        cbDinheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbCartao.setChecked(false);
            }
        });


    }
}

