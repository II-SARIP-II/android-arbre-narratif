package com.example.arbrenarratif.ui.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.ui.main.MainActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Référence au bouton "Commencer l'aventure"
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            // Rediriger vers MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
