package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CarrinhoActivity extends AppCompatActivity {
    private Button btnVoltar;
    private Button btnFinalizar;
    private TextView tvNomeLanche;
    private TextView tvQuantidadeLanche;
    private TextView tvPrecoLanche;
    private TextView tvNomeBebida;
    private TextView tvQuantidadeBebida;
    private TextView tvPrecoBebida;
    private TextView tvPreco;
    private TextView tvTamanhoBebida;

    //double total;

    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName = "/Download/carrinho.csv";
    String filePath = baseDir + fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvNomeLanche = (TextView) findViewById(R.id.tvNomeLanche);
        tvQuantidadeLanche = (TextView) findViewById(R.id.tvQuantidadeLanche);
        tvPrecoLanche = (TextView) findViewById(R.id.tvPrecoLanche);
        tvNomeBebida = (TextView) findViewById(R.id.tvNomeBebida);
        tvTamanhoBebida = (TextView) findViewById(R.id.tvTamanhoBebida);
        tvQuantidadeBebida = (TextView) findViewById(R.id.tvQuantidadeBebida);
        tvPrecoBebida = (TextView) findViewById(R.id.tvPrecoBebida);
        tvPreco = (TextView) findViewById(R.id.tvPreco3);

        voltar();
        finalizar();
        //calculaTotal();

        String[] data = readCSV(filePath);
        tvNomeLanche.setText(data[0].toString());
        tvQuantidadeLanche.setText(data[2].toString());
        tvPrecoLanche.setText(data[3].toString());
        tvNomeBebida.setText(data[4].toString());
        tvTamanhoBebida.setText(data[5].toString());
        tvQuantidadeBebida.setText(data[6].toString());
        tvPrecoBebida.setText(data[7].toString());

       // tvPreco.setText(String.valueOf(String.valueOf(total)));
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

    public void calculaTotal(){
          //  total = Double.parseDouble(tvPrecoLanche.getText().toString()) + Double.parseDouble(tvPrecoBebida.getText().toString());
    }
}