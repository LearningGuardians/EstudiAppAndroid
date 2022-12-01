package edu.escuelaing.ieti.estudiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Landing extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

    }
    public void goToPlans(View view) {
        startActivity(new Intent(Landing.this , PlanOperativoView.class));

    }

    public void goToCreatePlans(View view) {
        startActivity(new Intent(Landing.this , PlanEstudio_Create.class));
    }

}
