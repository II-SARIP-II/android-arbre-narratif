package com.example.arbrenarratif.ui.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.arbrenarratif.R;
import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.viewModel.StoryViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StoryViewModel viewModel = new ViewModelProvider(this).get(StoryViewModel.class);

        StoryNode currentNode = viewModel.getCurrentNode();
        System.out.println("Texte : " + currentNode.getText());

        viewModel.goToNextNode(currentNode.getChoices().get(0).getNextNode());
        StoryNode nextNode = viewModel.getCurrentNode();
        System.out.println("Texte suivant : " + nextNode.getText());
    }
}