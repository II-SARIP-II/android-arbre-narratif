package com.example.arbrenarratif.data.model;

public class Choice {
    private String text;
    private int nextNode;

    public Choice(String text, int nextNode) {
        this.text = text;
        this.nextNode = nextNode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNextNode() {
        return nextNode;
    }

    public void setNextNode(int nextNode) {
        this.nextNode = nextNode;
    }
}
