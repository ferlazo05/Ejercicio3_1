package com.example.ejercicio3_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejercicio3_1.Configuration.SQLiteConexion;
import com.example.ejercicio3_1.Configuration.Transactions;

public class ActivityModificarEmp extends AppCompatActivity {
    SQLiteConexion conexion;
    EditText txt_nombres, txt_apellidos, txt_direccion, txt_edad;
    Button btn_actualizar, btn_eliminar;
    Spinner cmb_puesto;
    private final String lista_puestos[] = {"-Seleccionar-",
            "Director Ejecutivo",
            "Director de Operaciones",
            "Director Comercial",
            "Director de Marketing",
            "Director de Recursos Humanos",
            "Director Financiero"};
    int id_indice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_emp);
        conexion = new SQLiteConexion(this, Transactions.NameDatabase, null, 1);

        txt_nombres = (EditText) findViewById(R.id.txtModNombres);
        txt_apellidos = (EditText) findViewById(R.id.txtModApellidos);
        txt_direccion = (EditText) findViewById(R.id.txtModDireccion);
        txt_edad = (EditText) findViewById(R.id.txtModEdad);

        cmb_puesto = (Spinner) findViewById(R.id.cmbModPuesto);
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista_puestos);
        cmb_puesto.setAdapter(adp);

        btn_actualizar = (Button) findViewById(R.id.btnActualizar);
        btn_eliminar = (Button) findViewById(R.id.btnEliminar);
        Bundle recibir_id = getIntent().getExtras();
        id_indice = recibir_id.getInt("id");
        ConsultaLlenarTxt();

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmarEliminar();
            }
        });
    }

    private void ConsultaLlenarTxt()
    {
        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this, R.array.list_puestos, android.R.layout.simple_spinner_dropdown_item);
        cmb_puesto.setAdapter(adp);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {String.valueOf(id_indice)};
        String[] fields = {Transactions.empleado_nombres, Transactions.empleado_apellidos,
                Transactions.empleado_direccion, Transactions.empleado_puesto, Transactions.empleado_edad};
        String WhereCondition = Transactions.empleado_id + "=?";
        try
        {
            Cursor cdata = db.query(Transactions.tablaEmpleados, fields, WhereCondition, params, null, null, null);
            cdata.moveToFirst();
            txt_nombres.setText(cdata.getString(0));
            txt_apellidos.setText(cdata.getString(1));
            txt_direccion.setText(cdata.getString(2));
            cmb_puesto.setSelection(adp.getPosition(cdata.getString(3)));
            txt_edad.setText(cdata.getString(4));
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error, no se encontró la persona.", Toast.LENGTH_SHORT).show();
        }
    }

    private void Actualizar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {String.valueOf(id_indice)};
        ContentValues valores = new ContentValues();

        String job = cmb_puesto.getSelectedItem().toString();

        valores.put(Transactions.empleado_nombres, txt_nombres.getText().toString());
        valores.put(Transactions.empleado_apellidos, txt_apellidos.getText().toString());
        valores.put(Transactions.empleado_direccion, txt_direccion.getText().toString());
        valores.put(Transactions.empleado_puesto, job);
        valores.put(Transactions.empleado_edad, txt_edad.getText().toString());
        db.update(Transactions.tablaEmpleados, valores, Transactions.empleado_id+"=?", params);
        Toast.makeText(this, "Se actualizó correctamente", Toast.LENGTH_SHORT).show();
        db.close();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void Eliminar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {String.valueOf(id_indice)};

        db.delete(Transactions.tablaEmpleados, Transactions.empleado_id+"=?", params);
        Toast.makeText(this, "Se eliminó correctamente", Toast.LENGTH_SHORT).show();
        db.close();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void ConfirmarEliminar()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Desea eliminar?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Eliminar();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }
}