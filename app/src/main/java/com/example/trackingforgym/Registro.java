package com.example.trackingforgym;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackingforgym.data.DataBase;

public class Registro extends AppCompatActivity {
    EditText nombre;
    EditText correo;
    EditText clave;
    EditText clave0;
    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = (EditText) findViewById(R.id.registro_nombre);
        correo = (EditText) findViewById(R.id.registro_correo);
        clave = (EditText) findViewById(R.id.registroClave);
        clave0 = (EditText) findViewById(R.id.registroClave0);
    }

    public void addUser(View view) {
        if(clave.getText().toString().equals(clave0.getText().toString()))
        response= DataBase.addUser(nombre.getText().toString(),correo.getText().toString(),clave.getText().toString());
        if(response.equals("\"succes\""))
            finish();
        else if(response.equals("\"already exists\"")){
            Toast toast = Toast.makeText(this, "ya está en uso este correo", Toast.LENGTH_LONG);
            toast.show();
        }
            Toast toast = Toast.makeText(this, response, Toast.LENGTH_LONG);
        toast.show();
        //System.out.println("oprimido");
    }
}