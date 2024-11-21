package com.example.arbrenarratif.data.model;

import java.util.ArrayList;
import java.util.List;

public class StoryNode {
    private int Id;
    private String text;
    private List<Choice> choices;

    public StoryNode(int id, String text){
        this.Id = id;
        this.text = text;
        this.choices = new ArrayList<>();
    }

    public int getId(){
        return this.Id;
    }
    public String getText(){
        return this.text;
    }
    public List<Choice> getChoices(){
        return choices;
    }
    public void setID(int id){
        this.Id = id;
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
                "id=" + this.Id +
                ", text='" + this.text + '\'' +
                ", choices=" + this.choices +
                '}';
    }

}
