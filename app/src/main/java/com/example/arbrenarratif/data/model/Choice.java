package com.example.arbrenarratif.data.model;

public class Choice {
    private String text;
    private int nextNodeId;

    public Choice(String text, int nextNodeId) {
        this.text = text;
        this.nextNodeId = nextNodeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(int nextNodeId) {
        this.nextNodeId = nextNodeId;
    }
}
