package com.example.aplicacion2.Objetos.Productos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.aplicacion2.Clases.ListProductActivity;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbProductos;


public class ProductoViewActivity extends AppCompatActivity {

    private EditText etProducto, etPrecio, etUbicacion, etCantidad, etTipo;
    private ImageButton btnEdit, btnDelete;
    private Button btnGuardar, btnDeshacer;
    Productos producto;
    int id = 0;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_view);



        etProducto = findViewById(R.id.ettProducto);
        etPrecio = findViewById(R.id.ettPrecio);
        etUbicacion = findViewById(R.id.ettUbicacion);
        etCantidad = findViewById(R.id.ettCantidad);
        etTipo = findViewById(R.id.ettTipoProducto);
        btnEdit = findViewById(R.id.imgbtnEdit);
        btnDelete = findViewById(R.id.imgbtnDelet);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnDeshacer = findViewById(R.id.btnDeshacer);


        //desabilitar text edits
        etProducto.setEnabled(false);
        etPrecio.setEnabled(false);
        etUbicacion.setEnabled(false);
        etCantidad.setEnabled(false);
        etTipo.setEnabled(false);

        btnGuardar.setVisibility(View.INVISIBLE);
        btnDeshacer.setVisibility(View.INVISIBLE);


        //Ver producto
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                id = Integer.parseInt(null);
            } else {
                id = extra.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        //aqui se hace la consulta a la BD

        final DbProductos dbProductos = new DbProductos(ProductoViewActivity.this);
        producto = dbProductos.verProducto(id);

        if (producto != null) {
            etProducto.setText(producto.getNombre());
            etPrecio.setText(String.valueOf(producto.getPrecio()));
            etCantidad.setText(String.valueOf(producto.getCantidad()));
            etUbicacion.setText(producto.getUbicacion());
            etTipo.setText(producto.getTipo());
        }


        //Editar Producto

        final boolean[] ready = {false};

        btnEdit.setOnClickListener(v -> {

            btnGuardar.setVisibility(View.VISIBLE);
            btnDeshacer.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
            etPrecio.setEnabled(true);
            etUbicacion.setEnabled(true);
            etCantidad.setEnabled(true);

            btnGuardar.setOnClickListener(v1 -> {
                if(!etCantidad.getText().toString().equals("")&& !etPrecio.getText().equals("") && !etUbicacion.getText().equals("")){

                    Editable pre = (etPrecio.getText());
                    Editable can = (etCantidad.getText());
                    String ubi = etUbicacion.getText().toString();

                    ready[0] = dbProductos.editProducto(id,pre,can,ubi);


                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                    if (ready[0]){
                        Toast.makeText(ProductoViewActivity.this, "Modificacion realizada", Toast.LENGTH_SHORT).show();
                        intercambiadorDeBotones();
                    }else {
                        Toast.makeText(ProductoViewActivity.this, "Error al modificar su Producto", Toast.LENGTH_SHORT).show();
                        intercambiadorDeBotones();
                    }
                }else {
                    Toast.makeText(ProductoViewActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_SHORT).show();
                    intercambiadorDeBotones();
                }
            });

            btnDeshacer.setOnClickListener(v12 -> {
                intercambiadorDeBotones();
                //aqui me falta saber como recuperar los datos anteriores de los editTexT

            });



        });


        btnDelete.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(ProductoViewActivity.this);
            builder.setMessage("¿Desea eliminar este producto?").setPositiveButton("Si", (dialog, i) -> {
                if(dbProductos.eliminarProducto(id)){
                    lista();
                }
            }).setNegativeButton("No", (dialog, i) -> {

            }).show();
        });




    }

    private void lista(){
        Intent intent = new Intent(this, ListProductActivity.class);
        startActivity(intent);
    }


    //para que el codigo sea menos extenso
    private void intercambiadorDeBotones(){
        btnGuardar.setVisibility(View.INVISIBLE);
        btnDeshacer.setVisibility(View.INVISIBLE);
        btnEdit.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);
        etPrecio.setEnabled(false);
        etUbicacion.setEnabled(false);
        etCantidad.setEnabled(false);
    }



}



