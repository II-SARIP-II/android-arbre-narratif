package com.example.arbrenarratif.ui.end;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import com.example.arbrenarratif.R;
import com.example.arbrenarratif.ui.main.MainActivity;

public class EndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
