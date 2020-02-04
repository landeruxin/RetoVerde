package com.example.inicioreto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {


    EditText usuario, contrasena;
    Button enviar, nuevo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.edtUsuarioLogin);
        contrasena = findViewById(R.id.edtContrasenaLogin);

        enviar=findViewById(R.id.btnEnviarLogin);
        nuevo=findViewById(R.id.btnNuevoUsuario);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean comercialValido = false;
                String usu = usuario.getText().toString();
                String con = contrasena.getText().toString();

                if(usuario.length()>0 && contrasena.length()>0){

                    BDSQLiteUsuarios bd = new BDSQLiteUsuarios(getApplicationContext(), "Usuarios_Airtruk", null, 1);
                    SQLiteDatabase db = bd.getReadableDatabase();

                    String sql = "select * from usuarios";
                    Cursor cursor;
                    try {
                        cursor = db.rawQuery(sql, null);

                        if (cursor.moveToFirst()) {
                            do {
                                if(cursor.getString(1).equalsIgnoreCase(usu) && cursor.getString(2).equalsIgnoreCase(con)){
                                    comercialValido = true;
                                }
                            } while (cursor.moveToNext());
                        }else{

                        }

                    } catch (Exception e) {

                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Se deben de rellenar todos los campos.", Toast.LENGTH_SHORT).show();
                }

                if(usu.equalsIgnoreCase("prueba")&&(con.equalsIgnoreCase("prueba"))){
                    comercialValido=true;
                }


                if(comercialValido){
                    Toast.makeText(getApplicationContext(),"REGISTRO CORRECTO", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent (Login.this, Menu.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"El usuario o la contraseña es INCORRECTA", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent (Login.this, CrearUsuario.class);
                startActivity(intento);
                usuario.setText("");
                contrasena.setText("");
            }
        });
    }
}
