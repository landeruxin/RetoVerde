package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Informacion extends AppCompatActivity{

    Button btnMapa, btnCorreo, btnTelefono, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);


        btnMapa = (Button) findViewById(R.id.ubicacion);
        btnCorreo = (Button) findViewById(R.id.Correo);
        btnTelefono = (Button) findViewById(R.id.telefono);
        btnVolver = (Button) findViewById(R.id.volver);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapaEmpresa.class);
                startActivity(intent);
            }
        });

        btnCorreo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","airtrukverde@gmail.com", null));
                startActivity(emailIntent);
            }
        });

        btnTelefono.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Uri number = Uri.parse("tel:943316900");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

                startActivity(callIntent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
        
    }

}
