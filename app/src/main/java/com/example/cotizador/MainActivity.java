package com.example.cotizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}