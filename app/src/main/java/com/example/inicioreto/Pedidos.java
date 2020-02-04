package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.util.Calendar;

public class Pedidos extends AppCompatActivity {
    Button anadir;
    TextView articulo;
    TextView cantidad;
    TableLayout tabla;
    Button crear;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        anadir=findViewById(R.id.btnAnadir);
        articulo=findViewById(R.id.txtCodigo);
        cantidad=findViewById(R.id.txtCantidad);
        tabla=findViewById(R.id.tabla);
        crear=findViewById(R.id.btnCrear);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(Integer.parseInt(articulo.getText().toString())>0&&Integer.parseInt(articulo.getText().toString())<10){


                    TableRow row = new TableRow(getApplicationContext());
                    row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    TextView tv1 = new TextView(getApplicationContext());
                    TextView tv2 = new TextView(getApplicationContext());
                    tv1.setText(articulo.getText().toString());

                    tv2.setText(cantidad.getText().toString());
                    row.addView(tv1);
                    row.addView(tv2);
                    tabla.addView(row);

                    }
                    else{
                        Toast aviso = Toast.makeText(getApplicationContext(), "INTRODUCE UN ARTICULO VÁLIDO", Toast.LENGTH_SHORT);
                        aviso.show();
                    }

                }catch(Exception e){
                    Toast aviso = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    aviso.show();
            }

            }

        });
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final Calendar c = Calendar.getInstance();

                    int anio = c.get(Calendar.YEAR);
                    int mes  = c.get(Calendar.MONTH);
                    mes=mes+1;
                    int dia = c.get(Calendar.DAY_OF_MONTH);
                    BDSQLiteCabecera abrirBD = new BDSQLiteCabecera(getApplicationContext(), "DBArticulo", null, 6);
                SQLiteDatabase db = abrirBD.getWritableDatabase();
                db.execSQL("INSERT INTO Cabecera (idCabecera, idComercial,FechaPedido) " +
                        "VALUES ( NULL , '" + "1" + "', '"+dia+"-"+mes+"-"+anio+"'");

        }catch (Exception ex) {

        }

            }
        });
    }
}
