package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalPartner extends AppCompatActivity {

    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_partners);


        b1=(Button)findViewById(R.id.bttRealizar);
        b2=(Button)findViewById(R.id.bttVisualizar);

        b1.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        Intent intent=new Intent(getApplicationContext(), AgregarCliente.class);
                        startActivity(intent);
                    }
         });
        b2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        Intent intent = new Intent(getApplicationContext(), VisualizarPartners.class);
                        startActivity(intent);
                    }
                });


    }



}
