package com.example.arbrenarratif.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.data.model.Choice;
import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.data.repository.StoryRepository;
import com.example.arbrenarratif.injection.ViewModelFactory;
import com.example.arbrenarratif.ui.end.EndActivity;
import com.example.arbrenarratif.utils.JsonHelper;
import com.example.arbrenarratif.viewModel.StoryViewModel;

public class MainActivity extends AppCompatActivity {

    private StoryViewModel viewModel;
    private TextView storyTextView;
    private LinearLayout choicesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storyTextView = findViewById(R.id.storyTextView);
        choicesLayout = findViewById(R.id.choicesLayout);

        StoryRepository repository = new StoryRepository();
        viewModel = new ViewModelProvider(this, new ViewModelFactory(repository)).get(StoryViewModel.class);

        // On réinitialise l’histoire avant de la lancer
        viewModel.resetStory(this);
        viewModel.startStory(this);

        viewModel.getCurrentNode().observe(this, storyNode -> {
            if (storyNode != null) {
                updateStoryNode(storyNode);
            } else {
                String finalText = viewModel.getLastNodeText(); // Méthode à créer dans le ViewModel
                Intent intent = new Intent(MainActivity.this, EndActivity.class);
                intent.putExtra("finalNodeText", finalText);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateStoryNode(StoryNode node) {
        storyTextView.setText(node.getText());
        choicesLayout.removeAllViews();

        for (Choice choice : node.getChoices()) {
            Button button = new Button(this);
            button.setText(choice.getText());
            button.setOnClickListener(v -> {
                viewModel.selectChoice(choice);
            });
            choicesLayout.addView(button);
        }
    }

}