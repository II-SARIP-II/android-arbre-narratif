package com.example.arbrenarratif.ui.end;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.ui.main.MainActivity;

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        String finalNodeText = getIntent().getStringExtra("finalNodeText");
        int finalScore = getIntent().getIntExtra("finalScore", 0);

        TextView endTextView = findViewById(R.id.endTextView);
        TextView scoreTextView = findViewById(R.id.scoreTextView);

        endTextView.setText(finalNodeText);

        String appreciation;
        if (finalScore >= 5) {
            appreciation = "Tu as été très écoresponsable !";
        } else if (finalScore >= 0) {
            appreciation = "Tu as fait quelques efforts, mais peux mieux faire.";
        } else {
            appreciation = "Tes choix ont été assez nocifs pour l’environnement...";
        }

        scoreTextView.setText("Score Éco : " + finalScore + "\n" + appreciation);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

}
