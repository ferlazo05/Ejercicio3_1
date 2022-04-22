package com.example.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ejercicio3_1.Configuration.SQLiteConexion;
import com.example.ejercicio3_1.Configuration.Transactions;
import com.example.ejercicio3_1.Models.Empleados;

import java.util.ArrayList;

public class ActivityModificacionEmp extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Empleados> listaEmpleados;
    ArrayList<String> ArregloEmpleados;
    int indice_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion_emp);
        conexion = new SQLiteConexion(this, Transactions.NameDatabase, null, 1);
        lista = (ListView) findViewById(R.id.listViewEmpleados);
        ObtenerListaPersonas();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloEmpleados);
        lista.setAdapter(adp);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ActivityModificacionEmp.this, ""+listaEmpleados.get(i).getId(), Toast.LENGTH_SHORT).show();
                indice_id = listaEmpleados.get(i).getId(); //id exacto
                enviarDato();
            }
        });
    }

    private void enviarDato()
    {
        Bundle enviar_id = new Bundle();
        enviar_id.putInt("id", indice_id);
        Intent intent = new Intent(getApplicationContext(), ActivityModificarEmp.class);
        intent.putExtras(enviar_id);
        startActivity(intent);
    }

    private void ObtenerListaPersonas()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados list_emp = null;
        listaEmpleados = new ArrayList<Empleados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Transactions.tablaEmpleados, null);
        while(cursor.moveToNext())
        {
            list_emp = new Empleados();
            list_emp.setId(cursor.getInt(0));
            list_emp.setNombres(cursor.getString(1));
            list_emp.setApellidos(cursor.getString(2));
            list_emp.setDireccion(cursor.getString(3));
            list_emp.setPuesto(cursor.getString(4));
            list_emp.setEdad(cursor.getInt(5));
            listaEmpleados.add(list_emp);
        }
        cursor.close();
        llenarlista();
    }

    private void llenarlista()
    {
        ArregloEmpleados = new ArrayList<String>();
        for(int i=0; i<listaEmpleados.size(); i++)
        {
            ArregloEmpleados.add(listaEmpleados.get(i).getId()+" | "+
                    listaEmpleados.get(i).getNombres()+" "+
                    listaEmpleados.get(i).getApellidos()+" | "+
                    listaEmpleados.get(i).getPuesto()
                    );
        }
    }
}