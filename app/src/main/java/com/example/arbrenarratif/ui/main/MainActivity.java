package com.example.arbrenarratif.ui.main;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.data.model.Choice;
import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.data.repository.StoryRepository;
import com.example.arbrenarratif.injection.ViewModelFactory;
import com.example.arbrenarratif.ui.end.EndActivity;
import com.example.arbrenarratif.viewModel.StoryViewModel;

public class MainActivity extends AppCompatActivity {

    private StoryViewModel viewModel;
    private TextView storyTextView;
    private LinearLayout choicesLayout;
    private Animation pulseAnimation;
    private ProgressBar scoreGauge;
    private ImageView backgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Références aux vues
        backgroundImageView = findViewById(R.id.backgroundImageView);
        storyTextView = findViewById(R.id.storyTextView);
        choicesLayout = findViewById(R.id.choicesLayout);
        scoreGauge = findViewById(R.id.scoreGauge);

        // Charger l'animation de pulse
        pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse);

        StoryRepository repository = new StoryRepository();
        viewModel = new ViewModelProvider(this, new ViewModelFactory(repository)).get(StoryViewModel.class);

        // Observer les changements de nœud
        viewModel.getCurrentNode().observe(this, storyNode -> {
            if (storyNode != null) {
                updateStoryNode(storyNode);
                updateBackgroundImage(storyNode.getId());
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

        // Observer les changements de score pour mettre à jour la jauge
        viewModel.getEcoScore().observe(this, this::updateGauge);

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

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(16, 16, 16, 16);
            button.setLayoutParams(layoutParams);

            button.setPadding(16, 16, 16, 16);
            button.setAllCaps(false);
            button.setTextSize(20f);

            button.setBackgroundResource(R.drawable.button_background_selector);
            button.setTextColor(ContextCompat.getColorStateList(this, R.color.button_text_color_selector));

            button.setOnClickListener(v -> {
                animateButtonClick(v, choice);
            });

            choicesLayout.addView(button);
        }
    }

    private void updateBackgroundImage(int nodeId) {
        String imageName = "node_" + nodeId;
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (resId != 0) {
            backgroundImageView.setImageResource(resId);
        } else {
            backgroundImageView.setImageResource(R.drawable.splash_background);
        }
    }

    private void updateGauge(int score) {
        int maxScore = 20;
        int minScore = -20;

        if (score > maxScore) score = maxScore;
        if (score < minScore) score = minScore;

        int mappedProgress = score + 20;

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(scoreGauge, "progress", scoreGauge.getProgress(), mappedProgress);
        progressAnimator.setDuration(800);
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();

        if (score < 0) {
            scoreGauge.setProgressTintList(ContextCompat.getColorStateList(this, R.color.red));
        } else if (score > 0) {
            scoreGauge.setProgressTintList(ContextCompat.getColorStateList(this, R.color.green));
        } else {
            scoreGauge.setProgressTintList(ContextCompat.getColorStateList(this, R.color.white));
        }
    }

    private void animateButtonClick(View view, Choice choice) {
        setChoicesEnabled(false);

        choicesLayout.startAnimation(pulseAnimation);

        pulseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                choicesLayout.clearAnimation();
                viewModel.selectChoice(choice);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
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