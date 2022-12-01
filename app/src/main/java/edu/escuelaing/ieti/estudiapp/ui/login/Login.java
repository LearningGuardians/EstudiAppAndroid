package edu.escuelaing.ieti.estudiapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.escuelaing.ieti.estudiapp.PlanEstudio_Create;
import edu.escuelaing.ieti.estudiapp.R;
import edu.escuelaing.ieti.estudiapp.data.model.User;
import edu.escuelaing.ieti.estudiapp.data.model.UserApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {


    EditText e1,e2;
    TextView error;
    String email, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = findViewById(R.id.username);
        e2 = findViewById(R.id.password);
        //backToCreate();
        error = findViewById(R.id.textView2);

    }



    public void checkUser(View view) {

        email = e1.getText().toString();
        password = e2.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-44-202-136-239.compute-1.amazonaws.com:8080")  //En esta linea se indica la URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);
        User user = new User(email,password);
        Call<User> call = userApi.verifyEmail(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                CharSequence situacion = "usuario o contraseña erroneos ";

                if (response.isSuccessful()){
                    startActivity(new Intent(Login.this, PlanEstudio_Create.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),situacion,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}