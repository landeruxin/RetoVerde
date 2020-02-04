package com.example.inicioreto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalPedido extends AppCompatActivity {
    private Button pedido;
    private Button visualizar;
    private Button catalogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_pedido);
        pedido=(Button)findViewById(R.id.btnPedido);
        visualizar=(Button)findViewById(R.id.btnVisualizar);
        catalogo=findViewById(R.id.btnCatalogo);
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Catalogo.class);
                startActivity(intent);
            }
        });

        pedido.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v){
                                            Intent intent=new Intent(getApplicationContext(), Pedidos.class);
                                            startActivity(intent);
                                        }
        });
        visualizar.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View v){
                                                Intent intent=new Intent(getApplicationContext(),Pedidos.class);
                                                startActivity(intent);
                                            }
        });

    }
}
