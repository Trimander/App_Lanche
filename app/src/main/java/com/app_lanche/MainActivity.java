package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button novoPedido;
    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName = "/Download/carrinho.csv";
    String filePath = baseDir + fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novoPedido = (Button) findViewById(R.id.btnNovoPedido);

        novoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarCSV(filePath);
                Intent intent = new Intent(MainActivity.this, CardapioActivity.class);
                startActivity(intent);
                }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void criarCSV(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }
}
