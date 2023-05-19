package com.example.aplicacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aplicacion2.Clases.InterestActivity;
import com.example.aplicacion2.Clases.RememberActivity;
import com.example.aplicacion2.Clases.ShoppingListActivity;

public class MainUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_usuario);

        TextView usuarioNombre = findViewById(R.id.textNameUser);
        String Nombre = getIntent().getStringExtra("user");
        usuarioNombre.setText(Nombre);

    }

    //Metodos

    public void irList(View view) {
        Intent i = new Intent(this, ShoppingListActivity.class);
        startActivity(i);    }

    public void irInterest(View view) {
        Intent i = new Intent(this, RememberActivity.class);
        startActivity(i);    }
    public void irRemember(View view) {
        Intent i = new Intent(this, InterestActivity.class);
        startActivity(i);    }
}