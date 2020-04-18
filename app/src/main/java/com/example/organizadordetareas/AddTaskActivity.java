package com.example.organizadordetareas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

    EditText fecha, hora;
    ImageButton addFecha, addHora;

    public final Calendar c = Calendar.getInstance();

    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int year = c.get(Calendar.YEAR);

    final int hour = c.get(Calendar.HOUR_OF_DAY);
    final int minute = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        fecha = (EditText) findViewById(R.id.txtFecha);
        hora = (EditText) findViewById(R.id.txtHora);
        addFecha = (ImageButton)findViewById(R.id.addFecha);
        addHora = (ImageButton)findViewById(R.id.addHora);

        addFecha.setOnClickListener(this);
        addHora.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addFecha:
                obtenerFecha();
                break;
            case R.id.addHora:
                obtenerHora();
                break;
        }
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                fecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

            }
        },year, month, day);
        //Muestro el widget
        recogerFecha.show();
    }

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                String AM_PM;

                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                hora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
        }, hour, minute, false);

        recogerHora.show();
    }
}
