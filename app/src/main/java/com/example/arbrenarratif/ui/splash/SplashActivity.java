package com.example.arbrenarratif.ui.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.ui.main.MainActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private View sliderContainer;
    private View draggableView;
    private int sliderMaxWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sliderContainer = findViewById(R.id.sliderContainer);
        draggableView = findViewById(R.id.draggable_view);

        draggableView.setOnTouchListener(new TouchListener());
        draggableView.setClickable(true);

        // Calculer la largeur maximale pour le déplacement
        sliderContainer.post(() -> {
            sliderMaxWidth = sliderContainer.getWidth() - draggableView.getWidth();
        });
    }

    private class TouchListener implements View.OnTouchListener {

        private float initPosX;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    // Récupérer la position initiale
                    initPosX = event.getRawX() - v.getX();
                    break;

                case MotionEvent.ACTION_MOVE:
                    // Calculer la nouvelle position
                    float newPosX = event.getRawX() - initPosX;
                    if (newPosX < 0) newPosX = 0; // Limite gauche
                    if (newPosX > sliderMaxWidth) newPosX = sliderMaxWidth; // Limite droite

                    // Déplacer le slider
                    v.setX(newPosX);
                    break;

                case MotionEvent.ACTION_UP:
                    // Vérifier si le slider est complètement à droite
                    if (v.getX() >= sliderMaxWidth) {
                        startMainActivity();
                    } else {
                        // Réinitialiser la position si non terminé
                        v.animate()
                                .x(20) // Position initiale
                                .setDuration(300)
                                .start();
                    }
                    break;
            }
            return true;
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
