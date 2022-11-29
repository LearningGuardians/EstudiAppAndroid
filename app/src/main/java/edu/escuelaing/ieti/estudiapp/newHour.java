package edu.escuelaing.ieti.estudiapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Locale;

import edu.escuelaing.ieti.estudiapp.entities.PlanOperativo;
import edu.escuelaing.ieti.estudiapp.services.PlanOperativoLocal;

public class newHour extends AppCompatActivity {

    //Variables Globales
    Button newHourButton_, newNowButton;
    int hourArrive, minuteArrive;
    String pOperativo_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hour);
        Intent intent = getIntent();
        pOperativo_name = intent.getStringExtra("pOperativo_Name");
        //Listenes de botones de hora
        newHourButton_ = findViewById(R.id.newHourButton);
        newNowButton = findViewById(R.id.newNowButton);

    }

    /**
     * Funcion genereada para escuchar la hora que escogio el estudiante en la cual va a llegar a la casa.
     * @param view
     */
    public void timePickHour(View view) {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hourArrive = selectedHour;
                minuteArrive = selectedMinute;
                newHourButton_.setText(String.format(Locale.getDefault(), "%02d:%02d", hourArrive, minuteArrive));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, hourArrive, minuteArrive, true);
        timePickerDialog.setTitle("Seleccionar Hora");
        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateTime(View view){
        System.out.println("NOMBRE DEL PLAN OPERATIVO" + pOperativo_name);
        PlanOperativo pOperativo = PlanOperativoLocal.getByNombre(pOperativo_name);
        pOperativo.setHoraComienzo(String.format(Locale.getDefault(), "%02d:%02d", hourArrive, minuteArrive));
        PlanOperativoLocal.update(pOperativo,pOperativo.getId());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Actualizado")
                .setMessage("Se ha actualizado la hora de comienzo")
                .setPositiveButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(),PlanOperativoView.class);
                                startActivity(intent);
                            }
                        });
        builder.create();
        builder.show();
    }


}