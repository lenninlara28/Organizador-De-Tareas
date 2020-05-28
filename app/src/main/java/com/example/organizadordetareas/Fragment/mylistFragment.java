package com.example.organizadordetareas.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizadordetareas.ConexionSqLiteHelper;
import com.example.organizadordetareas.DetalleTask;
import com.example.organizadordetareas.MyAdapter;
import com.example.organizadordetareas.R;
import com.example.organizadordetareas.entidades.tareas;
import com.example.organizadordetareas.utilidades.Utilidades;

import java.util.ArrayList;

public class mylistFragment extends AppCompatActivity implements View.OnClickListener{
    ListView listViewTareas;
    ArrayList<String> listaInformacion;
    ArrayList<tareas> listaTareas;
    ConexionSqLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list_fragment);

        conn = new ConexionSqLiteHelper(getApplicationContext(), "db_tareas", null, 1);
        listViewTareas = (ListView) findViewById(R.id.listViewTareas);

        consultarLista();

        MyAdapter myAdapter = new MyAdapter(this, R.layout.vista_tareas_principal, listaInformacion);
        listViewTareas.setAdapter(myAdapter);

        listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mylistFragment.this, DetalleTask.class);
                intent.putExtra("objetoData", listaTareas.get(position));
                startActivity(intent);
            }
        });

    }

    private void consultarLista() {
        SQLiteDatabase db = conn.getReadableDatabase();
        tareas task = null;
        listaTareas = new ArrayList<tareas>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_TAREAS+ " WHERE "+Utilidades.CAMPO_ESTADO+" = 1", null);

        while(cursor.moveToNext()){
            task = new tareas();
            task.setTarea(cursor.getString(0));
            task.setFecha(cursor.getString(1));
            task.setHora(cursor.getString(2));
            task.setEstado(cursor.getString(3));
            listaTareas.add(task);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i<listaTareas.size(); i++){
            listaInformacion.add("Tarea: "+ listaTareas.get(i).getTarea()+
                                 "\nFecha: "+ listaTareas.get(i).getFecha()+
                                 "\nHora: "+ listaTareas.get(i).getHora());
        }
    }

    @Override
    public void onClick(View v) {

    }

}
