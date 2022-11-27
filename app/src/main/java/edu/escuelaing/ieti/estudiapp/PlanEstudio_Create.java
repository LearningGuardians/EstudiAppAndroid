package edu.escuelaing.ieti.estudiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.android.material.card.MaterialCardView;

import java.util.Locale;

public class PlanEstudio_Create extends AppCompatActivity {

    Button timeButton;
    int hour,minute;
    MaterialCardView spanishCardView,mathCardView,englishCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_estudio_create);
        timeButton = findViewById(R.id.hourButton);

        //Cards new listener
         spanishCardView = findViewById(R.id.spanishCard);
         mathCardView = findViewById(R.id.matematicasCard);
         englishCardView = findViewById(R.id.inglesCard);


         //change listener
        spanishCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spanishCardView.setChecked(!spanishCardView.isChecked());
                mathCardView.setChecked(false);
                englishCardView.setChecked(false);
            }
        });

        mathCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mathCardView.setChecked(!mathCardView.isChecked());
                spanishCardView.setChecked(false);
                englishCardView.setChecked(false);
            }
        });

        englishCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                englishCardView.setChecked(!englishCardView.isChecked());
                spanishCardView.setChecked(false);
                mathCardView.setChecked(false);
            }
        });
    }


    /**
     * Funcion genereada para escuchar la hora que escogio el estudiante en la cual va a llegar a la casa.
     * @param view
     */
    public void timePick(View view) {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Seleccionar Hora");
        timePickerDialog.show();
    }

}