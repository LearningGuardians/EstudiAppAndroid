package edu.escuelaing.ieti.estudiapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.escuelaing.ieti.estudiapp.PlanEstudio_Create;
import edu.escuelaing.ieti.estudiapp.R;
import edu.escuelaing.ieti.estudiapp.data.model.User;
import edu.escuelaing.ieti.estudiapp.data.model.UserApi;
import edu.escuelaing.ieti.estudiapp.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {


    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backToCreate();
    }

    private void backToCreate(){
        Button backButton = (Button) findViewById(R.id.login);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, PlanEstudio_Create.class));
            }
        });
    }

    private void getUsers(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:/localhost:8080/")//En esta linea se indica la URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserApi userApi = retrofit.create(UserApi.class);
        Call<List<User>> users = userApi.getUsers();
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                //backToCreate
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                CharSequence textoInvalido = "Correo o contraseña invalidos";
                Toast.makeText(getApplicationContext(),textoInvalido,Toast.LENGTH_LONG).show();
            }
        });
    }

}