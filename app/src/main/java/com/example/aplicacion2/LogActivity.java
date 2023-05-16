package com.example.aplicacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LogActivity extends AppCompatActivity {

    private EditText Usuario_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Usuario_log =findViewById(R.id.Usuario_log);
    }



    //Metodos

    //metodo para para ir de LogActiviy --> MainUsuarioActivity
    public void IrUsuario(View view) {
        Intent i = new Intent(this, MainUsuarioActivity.class);
        i.putExtra("user", Usuario_log.getText().toString());
        startActivity(i);    }
}