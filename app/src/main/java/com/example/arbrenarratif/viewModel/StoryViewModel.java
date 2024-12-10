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
    private StoryNode lastNode;
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
        int currentScore = ecoScore.getValue() != null ? ecoScore.getValue() : 0;
        currentScore += choice.getScore();
        ecoScore.setValue(currentScore);

        if (nextNode == null || nextNode.getChoices() == null || nextNode.getChoices().isEmpty()) {
            // Fin de l'histoire
            lastNode = nextNode;
            currentNode.setValue(null);
        } else {
            lastNode = nextNode;
            currentNode.setValue(nextNode);
        }
    }

    public void resetStory(Context context) {
        ecoScore.setValue(0);
        repository.loadStory(context);
        currentNode.setValue(repository.getNodeById(START_NODE_ID));
    }

    public String getLastNodeText() {
        return (lastNode != null) ? lastNode.getText() : "";
    }
}
