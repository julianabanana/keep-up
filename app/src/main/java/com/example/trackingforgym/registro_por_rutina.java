package com.example.trackingforgym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;

import com.example.trackingforgym.data.DataBase;
import com.example.trackingforgym.data.Ejercicio;
import com.example.trackingforgym.data.Rutine;
import com.example.trackingforgym.data.RutineHistoric;
import com.example.trackingforgym.data.Serie;
import com.example.trackingforgym.data.Session;

import java.util.ArrayList;
import java.util.List;

public class registro_por_rutina extends AppCompatActivity {

    List<Lista_Rutinas> elements;
    Rutine rutina;
    Button finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_por_rutina);
        rutina = (Rutine) getIntent().getSerializableExtra("rutina");
        Session.getUser().nuevoEntrenamiento= new RutineHistoric(0);
        Session.getUser().nuevoEntrenamiento.crearFecha();
        Session.getUser().nuevoEntrenamiento.idPadre=rutina.getId();
        Session.getUser().nuevoEntrenamiento.setColor(rutina.getColor());
        Session.getUser().nuevoEntrenamiento.setNombre(rutina.getNombre());
        init();
        finalizar= (Button) findViewById(R.id.buttonFinalizarEntrenamiento);
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase.addEntrenamiento(Session.getUser().nuevoEntrenamiento);
                Session.getUser().nuevoEntrenamiento.setId(DataBase.getEntrenamiento(Session.getUser().nuevoEntrenamiento).getId());
                Session.getUser().entrenamientos.add(Session.getUser().nuevoEntrenamiento);
                for(Serie a: Session.getUser().nuevoEntrenamiento.series){
                    DataBase.addSerie(Session.getUser().nuevoEntrenamiento.getId(),a);
                }
                finish();
            }
        });
    }

    public void init(){
        elements = new ArrayList<>();
        elements.add(new Lista_Rutinas("FF2D00", "Flexiones con palmada", "Tren superior", "Fuerte"));
        elements.add(new Lista_Rutinas("0008FF", "Press con mancuernas", "Tren superior", "Fuerte"));
        elements.add(new Lista_Rutinas("59FF00", "Tríceps con mancuerna", "Tren superior", "Fuerte"));
        elements.add(new Lista_Rutinas("008FFF", "Curl de bíceps con mancuernas", "Tren superior", "Fuerte"));
        elements.add(new Lista_Rutinas("F000FF", "Elevaciones frontales con mancuernas", "Tren superior", "Fuerte"));

        Rutinas_Adaptador rutinas_adaptador = new Rutinas_Adaptador(rutina.ejercicios, this, new Rutinas_Adaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Ejercicio item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_rutinas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rutinas_adaptador);

    }

    public void moveToDescription(Ejercicio item){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Lista_Rutinas",item);
        startActivity(intent);
    }
}