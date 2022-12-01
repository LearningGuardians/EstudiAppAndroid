package edu.escuelaing.ieti.estudiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import edu.escuelaing.ieti.estudiapp.ui.login.Login;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(MainActivity.this, Login.class));
    }
}
