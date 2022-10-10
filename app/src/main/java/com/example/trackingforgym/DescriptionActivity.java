package com.example.trackingforgym;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trackingforgym.data.Ejercicio;
import com.example.trackingforgym.data.Serie;
import com.example.trackingforgym.data.Session;

public class DescriptionActivity extends AppCompatActivity {

    TextView titleDescriptionTextView;
    TextView cityDescriptionTextView;
    TextView statusDescriptionTextView;
    Button subirSerie;
    EditText numREps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Ejercicio element = (Ejercicio) getIntent().getSerializableExtra("Lista_Rutinas");
        titleDescriptionTextView = findViewById(R.id.titleDescriptionTextView);
        numREps=(EditText) findViewById(R.id.Num_repeticiones);
        cityDescriptionTextView = findViewById(R.id.cityDescriptionTextView);
        statusDescriptionTextView = findViewById(R.id.statusDescriptionTextView);

        titleDescriptionTextView.setText(element.getNombre());
        titleDescriptionTextView.setTextColor(Color.parseColor("#"+element.getColor()));

        cityDescriptionTextView.setText(element.getNombre());

        statusDescriptionTextView.setText(element.getParteCuerpo());
        statusDescriptionTextView.setTextColor(Color.GRAY);
        subirSerie = (Button) findViewById(R.id.btnAgregarEjercicioEntrenamiento);
        subirSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session.getUser().nuevoEntrenamiento.series.add(new Serie(element.getId(), element.getNombre(), element.getColor(), element.getParteCuerpo(), Integer.parseInt(numREps.getText().toString()), element.getId()));
                finish();
            }
        });
    }
}