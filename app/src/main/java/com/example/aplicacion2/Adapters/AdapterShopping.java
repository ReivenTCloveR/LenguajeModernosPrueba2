package com.example.aplicacion2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacion2.Objetos.ListaProducto.ShoppingList;
import com.example.aplicacion2.R;
import com.example.aplicacion2.Objetos.ListaProducto.ShoppingListViewActivity;

import java.util.ArrayList;

public class AdapterShopping extends RecyclerView.Adapter<AdapterShopping.ViewHolderShopping>{

    Context context;
    public static ArrayList<ShoppingList> list;
    public static ArrayList<ShoppingList> originallist;

    public AdapterShopping(Context context, ArrayList<ShoppingList> list) {
        this.context = context;
        AdapterShopping.list = list;
        originallist = new ArrayList<>();
        originallist.addAll(list);
    }


    @NonNull
    @Override
    public ViewHolderShopping onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.desing_lvshopping ,parent,false);
        return new ViewHolderShopping(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderShopping holder, int position) {
        ShoppingList NombreLista = list.get(position);
        holder.NombreLista.setText(NombreLista.getNombre_list());
    }

    @Override
    public int getItemCount() {return list.size();}

    public static class ViewHolderShopping extends RecyclerView.ViewHolder{
        TextView NombreLista;
        ViewHolderShopping(View itemView) {
            super(itemView);
            NombreLista = itemView.findViewById(R.id.NombreProductoInList);

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, ShoppingListViewActivity.class);
                intent.putExtra("ID_List", list.get(getAdapterPosition()).getId_list());
                context.startActivities(new Intent[]{intent});
            });
        }
    }



}
