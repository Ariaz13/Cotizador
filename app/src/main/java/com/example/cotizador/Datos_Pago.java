package com.example.cotizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Datos_Pago extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos__pago);
    }

    public void NuevaCotizacion (View view){
        Intent nueva = new Intent( this, Nueva_cot.class);
        startActivity(nueva);
    }
    public void Comisiones (View view){
        Intent comisiones = new Intent( this, comisiones.class);
        startActivity(comisiones);
    }
    public void Pago (View view){
        Intent pago = new Intent( this, Datos_Pago.class);
        startActivity(pago);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem opcion_menu){
        int id = opcion_menu.getItemId();
        if(id == R.id.nueva_cot){
            NuevaCotizacion(null);
            return  true;
        }
        if( id == R.id.comi){
            Comisiones(null);
            return true;
        }
        if ( id == R.id.pag){
            Pago(null);
            return true;
        }
        return super.onOptionsItemSelected(opcion_menu);
    }
}