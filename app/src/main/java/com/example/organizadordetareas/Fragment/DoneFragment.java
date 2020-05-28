package com.example.organizadordetareas.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.organizadordetareas.ConexionSqLiteHelper;
import com.example.organizadordetareas.MyAdapter;
import com.example.organizadordetareas.R;
import com.example.organizadordetareas.entidades.tareas;
import com.example.organizadordetareas.utilidades.Utilidades;

import java.util.ArrayList;

public class DoneFragment extends AppCompatActivity implements View.OnClickListener {
    ListView listViewTareas;
    ArrayList<String> listaInformacion;
    ArrayList<tareas> listaTareas;
    ConexionSqLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_fragment);

        conn = new ConexionSqLiteHelper(getApplicationContext(), "db_tareas", null, 1);
        listViewTareas = (ListView) findViewById(R.id.listViewTareas);

        consultarLista();

        MyAdapter myAdapter = new MyAdapter(this, R.layout.vista_tareas_principal, listaInformacion);
        listViewTareas.setAdapter(myAdapter);
    }

    private void consultarLista() {
        SQLiteDatabase db = conn.getReadableDatabase();
        tareas task = null;
        listaTareas = new ArrayList<tareas>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_TAREAS+ " WHERE "+Utilidades.CAMPO_ESTADO+" = -1", null);

        while(cursor.moveToNext()){
            task = new tareas();
            task.setTarea(cursor.getString(0));
            task.setFecha(cursor.getString(1));
            task.setHora(cursor.getString(2));
            listaTareas.add(task);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i<listaTareas.size(); i++){
            listaInformacion.add("Tarea: "+ listaTareas.get(i).getTarea()+
                    "\nFecha: "+ listaTareas.get(i).getFecha()+
                    "\nHora: "+ listaTareas.get(i).getHora() + "\n");
        }
    }

    @Override
    public void onClick(View v) {

    }
}
