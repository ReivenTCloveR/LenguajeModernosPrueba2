package com.example.aplicacion2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion2.Objetos.Productos.Productos;
import com.example.aplicacion2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterProductAdd extends RecyclerView.Adapter<AdapterProductAdd.ViewHolderAddProduct>  {

    Context context;
    public static ArrayList<Productos> list;
    public static ArrayList<Productos> originallist;
    ArrayList<Productos> list_apoyo = new ArrayList<>();
    private OnItemClickListener mListener;


    public AdapterProductAdd(Context context, ArrayList<Productos> list) {
        this.context = context;
        AdapterProductAdd.list = list;
        originallist = new ArrayList<>();
        originallist.addAll(list);


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Productos producto);
        // void onItemClickforId(Productos producto);

    }


    @NonNull
    @Override
    public AdapterProductAdd.ViewHolderAddProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_lvproductadd ,parent,false);
        return new ViewHolderAddProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductAdd.ViewHolderAddProduct holder, int position) {

        Productos producto = list.get(position);
        holder.MostrarProducto.setText(producto.getNombre());
        int id = producto.getId();
        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(producto);
            }
           /* if (mListener != null) {
                mListener.onItemClick(producto);
            }*/
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolderAddProduct extends RecyclerView.ViewHolder {
        TextView MostrarProducto;
        EditText cantidadComprarEditText;

        ViewHolderAddProduct(View itemView) {
            super(itemView);
            MostrarProducto = itemView.findViewById(R.id.NombreLista);
            cantidadComprarEditText = itemView.findViewById(R.id.etCantidadComprar);


        }
    }






    @SuppressLint("NotifyDataSetChanged")
    public void filtro (String txtBuscar){
        int longitud = txtBuscar.length();
        if ( longitud== 0){
            list.clear();
            list.addAll(originallist);
        }else {
            List<Productos> colleccion =  list.stream()
                    .filter(i->i.getTipo().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            list.clear();
            list.addAll(colleccion);
        }
        notifyDataSetChanged();
    }


}
