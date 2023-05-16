package com.example.aplicacion2.Objetos.Productos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbProductos;

public class ProductoAddActivity extends AppCompatActivity {

    private EditText etProducto, etPrecio, etCantidad, etUbicacion, etTipo;
    Button btnAñadir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_add);


        etProducto = findViewById(R.id.ettProducto);
        etPrecio  = findViewById(R.id.ettPrecio);
        etUbicacion = findViewById(R.id.ettUbicacion);
        etCantidad = findViewById(R.id.ettCantidad);
        etTipo = findViewById(R.id.ettTipoProducto);
        btnAñadir = findViewById(R.id.btnAddNewProduct);


        btnAñadir.setOnClickListener(view -> {
            DbProductos dbProductos = new DbProductos(ProductoAddActivity.this);
            long id = dbProductos.insertarProducto(etProducto.getText().toString(), Integer.parseInt(etPrecio.getText().toString()), Integer.parseInt(etCantidad.getText().toString()), etUbicacion.getText().toString(), etTipo.getText().toString());

            if(id>0){
                Toast.makeText(this, "SE AÑADIO EL PRODUCTO A SU LISTA", Toast.LENGTH_SHORT).show();
                limpiar();
            }else {
                Toast.makeText(this, "ERROR AL AÑADIR EL PRODUCTO", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void limpiar (){

        etProducto.setText("");
        etPrecio.setText("");
        etUbicacion.setText("");
        etCantidad.setText("");
        etTipo.setText("");
    }


}