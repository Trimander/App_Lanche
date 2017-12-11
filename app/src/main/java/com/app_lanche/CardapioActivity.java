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

        List<Lanche> samples = new ArrayList<>(10);
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - IMPERIO", "R$ 17,00", "", BebidasActivity.class));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - TUDO", "R$ 17,00", "", null));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - BACON", "R$ 17,00", "", null));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - EGG BACON", "R$ 17,00", "", null));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "MISTO", "R$ 17,00", "", null));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - SALADA", "R$ 17,00", "", null));
        samples.add(new Lanche(R.drawable.lanche, R.drawable.add, "X - BURGUER", "R$ 17,00", "", null));

        LancheAdapter adapter = new LancheAdapter(samples, this);
        lst.setAdapter(adapter);

//        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent myIntent = new Intent(CardapioActivity.this, AdicionarLancheActivity.class);
//                //myIntent.putExtra("test", "hello");
//                startActivity(myIntent);
//            }
//        });

    }


}
