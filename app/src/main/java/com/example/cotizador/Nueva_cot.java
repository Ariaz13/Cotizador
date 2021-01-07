package com.example.cotizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Nueva_cot extends AppCompatActivity {
    Button btn_cotizar;
    RadioGroup uso, referencia, impreso;
    EditText cliente, fechaEntrega, cantidadPersonajes,
            descripcionDetalle, especificaciones, nombreIdentificador;
    Spinner nivelDetalle, tamano;
    //PlainText costo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cot);
        btn_cotizar = (Button)findViewById(R.id.btn_cotizar);
        uso = (RadioGroup) findViewById(R.id.rd_uso);
        referencia = (RadioGroup) findViewById(R.id.rd_referencia);
        impreso = (RadioGroup)findViewById(R.id.rd_impreso);
        nombreIdentificador = (EditText) findViewById(R.id.titulo);
        cliente = (EditText) findViewById(R.id.nombre);
        fechaEntrega = (EditText) findViewById(R.id.fecha);
        cantidadPersonajes = (EditText) findViewById(R.id.cant_per);
        nivelDetalle = (Spinner) findViewById(R.id.niv_det);
        descripcionDetalle = (EditText) findViewById(R.id.descrip_detalle);
        tamano = (Spinner) findViewById(R.id.tamaño);
        especificaciones = (EditText) findViewById(R.id.extra);
        String [] tam = {"Chico","Mediano", "Grande"};
        String [] det = {"Poco","Medio", "Alto"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tam);
        tamano.setAdapter(adapter);
        ArrayAdapter <String> adap = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, det);
        nivelDetalle.setAdapter(adap);
        //BBDD_Helper helper = new BBDD_Helper(this);

        /*btn_cotizar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.COLUMNA2, cliente.getText().toString());
                values.put(Estructura_BBDD.COLUMNA3, uso.getCheckedRadioButtonId());
                values.put(Estructura_BBDD.COLUMNA4, fechaEntrega.getText().toString());
                values.put(Estructura_BBDD.COLUMNA5, tamano.getSelectedItem().toString());
                values.put(Estructura_BBDD.COLUMNA6, cantidadPersonajes.getText().toString());
                values.put(Estructura_BBDD.COLUMNA7, nivelDetalle.getSelectedItem().toString());
                values.put(Estructura_BBDD.COLUMNA8, descripcionDetalle.getText().toString());
                values.put(Estructura_BBDD.COLUMNA9, referencia.getCheckedRadioButtonId());
                values.put(Estructura_BBDD.COLUMNA10, impreso.getCheckedRadioButtonId());
                values.put(Estructura_BBDD.COLUMNA11, especificaciones.getText().toString());
                values.put(Estructura_BBDD.COLUMNA12, nombreIdentificador.getText().toString());
                values.put(Estructura_BBDD.COLUMNA13, "0");

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(), "Se guardó el registro con clave: " +
                        newRowId, Toast.LENGTH_LONG).show();
            }
        });*/
    }

    public void Insertar(View view){
        BBDD_Helper helper = new BBDD_Helper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA2, cliente.getText().toString());

        values.put(Estructura_BBDD.COLUMNA3, uso.getCheckedRadioButtonId());

        values.put(Estructura_BBDD.COLUMNA4, fechaEntrega.getText().toString());
        values.put(Estructura_BBDD.COLUMNA5, tamano.getSelectedItem().toString());
        values.put(Estructura_BBDD.COLUMNA6, cantidadPersonajes.getText().toString());
        values.put(Estructura_BBDD.COLUMNA7, nivelDetalle.getSelectedItem().toString());
        values.put(Estructura_BBDD.COLUMNA8, descripcionDetalle.getText().toString());
        values.put(Estructura_BBDD.COLUMNA9, referencia.getCheckedRadioButtonId());
        values.put(Estructura_BBDD.COLUMNA10, impreso.getCheckedRadioButtonId());
        values.put(Estructura_BBDD.COLUMNA11, especificaciones.getText().toString());
        values.put(Estructura_BBDD.COLUMNA12, nombreIdentificador.getText().toString());
        //values.put(Estructura_BBDD.COLUMNA13, "0");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);

        Toast.makeText(getApplicationContext(), "Se guardó el registro con clave: " +
                newRowId, Toast.LENGTH_LONG).show();
    }
}