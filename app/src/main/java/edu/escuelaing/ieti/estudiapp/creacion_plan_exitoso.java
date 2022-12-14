package edu.escuelaing.ieti.estudiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;

public class creacion_plan_exitoso extends AppCompatActivity {

    private ConcurrentHashMap<String, String> saveDataStudyPlan = new ConcurrentHashMap<>();

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
        saveDataStudyPlan.put("name",intent.getStringExtra("name_key"));
        materiaText.setText("Materia: "+intent.getStringExtra("materia_key"));
        saveDataStudyPlan.put("topic",intent.getStringExtra("materia_key"));
        razonText.setText("Razón: "+intent.getStringExtra("razon_key"));
        saveDataStudyPlan.put("reason",intent.getStringExtra("razon_key"));
        horaText.setText("Hora de Comienzo: "+intent.getStringExtra("hora_key"));
        saveDataStudyPlan.put("hour",intent.getStringExtra("hora_key"));
        backToCreate();
    }
    private void backToCreate(){
        Button backButton = (Button) findViewById(R.id.buttonBackID);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(creacion_plan_exitoso.this,PlanEstudio_Create.class));
            }
        });
    }


    /**
     * Funcion generada para enviar los datos del plan operativo hacia la actividad de comienzo de plan de estudio
     * @param view
     */
    public void sendDataPE(View view){
        Intent intent = new Intent(getApplicationContext(),start_activity.class);
        intent.putExtra("name_key2",saveDataStudyPlan.get("name"));
        intent.putExtra("materia_key2",saveDataStudyPlan.get("topic"));
        intent.putExtra("hora_key2",saveDataStudyPlan.get("hour"));
        intent.putExtra("razon_key2",saveDataStudyPlan.get("reason"));
        startActivity(intent);
    }
}