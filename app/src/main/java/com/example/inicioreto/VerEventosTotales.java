package com.example.inicioreto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class VerEventosTotales extends AppCompatActivity implements AdapterView.OnItemLongClickListener{
    private SQLiteDatabase db;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_eventos_totales);


        listView =findViewById(R.id.ltvListaEventos);
        listView.setOnItemLongClickListener(this);


        BDSQLiteEventos bd = new BDSQLiteEventos(getApplicationContext(), "Agenda_Airtruk", null, 1);
        db = bd.getReadableDatabase();

        String sql = "select * from eventos";
        Cursor cursor;
        String nombre, fechadesde, fechahasta, descripcion, ubicacion, horadesde, horahasta,id;
        try {
            cursor = db.rawQuery(sql, null);
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getString(0);
                    nombre = cursor.getString(1);
                    ubicacion = cursor.getString(2);
                    fechadesde = cursor.getString(3);
                    horadesde=cursor.getString(4);
                    fechahasta = cursor.getString(5);
                    horahasta=cursor.getString(6);
                    descripcion = cursor.getString(7);
                    arrayAdapter.add("Nº Evento:  "+id +"\n\n  Desde:  "+fechadesde +"\n  Hasta:  "+fechahasta+"\n  Nombre:  "+nombre +"\n  Ubicación: " + ubicacion + "\n  Hora inicial: " + horadesde + "\n  Hora final: " + horahasta + "\n  Descripción:" + descripcion+"\n\n");

                } while (cursor.moveToNext());
                listView.setAdapter(arrayAdapter);
            }else{
                this.finish();
            }

        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }


    private void eliminar(String dato){
        String []datos = dato.split("  ");

        String sql="delete from eventos where idEvento='"+datos[1]+"'";
        try{
            arrayAdapter.remove(dato);
            listView.setAdapter(arrayAdapter);
            db.execSQL(sql);
            Toast.makeText(getApplication(), "Evento eliminado",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplication(), "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence[] items = new CharSequence[2];
        items[0] = "Eliminar evento";
        items[1] = "Cancelar";
        final int pos = position;
        builder.setTitle("Eliminar evento")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (i == 0) {//eliminar evento
                            eliminar(parent.getItemAtPosition(pos).toString());
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }


}

