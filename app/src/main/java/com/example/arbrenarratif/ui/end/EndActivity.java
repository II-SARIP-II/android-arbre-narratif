package com.example.arbrenarratif.ui.end;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.ui.main.MainActivity;

public class EndActivity extends AppCompatActivity {

    private TextView endTextView;
    private TextView scoreTextView;
    private TextView appreciationTextView;
    private ImageView scoreImageView;
    private ProgressBar ecoProgressBar;
    private Button restartButton;
    private Button detailsButton;

    @SuppressLint("UseCompatLoadingForColorStateLists")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        // Références aux vues
        endTextView = findViewById(R.id.endTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        appreciationTextView = findViewById(R.id.appreciationTextView);
        scoreImageView = findViewById(R.id.backgroundImage);
        ecoProgressBar = findViewById(R.id.ecoProgressBar);
        restartButton = findViewById(R.id.restartButton);
        detailsButton = findViewById(R.id.detailsButton);

        // Récupérer les données passées via Intent
        String finalNodeText = getIntent().getStringExtra("finalNodeText");
        int finalScore = getIntent().getIntExtra("finalScore", 0);
        int finalNodeId = getIntent().getIntExtra("finalNodeId", 0);

        // Afficher le texte de fin
        endTextView.setText(finalNodeText);

        // Afficher le score
        scoreTextView.setText(getString(R.string.score_text, finalScore));

        // Configurer la ProgressBar
        int maxScore = 20;
        int minScore = -20;

        int normalizedScore = finalScore - minScore;
        ecoProgressBar.setMax(maxScore - minScore);
        ecoProgressBar.setProgress(normalizedScore);

        if (finalScore > 0) {
            ecoProgressBar.setProgressTintList(getResources().getColorStateList(R.color.hunterGreen));
        } else if (finalScore < 0) {
            ecoProgressBar.setProgressTintList(getResources().getColorStateList(R.color.red));
        } else {
            ecoProgressBar.setProgressTintList(getResources().getColorStateList(R.color.gray));
        }

        String imageName = "node_" + finalNodeId;
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (imageResId != 0) {
            scoreImageView.setImageResource(imageResId);
        } else {
            // Image par défaut si aucune correspondance
            scoreImageView.setImageResource(R.drawable.splash_background);
        }

        // Définir le texte d'appréciation basé sur le score
        String appreciation = getAppreciationText(finalScore);
        appreciationTextView.setText(appreciation);

        // Configurer le bouton de recommencement
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        // Configurer le bouton des détails
        detailsButton.setOnClickListener(v -> showDetailsDialog(finalScore));
    }

    /**
     * Renvoie un texte d'appréciation basé sur le score.
     *
     * @param finalScore Le score final de l'utilisateur.
     * @return Texte d'appréciation.
     */
    private String getAppreciationText(int finalScore) {
        if (finalScore >= 15) {
            return "Tu as été extrêmement écoresponsable !";
        } else if (finalScore >= 5) {
            return "Tu as fait quelques efforts écoresponsables.";
        } else {
            return "Tes choix ont été castastrophiques pour l'environnement...";
        }
    }

    /**
     * Affiche une boîte de dialogue avec des détails supplémentaires sur le score.
     *
     * @param finalScore Le score final de l'utilisateur.
     */
    private void showDetailsDialog(int finalScore) {
        String details;
        if (finalScore >= 15) {
            details = "Excellent! Tu as fait tous les choix possibles pour protéger l'environnement.\n\nVoici comment tu peux continuer à agir :";
            details += "\n• Continue à adopter des comportements écoresponsables.";
            details += "\n• Partage tes connaissances avec tes proches.";
            details += "\n• Implique-toi dans des initiatives locales.";
        } else if (finalScore >= 5) {
            details = "Bon travail! Tu as fait plusieurs choix écoresponsables, mais il y a encore des améliorations possibles.\n\nConseils pour augmenter ton score :";
            details += "\n• Réduis ta consommation de plastique.";
            details += "\n• Favorise les transports en commun ou le vélo.";
            details += "\n• Sensibilise ton entourage aux enjeux environnementaux.";
        } else {
            details = "Tes choix ont été assez nocifs pour l’environnement... Voici quelques conseils pour améliorer ton score éco :";
            details += "\n• Réduis ta consommation d'énergie.";
            details += "\n• Privilégie les produits durables et recyclables.";
            details += "\n• Participe à des actions de nettoyage ou de reboisement.";
        }

        new AlertDialog.Builder(this)
                .setTitle("Détails du Score Éco")
                .setMessage(details)
                .setPositiveButton("OK", null)
                .show();
    }
}
