package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BebidasActivity extends AppCompatActivity {

    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName = "/Download/carrinho.csv";
    String filePath = baseDir + fileName;

    private Spinner spinner1, spinner2;
    private Button btnNext;
    private Button btnVoltar;
    private Button btnAdicionar;
    private EditText etQuantidade;
    private TextView tvPrecoBebidas;

    private TextView tvPreco;
    double total;

    String bebida;
    String tamanho;
    String quantidade;
    String preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        tvPrecoBebidas = (TextView) findViewById(R.id.tvPrecoBebida);

        adicionarCarrinho();
        calculaTotal();
        calculaPreco();
        voltar();
        proximo();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void adicionarCarrinho() {
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")) {
                    Toast.makeText(BebidasActivity.this, "Digite a quantidade!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    bebida = spinner2.getSelectedItem().toString();
                    tamanho = spinner1.getSelectedItem().toString();
                    quantidade = String.valueOf(etQuantidade.getText());
                    preco = String.valueOf(String.valueOf(total));

                    String[] data2 = {"", bebida, tamanho, quantidade, preco};

                    writeCSV(filePath, data2);
                    Toast.makeText(BebidasActivity.this, "Adicionado ao carrinho", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }

    public void calculaTotal(){
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);
        tvPreco = (TextView) findViewById(R.id.tvPreco3);

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
                    total = 0;
                    tvPreco.setText(String.valueOf(total));
                }else{
                    total = Integer.parseInt(etQuantidade.getText().toString()) *Double.parseDouble(tvPrecoBebidas.getText().toString()) ;
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

    }

    public void calculaPreco(){
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

                if(selectedItemText.equals("2 Litros")){
                    if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                        total = 0;
                        tvPrecoBebidas.setText("8.00");
                        tvPreco.setText(String.valueOf(total));
                    }else{
                        tvPrecoBebidas.setText("8.00");
                        total = Integer.parseInt(etQuantidade.getText().toString()) *Double.parseDouble(tvPrecoBebidas.getText().toString()) ;
                        tvPreco.setText(String.valueOf(total));
                    }
                }
                if(selectedItemText.equals("1,5 Litros")){
                    if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                        total = 0;
                        tvPrecoBebidas.setText("6.00");
                        tvPreco.setText(String.valueOf(total));
                    }else{
                        tvPrecoBebidas.setText("6.00");
                        total = Integer.parseInt(etQuantidade.getText().toString()) *Double.parseDouble(tvPrecoBebidas.getText().toString()) ;
                        tvPreco.setText(String.valueOf(total));
                    }
                }
                if(selectedItemText.equals("600 ml")){
                    if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                        total = 0;
                        tvPrecoBebidas.setText("4.00");
                        tvPreco.setText(String.valueOf(total));
                    }else{
                        tvPrecoBebidas.setText("4.00");
                        total = Integer.parseInt(etQuantidade.getText().toString()) *Double.parseDouble(tvPrecoBebidas.getText().toString()) ;
                        tvPreco.setText(String.valueOf(total));
                    }
                }
                if(selectedItemText.equals("Lata")){
                    if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                        total = 0;
                        tvPrecoBebidas.setText("3.50");
                        tvPreco.setText(String.valueOf(total));
                    }else{
                        tvPrecoBebidas.setText("3.50");
                        total = Integer.parseInt(etQuantidade.getText().toString()) *Double.parseDouble(tvPrecoBebidas.getText().toString()) ;
                        tvPreco.setText(String.valueOf(total));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")) {
                    String[] data2 = {"", "", "", "", ""," "};

                    writeCSV(filePath, data2);
                    Toast.makeText(BebidasActivity.this, "Entrou no if", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BebidasActivity.this, PagamentoActivity.class);
                    startActivity(intent);
                }
                else {
                    bebida = spinner2.getSelectedItem().toString();
                    tamanho = spinner1.getSelectedItem().toString();
                    quantidade = String.valueOf(etQuantidade.getText());
                    preco = String.valueOf(String.valueOf(total));

                    String[] data2 = {"", bebida, tamanho, quantidade, preco};

                    writeCSV(filePath, data2);
                    Toast.makeText(BebidasActivity.this, "Adicionado ao carrinho", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BebidasActivity.this, PagamentoActivity.class);
                    startActivity(intent);

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
