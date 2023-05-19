package com.example.aplicacion2.Clases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.aplicacion2.Adapters.AdapterProduct;

import com.example.aplicacion2.Objetos.Productos.ProductoAddActivity;
import com.example.aplicacion2.R;
import com.example.aplicacion2.Setting.SettingsActivity;
import com.example.aplicacion2.db.DbHelper;
import com.example.aplicacion2.db.DbProductos;

public class ProductListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView listaProductos;
    SearchView svBuscar;
    AdapterProduct adapterProduct;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        listaProductos = findViewById(R.id.lvProducts);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));
        DbProductos dbProductos = new DbProductos(ProductListActivity.this);
        adapterProduct = new AdapterProduct(this, dbProductos.mostrarPoduct());
        listaProductos.setAdapter(adapterProduct);

        svBuscar = findViewById(R.id.svBuscador);
        svBuscar.setOnQueryTextListener(this);


        DbHelper dbhelper = new DbHelper(ProductListActivity.this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();


    }


    public void IrAdd(View view) {
        Intent i = new Intent(this, ProductoAddActivity.class);
        startActivity(i);
    }

    //Metodos de buscar
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String txtbusca) {
        adapterProduct.filtro(txtbusca);
        return false;
    }


    //Metodos para el Menu

    public boolean OnCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_navegacion, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Configuraciones:
                irSetting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void irSetting() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}