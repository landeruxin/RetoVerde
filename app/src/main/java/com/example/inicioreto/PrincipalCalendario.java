package com.example.inicioreto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class PrincipalCalendario extends AppCompatActivity implements CalendarView.OnDateChangeListener {
    private CalendarView calendarView;
    private Button verEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_calendario);

        calendarView = (CalendarView) findViewById(R.id.calendario);
        verEventos = (Button) findViewById(R.id.verEventos);


        calendarView.setOnDateChangeListener(this);

        verEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), VerEventosTotales.class); //CREAMOS EL INTENTO LE PASAMOS LA ACTIVIDAD A LA QUE PASA

                startActivity(intent);//ARRANCAMOS LA ACTIVIDAD

            }
        });



    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        CharSequence items[] = new CharSequence[3];
        items[0] = "Agregar evento";
        items[1] = "Ver eventos";
        items[2] = "Cancelar";

        Calendar fecha = new GregorianCalendar();

        int anio1 = fecha.get(Calendar.YEAR);
        int mes1 = fecha.get(Calendar.MONTH);
        int dia1 = fecha.get(Calendar.DAY_OF_MONTH);

        final int dia, mes, anio;
        dia = dayOfMonth;
        mes = month + 1;//ENERO ES 0
        anio = year;

        boolean valido = false;

        if(anio>anio1){
            valido=true;
        }else if(anio==anio1){
            if(mes>(mes1+1)){
                valido=true;
            }else if(mes == (mes1+1)){
                if(dia>=dia1){
                    valido=true;
                }
            }
        }

        if(valido==true) {
            builder.setTitle("Selecciona una tarea")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {


                            if (i == 0) {//1-AGREGAR EVENTOS


                                Intent intent = new Intent(getApplication(), AnadirEventos.class); //CREAMOS EL INTENTO LE PASAMOS LA ACTIVIDAD A LA QUE PASA

                                Bundle bundle = new Bundle();//PARA PASAR DATOS

                                bundle.putInt("dia", dia);

                                bundle.putInt("mes", mes);

                                bundle.putInt("anio", anio);


                                intent.putExtras(bundle); //PASAMOS LOS DATOS AL INTENTO (DIA, MES Y AÑO)


                                startActivity(intent); //ARRANCAMOS LA ACTIVIDAD


                            } else if (i == 1) {//2-VER EVENTOS

                                Intent intent = new Intent(getApplication(), VerEventos.class); //CREAMOS EL INTENTO LE PASAMOS LA ACTIVIDAD A LA QUE PASA

                                Bundle bundle = new Bundle();//PARA PASAR DATOS

                                bundle.putInt("dia", dia);

                                bundle.putInt("mes", mes);

                                bundle.putInt("anio", anio);

                                intent.putExtras(bundle); //PASAMOS LOS DATOS AL INTENTO (DIA, MES Y AÑO)

                                startActivity(intent);//ARRANCAMOS LA ACTIVIDAD

                            } else {//3-SALIR
                                return;
                            }


                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        }else{
            Toast.makeText(getApplication(), "La fecha seleccionada es anterior a la de hoy.", Toast.LENGTH_SHORT).show();
        }

    }


}

