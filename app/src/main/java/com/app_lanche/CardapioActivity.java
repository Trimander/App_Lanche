package com.app_lanche;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CardapioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView lst = (ListView) findViewById(R.id.lvtLanches);

        List<Lanche> samples = new ArrayList<>(7);
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - IMPERIO", "17.00", "Pão, maionese, hambúrguer, presunto, mussarela, bacon, ovo, frango, calabresa, lombo, tomate, alface, catupity, milho e ervilha.", AdicionarLancheActivity.class));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - TUDO", "12.00", "Pão, maionese, hambúrguer, presunto, mussarela, bacon, ovo, tomate, frango, milho e ervilha.", AdicionarLancheActivity.class));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - BACON", "9.00", "Pão, maionese, harmbúrguer, presunto, mussarela, bacon, milho e ervilha.", AdicionarLancheActivity.class));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - EGG BACON", "10.00", "Pão, maionese, hambúrguer, presunto, mussarela, bacon, ovo, milho e ervilha.", AdicionarLancheActivity.class));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - CALABRESA", "6.00", "Pão, maionese, hambúrguer, presunto, mussarela, calabresa, tomate, alface, milho e ervilha.", AdicionarLancheActivity.class));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - SALADA", "8.00", "Pão, maionese, hambúrguer, mussarela, tomate, alface, milho e ervilha.", AdicionarLancheActivity.class));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - BURGUER", "5.00", "Pão, maionese, hambúrguer, presunto, mussarela, milho e ervilha.", AdicionarLancheActivity.class));

        LancheAdapter adapter = new LancheAdapter(samples, this);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(CardapioActivity.this, AdicionarLancheActivity.class);
                startActivity(myIntent);
            }
        });

    }


}
