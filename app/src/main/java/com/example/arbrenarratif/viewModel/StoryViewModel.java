package com.example.arbrenarratif.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arbrenarratif.data.model.Choice;
import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.data.repository.StoryRepository;

public class StoryViewModel extends ViewModel {
    private StoryRepository repository;
    private MutableLiveData<StoryNode> currentNode = new MutableLiveData<>();
    private MutableLiveData<Integer> ecoScore = new MutableLiveData<>(0);
    private static final int START_NODE_ID = 1;

    public StoryViewModel(StoryRepository repository) {
        this.repository = repository;
    }

    public LiveData<StoryNode> getCurrentNode() {
        return currentNode;
    }

    public LiveData<Integer> getEcoScore() {
        return ecoScore;
    }

    public void startStory(Context context) {
        repository.loadStory(context);
        currentNode.setValue(repository.getNodeById(START_NODE_ID));
    }

    public void selectChoice(Choice choice) {
        StoryNode nextNode = repository.getNodeById(choice.getNextNode());
        if (nextNode == null || nextNode.getChoices().isEmpty()) {
            currentNode.setValue(null);
        } else {
            currentNode.setValue(nextNode);
        }
    }

    public void resetStory(Context context) {
        ecoScore.setValue(0);
        repository.loadStory(context);
        currentNode.setValue(repository.getNodeById(START_NODE_ID));
    }
}
