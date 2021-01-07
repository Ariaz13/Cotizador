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

        String[] selectionArgs = { nombre.getText().toString() };

        ArrayList<String> theList =new ArrayList<>();

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
                theList.add("Costo: " + data.getString(12));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                lv.setAdapter(listAdapter);
            }
        }
    }
}