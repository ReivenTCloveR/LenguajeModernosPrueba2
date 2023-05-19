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

public class AdapterViewShoppingList extends RecyclerView.Adapter<AdapterViewShoppingList.ViewHolderViewShooping>{

    Context context;
    public static ArrayList<ShoppingList> list;

    public AdapterViewShoppingList(Context context, ArrayList<ShoppingList> list) {
        this.context = context;
        AdapterViewShoppingList.list = list;
    }

    @NonNull
    @Override
    public ViewHolderViewShooping onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.desing_lvs_view_shoppinglist ,parent,false);
        return new ViewHolderViewShooping(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderViewShooping holder, int position) {

        ShoppingList idProducto = list.get(position);
        holder.idProducto.setText(String.valueOf(idProducto.getId_producto()));
        ShoppingList cantidad = list.get(position);
        holder.cantidadProducto.setText(String.valueOf(cantidad.getCantidad_productos()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolderViewShooping extends RecyclerView.ViewHolder{
        TextView idProducto, cantidadProducto;
        ViewHolderViewShooping(View itemView) {
            super(itemView);
            idProducto = itemView.findViewById(R.id.NombreProductoInList);
            cantidadProducto = itemView.findViewById(R.id.MostrarCantidadProductoComprar);

        }
    }
}
