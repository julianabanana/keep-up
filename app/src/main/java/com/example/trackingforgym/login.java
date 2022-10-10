package com.example.trackingforgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trackingforgym.data.DataBase;
import com.example.trackingforgym.data.Rutine;
import com.example.trackingforgym.data.Session;
import com.example.trackingforgym.data.User;

public class login extends AppCompatActivity {
    EditText userPasssword;
    EditText userEmail;
    TextView btnDirectRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPasssword=(EditText) findViewById(R.id.userPassword);
        userEmail =(EditText) findViewById(R.id.userEmail);
        btnDirectRegistro=(TextView) findViewById(R.id.btnDirectRegistro);

        btnDirectRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirLogin = new Intent(login.this, Registro.class);
                startActivity(abrirLogin);
            }
        });
    }

    public void ingresar(View view) {
        try{
            User usuario = DataBase.getUser(userEmail.getText().toString(),userPasssword.getText().toString());
            usuario.rutinas=DataBase.getRutinasUser(usuario.getId());
            usuario.entrenamientos=DataBase.getEntrenamientos(usuario.getId());
            usuario.ejercicios=DataBase.getEjerciciosUsuario(usuario.getId());
            usuario.stats=DataBase.getLastStat(usuario.getId());
            Session.setUser(usuario);
            Session.setSession(true);
            System.out.println(Session.getUser().getId());
            if(Session.getUser().getId()>0){
                finish();
            }
        }catch (Exception e){
            Toast toast = Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
            System.out.println(e.toString());
            toast.show();
        }

    }
    /*
    public void Siguiente(View view){
        Intent siguiente = new Intent(this, registro_por_rutina.class);
        startActivity(siguiente);
    }
    */
}