package com.example.aplicacion2.Objetos.ListaProducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aplicacion2.Adapters.AdapterViewShoppingList;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbHelper;
import com.example.aplicacion2.db.DbTablaProducto;

public class ShoppingListViewActivity extends AppCompatActivity {

    RecyclerView listaProductos;
    AdapterViewShoppingList adapterProductos;
    TextView txtNombreLista;
    ShoppingList Lista;
    int id = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_view);

        txtNombreLista = findViewById(R.id.txtNombreLista);


        listaProductos = findViewById(R.id.lvProductInList);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbTablaProducto dbTablaProducto = new DbTablaProducto(ShoppingListViewActivity.this);
        adapterProductos = new AdapterViewShoppingList(this, dbTablaProducto.mostrarListaCompra());
        listaProductos.setAdapter(adapterProductos);

        DbHelper dbHelper = new DbHelper( ShoppingListViewActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();




        //Ver producto
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                id = Integer.parseInt(null);
            } else {
                id = extra.getInt("ID_List");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID_List");
        }
        final DbTablaProducto dbTablaProducto1 = new DbTablaProducto(ShoppingListViewActivity.this);
        Lista = dbTablaProducto.verProductosInList(id);

        if (Lista != null){
            txtNombreLista.setText(Lista.getNombre_list());
        }
    }
}