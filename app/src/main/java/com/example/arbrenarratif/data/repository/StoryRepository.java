package com.example.arbrenarratif.data.repository;

import android.content.Context;

import com.example.arbrenarratif.data.model.StoryNode;
import com.example.arbrenarratif.utils.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class StoryRepository {
    private final HashMap<Integer, StoryNode> storyMap = new HashMap<>();

    // Charger l'arbre narratif depuis le fichier JSON
    public void loadStory(Context context) {
        // Lire le fichier JSON brut
        String jsonString = JsonHelper.loadJSONFromAssets(context, "story.json");

        // Parser le JSON en une liste de StoryNode
        Gson gson = new Gson();
        Type storyListType = new TypeToken<List<StoryNode>>() {}.getType();
        List<StoryNode> storyNodes = gson.fromJson(jsonString, storyListType);

        // Stocker les nœuds dans un HashMap pour un accès rapide
        for (StoryNode node : storyNodes) {
            storyMap.put(node.getId(), node);
        }
    }

    // Récupérer un nœud par son ID
    public StoryNode getNodeById(int id) {
        return storyMap.get(id);
    }
}
