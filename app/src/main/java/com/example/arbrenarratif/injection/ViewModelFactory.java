package com.example.arbrenarratif.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arbrenarratif.data.repository.StoryRepository;
import com.example.arbrenarratif.viewModel.StoryViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final StoryRepository repository;

    public ViewModelFactory(StoryRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StoryViewModel.class)) {
            return (T) new StoryViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
