package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewAnimator;

public class Menu extends AppCompatActivity {

    ImageButton btnPedidos, btnPartner, btnCalendario, btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPartner = (ImageButton) findViewById(R.id.Partners);
        btnPedidos = (ImageButton) findViewById(R.id.Pedidos);
        btnCalendario = (ImageButton) findViewById(R.id.Calendario);
        btnInfo = (ImageButton) findViewById(R.id.Informacion);


        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent(getApplicationContext(), PrincipalPedido.class);
                startActivity(intenta);
            }
        });

        btnPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentb = new Intent(getApplicationContext(), PrincipalPartner.class);
                startActivity(intentb);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentc = new Intent(getApplicationContext(), Informacion.class);
                startActivity(intentc);
            }
        });

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentd = new Intent(getApplicationContext(), PrincipalCalendario.class);
                startActivity(intentd);
            }
        });

    }
}
