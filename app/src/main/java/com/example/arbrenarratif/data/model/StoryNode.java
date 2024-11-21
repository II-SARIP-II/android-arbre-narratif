package com.example.arbrenarratif.data.model;

import java.util.List;

public class StoryNode {
    private int id;
    private String text;
    private List<Choice> choices;

    // Constructeur par défaut requis pour Gson
    public StoryNode() {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "StoryNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", choices=" + choices +
                '}';
    }
}
