package edu.escuelaing.ieti.estudiapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import edu.escuelaing.ieti.estudiapp.dtos.PlanOperativoDto;
import edu.escuelaing.ieti.estudiapp.entities.PlanOperativo;
import edu.escuelaing.ieti.estudiapp.ia.LearningIA;
import edu.escuelaing.ieti.estudiapp.services.PlanOperativoLocal;
import edu.escuelaing.ieti.estudiapp.services.PlanOperativoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanEstudio_Create extends AppCompatActivity {

    //Variables globales,
    Button timeButton, secondTimeButton, startButton;
    MaterialButton refuerzoButton,evaluacionButton,quizButton;
    EditText studyPlan;
    int hourArrive,minuteArrive;
    int hourStart, minuteStart;
    MaterialCardView spanishCardView,mathCardView,englishCardView;
    String cardSelectd,reasonSelected;

    //
    PlanOperativoLocal pO;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
    public PlanOperativo createStudyPlan(View view) {
        //Create Study plan
        PlanOperativo newStudyPlan = new PlanOperativo(cardSelectd,
                LocalTime.of(hourArrive, minuteArrive).toString(),
                studyPlan.getText().toString(), LocalTime.of(hourStart, minuteStart).toString(),
                reasonSelected);
        /*
        System.out.println(cardSelectd);
        System.out.println(LocalTime.of(hourArrive,minuteArrive).toString());
        System.out.println(studyPlan.getText().toString());
        System.out.println(LocalTime.of(hourStart,minuteStart).toString());
        System.out.println(reasonSelected);
         */
/*        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlanOperativoService pOperativoService = builder.create(PlanOperativoService.class);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/



        /**OkHttpClient okHttpClient = new OkHttpClient.Builder()
         .addInterceptor( loggingInterceptor )
         .addInterceptor(new AuthInterceptor(storage))
         .writeTimeout( 0, TimeUnit.MILLISECONDS )
         .readTimeout( 2, TimeUnit.MINUTES )
         .connectTimeout( 1, TimeUnit.MINUTES ).build();*/
        LearningIA ia = new LearningIA(newStudyPlan);
        /*Call<List<PlanOperativoDto>> call = pOperativoService.getAll();
        call.enqueue(new Callback<List<PlanOperativoDto>>() {
            @Override
            public void onResponse(Call<List<PlanOperativoDto>> call, Response<List<PlanOperativoDto>> response) {
                for(PlanOperativoDto po: response.body()){
                    System.out.println("POperativo:-----" + po.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PlanOperativoDto>> call, Throwable t) {
                System.out.println("Se encontro un error");
                t.printStackTrace();
            }
        });*/
        PlanOperativoLocal.create(newStudyPlan);
        PlanOperativoLocal.getAll().forEach(planOperativo -> {
            System.out.println("PlanOperativo__" + planOperativo.getNombrePlan());
        });
        ia.start();
        return null;


    }
}