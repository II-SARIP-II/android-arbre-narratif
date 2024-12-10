package com.example.arbrenarratif.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.data.model.Choice;
import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.data.repository.StoryRepository;
import com.example.arbrenarratif.injection.ViewModelFactory;
import com.example.arbrenarratif.viewModel.StoryViewModel;
import com.example.arbrenarratif.ui.end.EndActivity;

public class MainActivity extends AppCompatActivity {

    private StoryViewModel viewModel;
    private TextView storyTextView;
    private LinearLayout choicesLayout;
    private ConstraintLayout rootLayout;
    private Animation pulseAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Références aux vues
        rootLayout = findViewById(R.id.rootLayout);
        storyTextView = findViewById(R.id.storyTextView);
        choicesLayout = findViewById(R.id.choicesLayout);

        // Charger l'animation de pulse
        pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse);

        StoryRepository repository = new StoryRepository();
        viewModel = new ViewModelProvider(this, new ViewModelFactory(repository)).get(StoryViewModel.class);

        viewModel.getCurrentNode().observe(this, storyNode -> {
            if (storyNode != null) {
                updateStoryNode(storyNode);
            } else {
                String finalText = viewModel.getLastNodeText();
                int finalScore = viewModel.getEcoScore().getValue() != null ? viewModel.getEcoScore().getValue() : 0;

                Intent intent = new Intent(MainActivity.this, EndActivity.class);
                intent.putExtra("finalNodeText", finalText);
                intent.putExtra("finalScore", finalScore);
                startActivity(intent);
                finish();
            }
        });

        // Réinitialiser et démarrer l'histoire
        viewModel.resetStory(this);
        viewModel.startStory(this);
    }

    private void updateStoryNode(StoryNode node) {
        storyTextView.setText(node.getText());
        choicesLayout.removeAllViews();

        for (Choice choice : node.getChoices()) {
            Button button = new Button(this);
            button.setText(choice.getText());

            // Crée des LayoutParams avec des marges
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(16, 16, 16, 16); // Marges en pixels (gauche, haut, droite, bas)
            button.setLayoutParams(layoutParams);

            button.setPadding(16, 16, 16, 16); // Padding
            button.setAllCaps(false);
            button.setTextSize(20f);

            // Appliquer les sélecteurs de fond et de texte
            button.setBackgroundResource(R.drawable.button_background_selector); // Sélecteur de fond
            button.setTextColor(ContextCompat.getColorStateList(this, R.color.button_text_color_selector)); // Sélecteur de couleur du texte

            // Définir l'OnClickListener avec animation de pulse
            button.setOnClickListener(v -> {
                animateButtonClick(v, choice);
            });

            choicesLayout.addView(button);
        }
    }

    private void animateButtonClick(View view, Choice choice) {
        // Désactiver tous les boutons pendant l'animation
        setChoicesEnabled(false);

        // Appliquer l'animation de pulse sur le rootLayout
        rootLayout.startAnimation(pulseAnimation);

        // Définir un listener pour détecter la fin de l'animation
        pulseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Rien à faire ici
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Réinitialiser l'animation (au cas où)
                rootLayout.clearAnimation();

                // Passer au choix sélectionné
                viewModel.selectChoice(choice);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Rien à faire ici
            }
        });
    }

    private void setChoicesEnabled(boolean enabled) {
        int childCount = choicesLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = choicesLayout.getChildAt(i);
            child.setEnabled(enabled);
        }
    }
}