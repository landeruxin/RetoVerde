package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearUsuario extends AppCompatActivity {

    EditText codigo, usuario, contrasena;
    Button enviar;
    final String clave = "airtruk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        codigo = findViewById(R.id.edtCodigo);
        usuario = findViewById(R.id.edtUsuarioNuevo);
        contrasena = findViewById(R.id.edtContrasenaNueva);

        enviar = findViewById(R.id.btnEnviarNuevo);

        usuario.setEnabled(false);
        contrasena.setEnabled(false);


        codigo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cod = codigo.getText().toString();
                if(cod.equalsIgnoreCase(clave)){
                    usuario.setEnabled(true);
                    contrasena.setEnabled(true);
                }
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuario.length()>0 && contrasena.length()>0) {
                    if(usuario.length()>5 && contrasena.length()>5) {
                        BDSQLiteUsuarios bd = new BDSQLiteUsuarios(getApplicationContext(), "Usuarios_Airtruk", null, 1);
                        SQLiteDatabase db = bd.getWritableDatabase();
                        String sql = "insert into usuarios(usuario,contrasena) values('" +
                                usuario.getText() +
                                "','" + contrasena.getText() + "')";

                        try {
                            db.execSQL(sql);
                            Toast.makeText(getApplication(), "Usuario Guardado", Toast.LENGTH_SHORT).show();

                            finish();

                            usuario.setText("");//SIRVE APARA LIMPIAR LOS ELEMENTOS
                            contrasena.setText("");
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "El usuario y la contraseña deben de tener mínimo 6 carácteres.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Se deben de rellenar todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
