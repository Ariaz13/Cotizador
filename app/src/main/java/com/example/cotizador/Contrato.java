package com.example.cotizador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Contrato extends AppCompatActivity {
    private TextView tv1;
    Button btn_aceptar, btn_cancelar;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato);
        tv1 = (TextView)findViewById(R.id.cotizacion_hecha);
        btn_aceptar = (Button)findViewById(R.id.btn_aceptar);
        btn_cancelar = (Button)findViewById(R.id.cancelar);
        name=getIntent().getStringExtra("cliente");
        String date=getIntent().getStringExtra("fecha");
        String costo=getIntent().getStringExtra("costo");
        tv1.setText("Cliente: " + name +"                                                                                     \n" +
                "\n" +
                "Fecha de entrega: "+ date + "\n" +
                "\n" +
                "Costo: "+ costo +"\n" +
                "\n" +
                "El presente contrato vale por la comisión de una imagen con las siguientes características:\n" +
                "\n" +
                "     •       Tamaño\n" +
                "\n" +
                "     •       Cantidad Personajes\n" +
                "\n" +
                "     •       Descripción de detalle\n" +
                "\n" +
                "\n" +
                "Al realizar el pedido se tomará en cuenta:\n" +
                "\n" +
                "      •      El pedido incluye una revisión del boceto con correcciones.\n" +
                "\n" +
                "      •      Las correcciones después del momento previamente iniciado tendrán un costo extra de $50.\n" +
                "\n" +
                "      •      En caso de ser impresa el costo extra y tiempo serán incluidos.\n" +
                "\n" +
                "      •      La realización de este proceso incluye comunicación entre cliente e ilustrador, por lo tanto, mientras más tardías sean las respuestas a las preguntas que el ilustrador realice el proceso de elaboración será más tardado.\n" +
                "\n" +
                "      •      Se trabaja con un adelanto del 50%.\n" +
                "\n" +
                "      •      De ser cancelada la comisión el adelanto no será reembolsado.\n" +
                "\n" +
                "     •       La ilustración será entregada una vez realizado el pago completo, sin importar fecha de entrega estimada.\n" +
                "\n" +
                "\n" +
                "Al leer este contrato y dar confirmación del pedido, las cláusulas presentadas anteriormente son aceptadas.");
    }

    public void Eliminar(View view){
        BBDD_Helper helper = new BBDD_Helper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String selection = Estructura_BBDD.COLUMNA2 + " LIKE ?";
        String[] selectionArgs = { name=getIntent().getStringExtra("cliente") };
        int deletedRows = db.delete(Estructura_BBDD.TABLE_NAME, selection, selectionArgs);

        Toast.makeText(getApplicationContext(), "Se ha cancelado el pedido", Toast.LENGTH_LONG).show();

        Intent cancelar = new Intent(this, MainActivity.class);
        startActivity(cancelar);
    }

    public void Aceptar(View view){
        Toast.makeText(getApplicationContext(), "Se ha realizado el pedido", Toast.LENGTH_LONG).show();

        Intent aceptar = new Intent(this, Datos_Pago.class);
        startActivity(aceptar);
    }

}