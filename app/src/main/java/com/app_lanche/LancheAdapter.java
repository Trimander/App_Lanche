package com.app_lanche;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class LancheAdapter extends BaseAdapter{

    private final List<Lanche> list;
    private final Context context;

    public LancheAdapter(List<Lanche> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<Lanche> getList() {
        return list;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.cardapio_item, parent, false);

        final Lanche sample = list.get(position);

        final ImageView icon = (ImageView)v.findViewById(R.id.imgSample);
        icon.setImageResource(sample.getImageLanche());

        final TextView title = (TextView)v.findViewById(R.id.txtTitleItem);
        title.setText(sample.getNomeLanche());

        final TextView valor = (TextView)v.findViewById(R.id.txtValorItem);
        valor.setText(sample.getValor());

       //  Bundle bundle = new Bundle();
      //  bundle.putStringArray();

        ImageButton btnGo = (ImageButton)v.findViewById(R.id.btnAdd);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sample.getCls() != null){
                    Intent intent = new Intent(context, sample.getCls());
                    intent.putExtra("nome", sample.getNomeLanche());
                    intent.putExtra("valor", sample.getValor());
                    intent.putExtra("desc", sample.getDescription());
                    context.startActivity(intent);

                }
                else{
                    Toast.makeText(context, "Desculpe, não é possivel acessar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
