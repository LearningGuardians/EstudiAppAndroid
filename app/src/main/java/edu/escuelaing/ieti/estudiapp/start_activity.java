package edu.escuelaing.ieti.estudiapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import edu.escuelaing.ieti.estudiapp.entities.PlanOperativo;
import edu.escuelaing.ieti.estudiapp.ia.LearningIA;

public class start_activity extends AppCompatActivity {
    private ConcurrentHashMap<String, String> saveDataStart = new ConcurrentHashMap<>();

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
        //Insercion de datos a hashmap para guardado
        saveDataStart.put("nombre",intent.getStringExtra("name_key2"));
        saveDataStart.put("materia",intent.getStringExtra("materia_key2"));
        saveDataStart.put("razon",intent.getStringExtra("razon_key2"));
        startLater();
        startNow();
    }

    /**
     * Funcion que modifica el listener del boton para que este se vaya a la actividad donde se pide la nueva
     * hora de comienzo
     */
    private void startLater(){
        Button backButton = (Button) findViewById(R.id.laterButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(creacion_plan_exitoso.this,PlanEstudio_Create.class));
                System.out.println("EN PROGRESO...");
            }
        });
    }

    /**
     * Funcion que modifica el listener del boton para que empiece de una vez las actividades del plan de estudio
     * Esto sirve para cuando se quiere empezar antes el plan de estudio para refactorizar la partcion de tiempos
     * de estudio
     */
    private void startNow(){
        Calendar now = Calendar.getInstance();
        Button backButton = (Button) findViewById(R.id.nowButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //PRUEBA -> COMO NO SE TIENE AUN LAS PANTALLAS DE ACTIVIDADES, SE MANDA A LA PRINCIPAL DE PLAN DE ESTUDIO
                PlanOperativo refactorStudyPlan = new PlanOperativo(saveDataStart.get("materia"),
                        LocalTime.of(now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE)).toString(),
                        saveDataStart.get("nombre"),
                        saveDataStart.get("razon"));
                LearningIA ia = new LearningIA(refactorStudyPlan);
                ia.start();
                startActivity(new Intent(start_activity.this,PlanOperativoView.class));
            }
        });
    }

}