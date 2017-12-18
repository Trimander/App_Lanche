package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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

    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName = "/Download/carrinho.csv";
    String filePath = baseDir + fileName;

    ArrayList<String> list ;

    // Variaveis para escrever no arquivo
    String quantidade;
    String preco;
    String adicionais;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lanche);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<>();

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvPreco2 = (TextView) findViewById(R.id.tvPrecoL);
        tvdescricao = (TextView) findViewById(R.id.tvDescricao);
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        tvPreco = (TextView) findViewById(R.id.tvPreco3);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);

        tvNome.setText(getIntent().getExtras().getString("nome"));
        tvPreco2.setText(getIntent().getExtras().getString("valor"));
        tvdescricao.setText(getIntent().getExtras().getString("desc"));

        proximo();
        voltar();
        calculaTotal();
        verificaCheckBox();
        adicionarCarrinho();
    }

    public void voltar(){
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdicionarLancheActivity.this, CardapioActivity.class);
                startActivity(intent);
            }
        });
    }

    public void proximo(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")) {
                    if(!temCSV(filePath)){
                        String[] data = {"", "", "", ""};
                        writeCSV(filePath, data);
                    }

                    Intent intent = new Intent(AdicionarLancheActivity.this, BebidasActivity.class);
                    startActivity(intent);

                }
                else{
                    String aux = "";
                    for (String str : list) {
                        aux += str + "";
                    }

                    nome = tvNome.getText().toString();
                    adicionais = aux;
                    quantidade = String.valueOf(etQuantidade.getText());
                    preco = String.valueOf(String.valueOf(total));

                    String[] data = {nome, adicionais, quantidade, preco};
                    writeCSV(filePath, data);
                    Toast.makeText(AdicionarLancheActivity.this,"Adicionado ao carrinho", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AdicionarLancheActivity.this, BebidasActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void calculaTotal(){
        etQuantidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                    total = 0;
                    tvPreco.setText(String.valueOf(total));
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
                    list.add("Bacon");
                }else{
                    totalAdicional -= 2.00;
                    list.remove("Bacon");
                }

                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.0));
                }else {
                    total = Integer.parseInt(etQuantidade.getText().toString()) * (Double.parseDouble(tvPreco2.getText().toString()) + totalAdicional);
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

        cbCebola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCebola.isChecked()){
                    totalAdicional += 0.50;
                    list.add("Cebola");
                }else{
                    totalAdicional -= 0.50;
                    list.remove("Cebola");
                }

                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.0));
                }else {
                    total = Integer.parseInt(etQuantidade.getText().toString()) * (Double.parseDouble(tvPreco2.getText().toString()) + totalAdicional);
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

        cbOvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbOvo.isChecked()){
                    totalAdicional += 1.00;
                    list.add("Ovo");
                }else{
                    totalAdicional -= 1.00;
                    list.remove("Ovo");
                }

                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.0));
                }else {
                    total = Integer.parseInt(etQuantidade.getText().toString()) * (Double.parseDouble(tvPreco2.getText().toString()) + totalAdicional);
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

        cbFrango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbFrango.isChecked()){
                    totalAdicional += 1.50;
                    list.add("Frango");
                }else{
                    totalAdicional -= 1.50;
                    list.remove("Frango");
                }

                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.0));
                }else {
                    total = Integer.parseInt(etQuantidade.getText().toString()) * (Double.parseDouble(tvPreco2.getText().toString()) + totalAdicional);
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });

        cbHamburguer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbHamburguer.isChecked()){
                    totalAdicional += 3.00;
                    list.add("Hamburguer");
                }else{
                    totalAdicional -= 3.00;
                    list.remove("Hamburguer");
                }

                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")){
                    tvPreco.setText(String.valueOf(0.0));
                }else {
                    total = Integer.parseInt(etQuantidade.getText().toString()) * (Double.parseDouble(tvPreco2.getText().toString()) + totalAdicional);
                    tvPreco.setText(String.valueOf(total));
                }
            }
        });



    }

    public void adicionarCarrinho() {
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etQuantidade.length()== 0 || etQuantidade == null || etQuantidade.equals("")) {
                    Toast.makeText(AdicionarLancheActivity.this, "Digite a quantidade!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    String aux = "";
                    for (String str : list) {
                        aux += str + " ";
                    }

                    nome = tvNome.getText().toString();
                    adicionais = aux;
                    quantidade = String.valueOf(etQuantidade.getText());
                    preco = String.valueOf(String.valueOf(total));

                    String[] data = {nome, adicionais, quantidade, preco};
                    writeCSV(filePath, data);
                    Toast.makeText(AdicionarLancheActivity.this, "Adicionado ao carrinho", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AdicionarLancheActivity.this, CardapioActivity.class);
                    startActivity(intent);

                    }
                }
        });
    }

    private void writeCSV(String filePath, String[] data){
        String text = "";

        for(int i = 0;i<data.length - 1;i++){
            text += data[i] + ",";
        }

        text += data[data.length-1];

        try{
            FileOutputStream outputStream = new FileOutputStream(new File(filePath));
            outputStream.write(text.getBytes());
            outputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @NonNull
    private String[] readCSV(String filePath){
        String data = "";
        try{
                FileInputStream fis = new FileInputStream(new File(filePath));

                int temp;
                while ((temp = fis.read()) != -1) {
                    data += (char) temp;
                }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return data.split(",");
    }

    private boolean temCSV(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            return true;
        }
        return false;
    }
}
