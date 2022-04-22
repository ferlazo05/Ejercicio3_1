package com.example.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_agregar, btn_listado, btn_modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_agregar = (Button) findViewById(R.id.btnAgregarEmp);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityAgregarEmp.class);
                startActivity(intent);
            }
        });
        btn_listado = (Button) findViewById(R.id.btnListadoEmp);
        btn_listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityListaEmp.class);
                startActivity(intent);
            }
        });
        btn_modificar = (Button) findViewById(R.id.btnModificarEmp);
        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityModificacionEmp.class);
                startActivity(intent);
            }
        });
    }
}