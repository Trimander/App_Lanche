package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdicionarLancheActivity extends AppCompatActivity {

    private Button btnNext;
    private Button btnVoltar;
    private Button btnAdicionar;
    private EditText etQuantidade;
    private TextView tvNome;
    private TextView tvPreco2;
    private TextView tvPreco;
    private TextView tvdescricao;
    private CheckBox cbBacon;
    private CheckBox cbCebola;
    private CheckBox cbOvo;
    private CheckBox cbFrango;
    private CheckBox cbHamburguer;
    double total, totalAdicional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lanche);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvPreco2 = (TextView) findViewById(R.id.tvPrecoL);
        tvdescricao = (TextView) findViewById(R.id.tvDescricao);

        tvNome.setText(getIntent().getExtras().getString("nome"));
        tvPreco2.setText(getIntent().getExtras().getString("valor"));
        tvdescricao.setText(getIntent().getExtras().getString("desc"));

        proximo();
        voltar();
        calculaTotal();
        verificaCheckBox();
    }

    public void voltar(){
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdicionarLancheActivity.this, CardapioActivity.class);
                startActivity(intent);
            }
        });
    }

    public void proximo(){
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdicionarLancheActivity.this, BebidasActivity.class);
                startActivity(intent);
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
                if(etQuantidade.length() <0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.0));
                }else{
                    total = Integer.parseInt(etQuantidade.getText().toString()) * (Double.parseDouble(tvPreco2.getText().toString()) + totalAdicional);
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

    }

    public void verificaCheckBox(){
        cbBacon = (CheckBox)  findViewById(R.id.cbBacon);
        cbCebola = (CheckBox)  findViewById(R.id.cbCebola);
        cbOvo = (CheckBox)  findViewById(R.id.cbOvo);
        cbFrango = (CheckBox)  findViewById(R.id.cbFrango);
        cbHamburguer = (CheckBox)  findViewById(R.id.cbHamburguer);

        cbBacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbBacon.isChecked()){
                    totalAdicional += 2.00;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }else{
                    totalAdicional -= 2.00;
                    tvPreco.setText(String.valueOf(total) + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString())));
                }
            }
        });

        cbCebola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCebola.isChecked()){
                    totalAdicional += 0.50;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }else{
                    totalAdicional -= 0.50;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }
            }
        });

        cbOvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbOvo.isChecked()){
                    totalAdicional += 1.00;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }else{
                    totalAdicional -= 1.00;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }
            }
        });

        cbFrango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbFrango.isChecked()){
                    totalAdicional += 1.50;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }else{
                    totalAdicional -= 1.50;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }
            }
        });

        cbHamburguer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbHamburguer.isChecked()){
                    totalAdicional += 3.00;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }else{
                    totalAdicional -= 3.00;
                    tvPreco.setText(String.valueOf(total + (totalAdicional * Integer.parseInt(etQuantidade.getText().toString()))));
                }
            }
        });



    }

}
