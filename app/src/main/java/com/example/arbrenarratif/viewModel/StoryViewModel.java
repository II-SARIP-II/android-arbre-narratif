package com.example.arbrenarratif.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.data.repository.StoryRepository;

public class StoryViewModel extends AndroidViewModel {
    private StoryRepository repository;
    private StoryNode currentNode;

    public StoryViewModel(Application application) {
        super(application);
        repository = new StoryRepository();
        repository.loadStory(application); // Charger les données depuis JSON
        currentNode = repository.getNodeById(1); // Démarrer avec le premier nœud
    }

    // Obtenir le nœud actuel
    public StoryNode getCurrentNode() {
        return currentNode;
    }

    // Naviguer vers le prochain nœud
    public void goToNextNode(int nextNodeId) {
        currentNode = repository.getNodeById(nextNodeId);
    }
}
