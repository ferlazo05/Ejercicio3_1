package com.example.ejercicio3_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
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

public class ActivityAgregarEmp extends AppCompatActivity {

    EditText txt_nombres, txt_apellidos, txt_direccion, txt_edad;
    Button btn_agregar;
    Spinner cmb_puesto;
    private final String lista_puestos[] = {"-Seleccionar-",
            "Director Ejecutivo",
            "Director de Operaciones",
            "Director Comercial",
            "Director de Marketing",
            "Director de Recursos Humanos",
            "Director Financiero"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_emp);

        cmb_puesto = (Spinner) findViewById(R.id.cmbAddPuesto);
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista_puestos);
        cmb_puesto.setAdapter(adp);

        txt_nombres = (EditText) findViewById(R.id.txtModNombres);
        txt_apellidos = (EditText) findViewById(R.id.txtModApellidos);
        txt_direccion = (EditText) findViewById(R.id.txtModDireccion);
        txt_edad = (EditText) findViewById(R.id.txtModEdad);

        btn_agregar = (Button) findViewById(R.id.btnActualizar);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarEmpleados();
            }
        });
    }

    private void AgregarEmpleados()
    {
        String nom = String.valueOf(txt_nombres.getText());
        String ape = String.valueOf(txt_apellidos.getText());
        String dir = String.valueOf(txt_direccion.getText());
        String puesto = String.valueOf(cmb_puesto.getSelectedItem());
        String edad = String.valueOf(txt_edad.getText());

        if(nom.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor ingrese un nombre.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        else if(ape.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor ingrese un apellido.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        else if(edad.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor ingrese una edad.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        else if(dir.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor ingrese una direccion.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        else if(puesto.equals("-Seleccionar-"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor seleccione un puesto.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            builder.show();
        }
        else
        {
            SQLiteConexion conexion = new SQLiteConexion(this, Transactions.NameDatabase, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues valores = new ContentValues();

            String job = cmb_puesto.getSelectedItem().toString();

            valores.put(Transactions.empleado_nombres, txt_nombres.getText().toString());
            valores.put(Transactions.empleado_apellidos, txt_apellidos.getText().toString());
            valores.put(Transactions.empleado_direccion, txt_direccion.getText().toString());
            valores.put(Transactions.empleado_puesto, job);
            valores.put(Transactions.empleado_edad, txt_edad.getText().toString());
            Long resultado = db.insert(Transactions.tablaEmpleados, Transactions.empleado_id, valores);
            if (resultado>=1)
            {
                Toast.makeText(getApplicationContext(), "Se registró con éxito! Codigo="+resultado.toString()
                        , Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No se registró", Toast.LENGTH_SHORT).show();
            }
            db.close();
            LimpiarPantalla();
        }
    }

    private void LimpiarPantalla()
    {
        txt_nombres.setText("");
        txt_apellidos.setText("");
        txt_edad.setText("");
        txt_direccion.setText("");
        cmb_puesto.setSelection(0);
    }
}