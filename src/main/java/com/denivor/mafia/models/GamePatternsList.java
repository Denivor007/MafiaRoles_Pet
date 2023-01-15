package com.denivor.mafia.models;

import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class GamePatternsList {
    private LinkedHashMap<String,GamePattern> gamePatterns;

    public void setGamePatterns(LinkedHashMap<String, GamePattern> gamePatterns) {
        this.gamePatterns = gamePatterns;
    }

    private String currentGamePatternName;
    private GamePattern currentGamePattern;

    public String getCurrentGamePatternName() {
        return currentGamePatternName;
    }

    public void setCurrentGamePatternName(String currentGamePatternName) {
        this.currentGamePatternName = currentGamePatternName;
        setCurrentGamePattern(gamePatterns.get(currentGamePatternName));
    }

    public GamePattern getCurrentGamePattern() {
        return currentGamePattern;
    }

    public void setCurrentGamePattern(GamePattern currentGamePattern) {
        this.currentGamePattern = currentGamePattern;
    }


    public GamePatternsList(){
        gamePatterns = new LinkedHashMap<>();
        HashMap<String, Integer> activeRolesQuantity1 = new HashMap<>();
        activeRolesQuantity1.put("Regular maifa", 2);
        activeRolesQuantity1.put("Mafia don", 1);
        activeRolesQuantity1.put("Sheriff", 1);
        activeRolesQuantity1.put("Doctor", 1);
        int countOfPlayers1 = 10;

        HashMap<String, Integer> activeRolesQuantity3 = new HashMap<>();
        activeRolesQuantity3.put("Regular maifa", 2);
        activeRolesQuantity3.put("Mafia don", 1);
        activeRolesQuantity3.put("Sheriff", 1);
        int countOfPlayers3 = 10;

        HashMap<String, Integer> activeRolesQuantity2 = new HashMap<>();
        activeRolesQuantity2.put("Regular maifa", 3);
        activeRolesQuantity2.put("Mafia don", 1);
        activeRolesQuantity2.put("Sheriff", 1);
        activeRolesQuantity2.put("Doctor", 1);
        activeRolesQuantity2.put("Maniac", 1);
        int countOfPlayers2 = 13;

        gamePatterns.put("Classic", new GamePattern(activeRolesQuantity1, countOfPlayers1));
        gamePatterns.put("Strict", new GamePattern(activeRolesQuantity3, countOfPlayers3));
        gamePatterns.put("Fun", new GamePattern(activeRolesQuantity2, countOfPlayers2));

        currentGamePatternName = "Classic";
        currentGamePattern = gamePatterns.get(currentGamePatternName);
    }



    public GamePattern getPatternByKey(String key){
        return gamePatterns.get(key);
    }

    public Set<String> getKeySet() {
        return gamePatterns.keySet();
    }
}


