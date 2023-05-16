package com.example.aplicacion2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacion2.Objetos.ListaProducto.ShoppingList;
import com.example.aplicacion2.R;

import java.util.ArrayList;

public class AdapterShopping extends RecyclerView.Adapter<AdapterShopping.ViewHolderShopping>{

    Context context;
    public static ArrayList<ShoppingList> list;
    public static ArrayList<ShoppingList> originallist;

    public AdapterShopping(Context context, ArrayList<ShoppingList> list) {
        this.context = context;
        this.list = list;
        originallist = new ArrayList<>();
        originallist.addAll(list);
    }


    @NonNull
    @Override
    public AdapterShopping.ViewHolderShopping onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.desing_lvshopping ,parent,false);
        return new AdapterShopping.ViewHolderShopping(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShopping.ViewHolderShopping holder, int position) {
        String txtProducto = list.get(position).getNombre_list();
    }

    @Override
    public int getItemCount() {return list.size();}

    public static class ViewHolderShopping extends RecyclerView.ViewHolder{
        TextView MostrarNombreList, MostrarCantidadProductos;
        ViewHolderShopping(View itemView){
            super(itemView);
            MostrarNombreList=itemView.findViewById(R.id.NombreLista);
            MostrarCantidadProductos=itemView.findViewById(R.id.MostrarCantidadProducto);

            itemView.setOnClickListener(view -> {  /*aqui tengo que enviar el titulo nada mas */   });

        }

    }



}
