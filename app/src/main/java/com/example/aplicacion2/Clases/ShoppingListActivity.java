package com.example.aplicacion2.Clases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplicacion2.Adapters.AdapterShopping;
import com.example.aplicacion2.Objetos.ListaProducto.ShoppingListAddActivity;
import com.example.aplicacion2.R;
import com.example.aplicacion2.Setting.SettingsActivity;
import com.example.aplicacion2.db.DbHelper;
import com.example.aplicacion2.db.DbTablaProducto;

public class ShoppingListActivity extends AppCompatActivity {

    ImageView btnBack;
    RecyclerView listaCompras;
    AdapterShopping adapterShopping;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        listaCompras = findViewById(R.id.lvListaCompra);
        listaCompras.setLayoutManager(new LinearLayoutManager(this));

        //Aqui va lo de la base de datos
        DbTablaProducto dbShopping = new DbTablaProducto(ShoppingListActivity.this);

        adapterShopping = new AdapterShopping(this, dbShopping.mostrarListaCompra());
        listaCompras.setAdapter(adapterShopping);


        DbHelper dbhelper = new DbHelper(ShoppingListActivity.this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();



        Toolbar toolobar_menu = findViewById(R.id.toolbar);
        setSupportActionBar(toolobar_menu);
        initiToolbar();
        selectionToolbar();

    }

















    //interfaz
    void initiToolbar(){
        btnBack = findViewById(R.id.imgbtnBack);
    }

    void selectionToolbar(){
        btnBack.setOnClickListener(v -> finish());     }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_navegacion, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.Configuraciones:
                Toast.makeText(this, "Usuario", Toast.LENGTH_SHORT).show();
                Intent ir1 = new Intent(this, SettingsActivity.class);
                startActivity(ir1);
                break;
            case R.id.AddList:
                Toast.makeText(this, "Añadir Productos", Toast.LENGTH_SHORT).show();
                Intent ir2 = new Intent(this, ShoppingListAddActivity.class);
                DbTablaProducto dbShopping = new DbTablaProducto(ShoppingListActivity.this);
                int ID_last =dbShopping.obtenerUltimaIdListaProductos();
                ir2.putExtra("ID_last", ID_last);
                startActivity(ir2);
                break;
            case R.id.PrudctList:
                Toast.makeText(this, "Lista de Productos", Toast.LENGTH_SHORT).show();
                Intent ir3 = new Intent(this, ProductListActivity.class);
                startActivity(ir3);
                break;

            default:
                return super.onOptionsItemSelected(menuItem);}
        return super.onOptionsItemSelected(menuItem);
    }




}

