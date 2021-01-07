package com.example.cotizador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class Nueva_cot extends AppCompatActivity implements View.OnClickListener {
    Button btn_cotizar;
    RadioGroup uso, referencia, impreso;
    EditText cliente, fechaEntrega, cantidadPersonajes,
            descripcionDetalle, especificaciones, nombreIdentificador;
    Spinner nivelDetalle, tamano;
    private int dia, mes, anio;
    int ctamanio, cnDetalle, cpersonaje, creferencia, cuso, cimpresion, total=0;
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
        String [] tam = {"5cmx5cm","10cmx15cm","20cmx30cm", "30cmx40cm"};
        String [] det = {"Fondo plano","Poco detalle", "Muy detallado"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tam);
        tamano.setAdapter(adapter);
        ArrayAdapter <String> adap = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, det);
        nivelDetalle.setAdapter(adap);

        fechaEntrega.setOnClickListener(this);
    }

    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fechaEntrega.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }
                ,anio, mes, dia);
        datePickerDialog.show();
    }

    public void Insertar(View view){
        BBDD_Helper helper = new BBDD_Helper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        if (!cliente.getText().toString().isEmpty() && !fechaEntrega.getText().toString().isEmpty() && !tamano.getSelectedItem().toString().isEmpty()
        && !cantidadPersonajes.getText().toString().isEmpty() && !nivelDetalle.getSelectedItem().toString().isEmpty() && !descripcionDetalle.getText().toString().isEmpty()
        && !especificaciones.getText().toString().isEmpty() && !descripcionDetalle.getText().toString().isEmpty()) {
            values.put(Estructura_BBDD.COLUMNA2, cliente.getText().toString());
            if (uso.getCheckedRadioButtonId() == R.id.personal) {
                values.put(Estructura_BBDD.COLUMNA3, "Personal");
            } else {
                values.put(Estructura_BBDD.COLUMNA3, "Empresarial");
            }
            values.put(Estructura_BBDD.COLUMNA4, fechaEntrega.getText().toString());
            values.put(Estructura_BBDD.COLUMNA5, tamano.getSelectedItem().toString());
            values.put(Estructura_BBDD.COLUMNA6, cantidadPersonajes.getText().toString());
            values.put(Estructura_BBDD.COLUMNA7, nivelDetalle.getSelectedItem().toString());
            values.put(Estructura_BBDD.COLUMNA8, descripcionDetalle.getText().toString());
            if (referencia.getCheckedRadioButtonId() == R.id.ref_si) {
                values.put(Estructura_BBDD.COLUMNA9, "Si");
            } else {
                values.put(Estructura_BBDD.COLUMNA9, "No");
            }
            if (impreso.getCheckedRadioButtonId() == R.id.imp_si) {
                values.put(Estructura_BBDD.COLUMNA10, "Si");
            } else {
                values.put(Estructura_BBDD.COLUMNA10, "No");
            }
            values.put(Estructura_BBDD.COLUMNA11, especificaciones.getText().toString());
            values.put(Estructura_BBDD.COLUMNA12, nombreIdentificador.getText().toString());
            CalcularCosto(view);
            values.put(Estructura_BBDD.COLUMNA13, total);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);

            Toast.makeText(getApplicationContext(), "Se guardó el registro con clave: " +
                    newRowId, Toast.LENGTH_LONG).show();
            Variables(view);
        }else
            Toast.makeText(getApplicationContext(), "Debes de completar todos los campos",
                    Toast.LENGTH_LONG).show();
    }

    public void Variables(View view) {
        Intent com = new Intent(this, Contrato.class);
        com.putExtra("cliente", cliente.getText().toString());
        com.putExtra("fecha", fechaEntrega.getText().toString());
        String t = String.valueOf(total);
        com.putExtra("costo", t);
        startActivity(com);
    }

    public int CalcularCosto (View view){
        // TAMAÑO
        if(tamano.getSelectedItem().toString() == "5cmx5cm"){
            ctamanio = 5;
        }else if (tamano.getSelectedItem().toString() == "10cmx15cm"){
            ctamanio = 20;
        }else if(tamano.getSelectedItem().toString() == "20cmx30cm"){
            ctamanio = 35;
        }else if(tamano.getSelectedItem().toString() == "30cmx40cm"){
            ctamanio = 50;
        }
        // DETALLE
        if(nivelDetalle.getSelectedItem().toString() == "Fondo plano"){
            cnDetalle = 10;
        }else if(nivelDetalle.getSelectedItem().toString() == "Poco detalle") {
            cnDetalle = 20;
        }else if(nivelDetalle.getSelectedItem().toString() == "Muy detallado") {
            cnDetalle = 50;
        }
        // PERSONAJE
        String p = cantidadPersonajes.getText().toString();
        int per = Integer.parseInt(p);
        cpersonaje = 80 * per;
        //REFERENCIA
        if(referencia.getCheckedRadioButtonId() == R.id.ref_si){
            creferencia = 20;
        }else{creferencia = 60;}
        //USO
        if(uso.getCheckedRadioButtonId() == R.id.personal){
            cuso = 20;
        }else{cuso = 60;}
        //IMPRESO
        if(impreso.getCheckedRadioButtonId() == R.id.imp_si){
            cimpresion = 40;
        }else{cimpresion = 0;}

        total = ctamanio + cnDetalle + cpersonaje + creferencia + cuso + cimpresion;
        return total;
    }
}