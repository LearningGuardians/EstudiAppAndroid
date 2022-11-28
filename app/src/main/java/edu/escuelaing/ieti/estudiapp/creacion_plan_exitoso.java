package edu.escuelaing.ieti.estudiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class creacion_plan_exitoso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_plan_exitoso);
        TextView nameText = findViewById(R.id.nombreTextView);
        TextView materiaText = findViewById(R.id.materiaTextView);
        TextView razonText = findViewById(R.id.razonTextView);
        TextView horaText = findViewById(R.id.horaTextView);
        Intent intent = getIntent();
        nameText.setText("Nombre: "+intent.getStringExtra("name_key"));
        materiaText.setText("Materia: "+intent.getStringExtra("materia_key"));
        razonText.setText("Razón: "+intent.getStringExtra("razon_key"));
        horaText.setText("Hora de Comienzo: "+intent.getStringExtra("hora_key"));
    }
}