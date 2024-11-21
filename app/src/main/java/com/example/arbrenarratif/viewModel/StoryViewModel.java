package com.example.arbrenarratif.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arbrenarratif.data.model.Choice;
import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.data.repository.StoryRepository;

public class StoryViewModel extends ViewModel {
    private StoryRepository repository;
    private MutableLiveData<StoryNode> currentNode = new MutableLiveData<>();

    public StoryViewModel(StoryRepository repository) {
        this.repository = repository;
    }

    public LiveData<StoryNode> getCurrentNode() {
        return currentNode;
    }

    public void startStory(Context context) {
        repository.loadStory(context);
        // Démarrer au nœud avec id = 1
        currentNode.setValue(repository.getNodeById(1));
    }

    public void selectChoice(Choice choice) {
        StoryNode nextNode = repository.getNodeById(choice.getNextNode());
        currentNode.setValue(nextNode);
    }
}
