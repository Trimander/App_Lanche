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
    double total;

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
                if(etQuantidade.length() == 0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.00));
                }else{
                    total = Integer.parseInt(etQuantidade.getText().toString()) * Double.parseDouble(tvPreco2.getText().toString());
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

    }

}
