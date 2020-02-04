package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class AnadirEventos extends AppCompatActivity implements View.OnClickListener {

    private EditText nombreEvento, ubicacion, fechadesde, fechahasta, horadesde, horahasta, descripcion;
    private Button guardar, cancelar;
    private int anio, mes, dia, hora, minuto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_evento);

        nombreEvento = findViewById(R.id.edtNombreEvento);
        ubicacion = findViewById(R.id.edtUbicaciones);
        fechadesde = findViewById(R.id.edtFechaDesde);
        fechahasta = findViewById(R.id.edtFechaHasta);
        horadesde = findViewById(R.id.edtHoraInicio);
        horahasta = findViewById(R.id.edtHoraHasta);
        descripcion = findViewById(R.id.edtDescripcion);


        Bundle bundle = getIntent().getExtras();

        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        anio = bundle.getInt("anio");

        fechadesde.setText(dia + " - " + mes + " - " + anio);


        guardar = findViewById(R.id.btnGuardar);
        cancelar = findViewById(R.id.btnCancelar);

        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        fechahasta.setOnClickListener(this);
        horadesde.setOnClickListener(this);
        horahasta.setOnClickListener(this);



    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==guardar.getId()){
            if(nombreEvento.length()>0 && ubicacion.length()>0 && fechahasta.length()>0 && horadesde.length()>0 && horahasta.length()>0 && descripcion.length()>0) {
                //guardar datos

                BDSQLiteEventos bd = new BDSQLiteEventos(getApplicationContext(), "Agenda_Airtruk", null, 1);
                SQLiteDatabase db = bd.getWritableDatabase();

                String sql = "insert into eventos(nombreEvento,ubicacion,fechadesde,horadesde,fechahasta,horahasta,descripcion) values('" +
                        nombreEvento.getText() +
                        "','" + ubicacion.getText() +
                        "','" + fechadesde.getText() +
                        "','" + horadesde.getText() +
                        "','" + fechahasta.getText() +
                        "','" + horahasta.getText() +
                        "','" + descripcion.getText() + "')";

                try {
                    db.execSQL(sql);
                    Toast.makeText(getApplication(), "Evento guardado", Toast.LENGTH_SHORT).show();

                    Intent intento_dia = new Intent(getApplication(), PrincipalCalendario.class);

                    Bundle bundle_dia = new Bundle();

                    bundle_dia.putInt("dia1", dia);

                    bundle_dia.putInt("mes1", mes);

                    bundle_dia.putInt("anio1", anio);

                    intento_dia.putExtras(bundle_dia);

                    startActivity(intento_dia);


                    finish();

                    nombreEvento.setText("");//SIRVE APARA LIMPIAR LOS ELEMENTOS
                    ubicacion.setText("");
                    fechadesde.setText("");
                    fechahasta.setText("");
                    horadesde.setText("");
                    horahasta.setText("");
                    descripcion.setText("");

                } catch (Exception e) {
                    Toast.makeText(getApplication(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                this.finish();
            }else{
                Toast.makeText(getApplicationContext(), "Se deben de rellenar todos los campos.", Toast.LENGTH_SHORT).show();
            }



        } else if(v.getId()==fechahasta.getId()) {

            final Calendar c = Calendar.getInstance();

                anio = c.get(Calendar.YEAR);
                mes  = c.get(Calendar.MONTH);
                mes=mes+1;
                dia = c.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {



                                boolean valido = false;

                                if(year>anio){
                                    valido=true;
                                }else if(year==anio){
                                    if((monthOfYear+1)>mes){
                                        valido=true;
                                    }else if(monthOfYear == mes){
                                        if(dayOfMonth>=dia){
                                            valido=true;
                                        }
                                    }
                                }
                                if(valido==true) {
                                    fechahasta.setText(dayOfMonth + " - " + (monthOfYear + 1) + " - " + year);
                                }else{
                                    Toast.makeText(getApplication(), "La fecha seleccionada es anterior a la de hoy.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, anio, mes, dia);
                datePickerDialog.show();
        }else if(v.getId()==horadesde.getId()){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            horadesde.setText(hourOfDay + ":" + minute);
                        }
                    }, hora, minuto, false);
            timePickerDialog.show();

        }else if(v.getId()==horahasta.getId()){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            horahasta.setText(hourOfDay + ":" + minute);
                        }
                    }, hora, minuto, false);
            timePickerDialog.show();
        }else {
            this.finish();
            return;


        }
    }
}
