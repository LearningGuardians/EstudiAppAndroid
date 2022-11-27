package edu.escuelaing.ieti.estudiapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.time.LocalTime;
import java.util.Locale;

import edu.escuelaing.ieti.estudiapp.entities.PlanOperativo;
import edu.escuelaing.ieti.estudiapp.ia.LearningIA;

public class PlanEstudio_Create extends AppCompatActivity {

    //Variables globales,
    Button timeButton, secondTimeButton;
    MaterialButton refuerzoButton,evaluacionButton,quizButton;
    EditText studyPlan;
    int hourArrive,minuteArrive;
    int hourStart, minuteStart;
    MaterialCardView spanishCardView,mathCardView,englishCardView;
    String cardSelectd,reasonSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_estudio_create);
        //Listeners de botones de hora
        timeButton = findViewById(R.id.hourButton);
        secondTimeButton = findViewById(R.id.startTimeButton);

        //Traer el nombre del plan de estudio
        studyPlan = findViewById(R.id.idPlanEstudio);

        //Cards new listener
        //Adicion al listener: se hace que se guarde en una variable global cual carta escogio
         spanishCardView = findViewById(R.id.spanishCard);
         mathCardView = findViewById(R.id.matematicasCard);
         englishCardView = findViewById(R.id.inglesCard);


         //change listener spanish card
        spanishCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardSelectd = "espanol";
                spanishCardView.setChecked(!spanishCardView.isChecked());
                mathCardView.setChecked(false);
                englishCardView.setChecked(false);
            }
        });

        //change listener math card
        mathCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardSelectd = "matematicas";
                mathCardView.setChecked(!mathCardView.isChecked());
                spanishCardView.setChecked(false);
                englishCardView.setChecked(false);
            }
        });

        //change listener english card
        englishCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardSelectd = "ingles";
                englishCardView.setChecked(!englishCardView.isChecked());
                spanishCardView.setChecked(false);
                mathCardView.setChecked(false);
            }
        });

        //Listeners de botones de razon de uso del aplicativo
        refuerzoButton = findViewById(R.id.refuerzoButton);
        evaluacionButton = findViewById(R.id.evaluacionButton);
        quizButton = findViewById(R.id.quizButton);

        //Change refuerzoButton listener
        refuerzoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reasonSelected = "refuerzo";
                refuerzoButton.setChecked(true);
                evaluacionButton.setChecked(false);
                quizButton.setChecked(false);
            }
        });

        //change evaluacionButton listener
        evaluacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reasonSelected = "evaluacion";
                evaluacionButton.setChecked(true);
                refuerzoButton.setChecked(false);
                quizButton.setChecked(false);
            }
        });

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reasonSelected = "quiz";
                quizButton.setChecked(true);
                refuerzoButton.setChecked(false);
                evaluacionButton.setChecked(false);
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
                hourArrive = selectedHour;
                minuteArrive = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hourArrive, minuteArrive));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, hourArrive, minuteArrive, true);
        timePickerDialog.setTitle("Seleccionar Hora");
        timePickerDialog.show();
    }

    /**
     * Funcion generada para escuchar la hora que escoja el usuario para empezar a estudiar
     * @param view
     */
    public void timePick2(View view){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hourStart = selectedHour;
                minuteStart = selectedMinute;
                secondTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hourStart, minuteStart));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, hourStart, minuteStart, true);
        timePickerDialog.setTitle("Seleccionar Hora");
        timePickerDialog.show();
    }

    /**
     * Funcion generada para crear el plan de estudio de acuerdo con las variables que da el usuario
     * Tambien se le pide a la inteligencia artificial que haga la particion de estudios
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createStudyPlan(View view){
        //Create Study plan
        PlanOperativo newStudyPlan = new PlanOperativo(cardSelectd,
                LocalTime.of(hourArrive,minuteArrive).toString(),
                studyPlan.getText().toString(),LocalTime.of(hourStart,minuteStart).toString(),
                reasonSelected);
        /**
        System.out.println(cardSelectd);
        System.out.println(LocalTime.of(hourArrive,minuteArrive).toString());
        System.out.println(studyPlan.getText().toString());
        System.out.println(LocalTime.of(hourStart,minuteStart).toString());
        System.out.println(reasonSelected);
         */
        LearningIA ia = new LearningIA(newStudyPlan);
        ia.start();

    }

}