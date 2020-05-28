package com.example.organizadordetareas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizadordetareas.Fragment.mylistFragment;
import com.example.organizadordetareas.entidades.tareas;
import com.example.organizadordetareas.utilidades.Utilidades;

import java.util.ArrayList;

public class DetalleTask extends AppCompatActivity implements View.OnClickListener{
    tareas Item;
    TextView txtTarea, txtFecha, txtHora;
    ListView listViewTareas;
    ArrayList<String> listaInformacion;
    ArrayList<tareas> listaTareas;
    ConexionSqLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_task);

        conn = new ConexionSqLiteHelper(getApplicationContext(), "db_tareas", null, 1);

        Item = (tareas) getIntent().getSerializableExtra("objetoData");

        txtTarea = (TextView) findViewById(R.id.txtTareaDetalle);
        txtFecha = (TextView) findViewById(R.id.txtFechaDetalle);
        txtHora = (TextView) findViewById(R.id.txtHoraDetalle);

        txtTarea.setText(Item.getTarea());
        txtFecha.setText(Item.getFecha());
        txtHora.setText(Item.getHora());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTerminado:
                tareaTerminada();
                break;
            case R.id.btnEditar:
                editar();
                break;
            case R.id.btnEliminar:
                eliminar();
                break;
        }
    }

    private void eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtTarea.getText().toString()};

        db.delete(Utilidades.TABLA_TAREAS, Utilidades.CAMPO_TAREA+"=?", parametros);
        Toast.makeText(getApplicationContext(), "Eliminado exitosamente!", Toast.LENGTH_LONG).show();
        db.close();
        openList();
    }

    private void openList(){
        Intent intent = new Intent(this, mylistFragment.class);
        startActivity(intent);
    }

    private void tareaTerminada() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtTarea.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ESTADO, "-1");

        db.update(Utilidades.TABLA_TAREAS, values, Utilidades.CAMPO_TAREA+"=?", parametros);
        Toast.makeText(getApplicationContext(), "Tarea completada!", Toast.LENGTH_LONG).show();
        db.close();
        openList();
    }

    private void editar() {

    }
}
