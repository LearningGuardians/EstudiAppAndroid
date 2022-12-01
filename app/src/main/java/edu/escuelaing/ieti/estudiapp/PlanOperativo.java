package edu.escuelaing.ieti.estudiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlanOperativo extends AppCompatActivity {

    /**
     * Funcion main de la actividad PlanOperativo.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_operativo);
        nextPage();
    }

    /**
     * Funcion generada para redirigir hacia la actividad de creacion de plan de estudios al cliente.
     * Esto mediante un listener, el cual revisa si se ha oprimido el botón.
     */
    private void nextPage(){
        Button nextButton = (Button) findViewById(R.id.PlanEstudioButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlanOperativo.this,PlanEstudio_Create.class));
            }
        });
    }
}

