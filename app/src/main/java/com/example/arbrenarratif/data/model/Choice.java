package com.example.arbrenarratif.data.model;

public class Choice {
    private String text;
    private int nextNode;

    private int score;

    // Constructeur par défaut requis pour Gson
    public Choice() {
    }

    // Getters
    public String getText() {
        return text;
    }

    public int getNextNode() {
        return nextNode;
    }

    // Setters
    public void setText(String text) {
        this.text = text;
    }

    public void setNextNode(int nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "text='" + text + '\'' +
                ", nextNode=" + nextNode +
                '}';
    }

    public int getScore() {
        return score;
    }
}
