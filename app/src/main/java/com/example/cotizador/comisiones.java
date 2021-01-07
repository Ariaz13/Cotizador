package com.example.cotizador;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class comisiones extends AppCompatActivity {
    EditText nombre;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comisiones);
        nombre = (EditText)findViewById(R.id.ing_nom);
        lv = (ListView) findViewById(R.id.comisiones);
    }

    public void Buscar(View view){
        BBDD_Helper helper = new BBDD_Helper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        /*String[] projection = {
                Estructura_BBDD.COLUMNA2,
                Estructura_BBDD.COLUMNA3,
                Estructura_BBDD.COLUMNA4,
                Estructura_BBDD.COLUMNA5,
                Estructura_BBDD.COLUMNA6,
                Estructura_BBDD.COLUMNA7,
                Estructura_BBDD.COLUMNA8,
                Estructura_BBDD.COLUMNA9,
                Estructura_BBDD.COLUMNA10,
                Estructura_BBDD.COLUMNA11,
                Estructura_BBDD.COLUMNA12
        };*/

// Filter results WHERE "title" = 'My Title'
        //String selection = Estructura_BBDD.COLUMNA2 + " = ?";
        String[] selectionArgs = { nombre.getText().toString() };

        //BBDD_Helper h = new BBDD_Helper(this);
        ArrayList<String> theList =new ArrayList<>();

        //Cursor data= h.getAllData();
        Cursor data = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLE_NAME + " WHERE " + Estructura_BBDD.COLUMNA2 + " = ?", selectionArgs);
        if (data.getCount()==0){
            return;
        }
        else
        {
            while(data.moveToNext()){
                theList.add("Título: " + data.getString(11));
                theList.add("Nombre cliente: " + data.getString(1));
                theList.add("Uso: " + data.getString(2));
                theList.add("Fecha entrega: " + data.getString(3));
                theList.add("Tamaño: " + data.getString(4));
                theList.add("Cantidad de personajes: " + data.getString(5));
                theList.add("Nivel de detalle: " + data.getString(6));
                theList.add("Descripción: " + data.getString(7));
                theList.add("Referencia: " + data.getString(8));
                theList.add("Impreso: " + data.getString(9));
                theList.add("Especificaciones: " + data.getString(10));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                lv.setAdapter(listAdapter);
            }
        }


    // How you want the results sorted in the resulting Cursor
        /*String sortOrder =
                Estructura_BBDD.COLUMNA2 + " DESC";

        Cursor cursor = db.query(
                Estructura_BBDD.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );*/
    }
}