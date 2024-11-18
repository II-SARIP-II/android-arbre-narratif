package com.example.arbrenarratif.data.model;

import java.util.ArrayList;
import java.util.List;

public class StoryNode {
    private int ID;
    private String text;
    private List<Choice> choices;

    public StoryNode(int id, String text){
        this.ID = id;
        this.text = text;
        this.choices = new ArrayList<>();
    }

    public int getID(){
        return this.ID;
    }
    public String getText(){
        return this.text;
    }
    public List<Choice> getChoices(){
        return choices;
    }
    public void setID(int id){
        this.ID = id;
    }
    public void setText(String text){
        this.text = text;
    }
    public void setChoices(List<Choice> choices){
        this.choices = choices;
    }
    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }
    @Override
    public String toString() {
        return "StoryNode{" +
                "id=" + this.ID +
                ", text='" + this.text + '\'' +
                ", choices=" + this.choices +
                '}';
    }

}
