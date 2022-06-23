package com.example.n1_henriquegoebel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AdapterJogadores extends BaseAdapter {

    private List<Jogador> jogadorList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterJogadores(Context context, List<Jogador> listaJogadores){
        this.jogadorList = listaJogadores;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return jogadorList.size();
    }

    @Override
    public Object getItem(int i) {
        return jogadorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(jogadorList.get(i).id);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.activity_lista_layout, null);

            item = new ItemSuporte();
            item.tvNomeCamiseta = convertView.findViewById(R.id.tvNomeCamisetaList);
            item.tvNumeroCamiseta = convertView.findViewById(R.id.tvNumeroCamisetaList);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag(item);
        }else{
            item = (ItemSuporte) convertView.getTag();
        }

        Jogador jogador = jogadorList.get(i);
        item.tvNomeCamiseta.setText(jogador.nomeCamiseta);
        item.tvNumeroCamiseta.setText(String.valueOf(jogador.getNumeroCamiseta()));

        if (i % 2 == 0){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else{
            item.layout.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNomeCamiseta;
        TextView tvNumeroCamiseta;
        LinearLayout layout;
    }
}
