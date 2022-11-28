package edu.escuelaing.ieti.estudiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalTime;

public class start_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        TextView nameTextStart = findViewById(R.id.nombreTextStart);
        TextView materiaTextStart = findViewById(R.id.materiaTextStart);
        TextView horaTextStart = findViewById(R.id.horaTextStart);
        Intent intent = getIntent();
        nameTextStart.setText("Nombre: "+intent.getStringExtra("name_key2"));
        materiaTextStart.setText("Materia: "+intent.getStringExtra("materia_key2"));
        horaTextStart.setText("hora de Comienzo: "+intent.getStringExtra("hora_key2"));
    }

}