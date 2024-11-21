package com.example.arbrenarratif.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.utils.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryRepository {
    private Map<Integer, StoryNode> storyMap = new HashMap<>();

    public void loadStory(Context context) {
        String jsonString = JsonHelper.loadJSONFromAssets(context, "story.json");
        Log.d("StoryRepository", "JSON Loaded: " + jsonString); // Ajoute ce log

        Gson gson = new Gson();
        Type storyListType = new TypeToken<List<StoryNode>>() {}.getType();
        List<StoryNode> storyNodes = gson.fromJson(jsonString, storyListType);

        Log.d("StoryRepository", "Parsed Story Nodes: " + storyNodes); // Et celui-ci

        for (StoryNode node : storyNodes) {
            storyMap.put(node.getId(), node);
            Log.d("StoryRepository", "Node added: " + node.getId()); // Et celui-ci
        }
    }

    public StoryNode getNodeById(int id) {
        StoryNode node = storyMap.get(id);
        Log.d("StoryRepository", "Get Node by ID " + id + ": " + node); // Et celui-ci
        return node;
    }
}

