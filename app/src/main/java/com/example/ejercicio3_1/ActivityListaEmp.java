package com.example.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.ejercicio3_1.Configuration.SQLiteConexion;
import com.example.ejercicio3_1.Configuration.Transactions;
import com.example.ejercicio3_1.Models.Empleados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityListaEmp extends AppCompatActivity {
    SQLiteConexion conexion;
    List<ListEmployees> employees;
    ArrayList<Empleados> listaEmpleados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_emp);
        conexion = new SQLiteConexion(this, Transactions.NameDatabase, null, 1);
        IniciarList();
    }

    private void IniciarList()
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
        employees = new ArrayList<>();
        for(int i=0; i<listaEmpleados.size(); i++)
        {
            employees.add(new ListEmployees(listaEmpleados.get(i).getApellidos(), listaEmpleados.get(i).getNombres(), listaEmpleados.get(i).getPuesto()));
        }
        ListAdapter listAdapter = new ListAdapter(employees, this);
        RecyclerView recyclerView = findViewById(R.id.ListEmployees);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}