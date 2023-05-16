package com.example.aplicacion2.Objetos.ListaProducto;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.aplicacion2.Adapters.AdapterProductAdd;
import com.example.aplicacion2.Clases.ShoppingListActivity;
import com.example.aplicacion2.Objetos.Productos.Productos;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbHelper;
import com.example.aplicacion2.db.DbProductos;
import com.example.aplicacion2.db.DbTablaProducto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;


public class ShoppingListAddActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterProductAdd.OnItemClickListener  {

    RecyclerView listaProductos;
    ImageView btnBack;
    FloatingActionButton addProductos;
    SearchView BuscarProductos;
    AdapterProductAdd adapterProductAdd;
    EditText etNombreLista;
    private final Map<Integer, Integer> mCantidadClics = new HashMap<>();

    long ID = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_add);

        listaProductos = findViewById(R.id.lv_addProduct);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(ShoppingListAddActivity.this);

        adapterProductAdd = new AdapterProductAdd(this, dbProductos.mostrarPoduct());
        adapterProductAdd.setOnItemClickListener(this);
        listaProductos.setAdapter(adapterProductAdd);

        //Botton Seach
        BuscarProductos =findViewById(R.id.BuscarProductos);
        BuscarProductos.setOnQueryTextListener(this);

        DbHelper dbhelper = new DbHelper(ShoppingListAddActivity.this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        //inicializar btns
        btnBack = findViewById(R.id.imgbtnBack);
        addProductos = findViewById(R.id.addButton);

        btnBack.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingListActivity.class);
            startActivity(i);
        });



        //Llamar Metodos


        //EditText Nombre de la Tabla
        etNombreLista = findViewById(R.id.etNombreLista);


        addProductos.setOnClickListener(v -> {
            DbTablaProducto dbTablaProducto = new DbTablaProducto(ShoppingListAddActivity.this);
            String nombreLista = etNombreLista.getText().toString();

            for (Map.Entry<Integer, Integer> entry : mCantidadClics.entrySet()) {
                int idProducto = entry.getKey();
                int cantidad = entry.getValue();

                try {
                    ID = dbTablaProducto.insertarTablaProducto(1, nombreLista, idProducto, cantidad );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            mCantidadClics.clear();
        });

    }
    //Cuando hago click guardo id + cantidad en un Diccionario y notifico
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemClick(Productos producto) {
        producto.aumentarContador();
        int id = producto.getId();
        int contador = producto.getClickCount();
        mCantidadClics.put(id, contador);
        // Notifica al adaptador que los datos han cambiado
        adapterProductAdd.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {return false;}

    @Override
    public boolean onQueryTextChange(String txtbusca) {
        adapterProductAdd.filtro(txtbusca);
        return false;
    }





}