package com.denivor.mafia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GamePatternList {
    @JsonProperty("gamePatterns")
    private HashMap<String,GamePattern> gamePatterns;

    public void setGamePatterns(HashMap<String, GamePattern> gamePatterns) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePatternList that = (GamePatternList) o;
        return Objects.equals(gamePatterns, that.gamePatterns) && Objects.equals(currentGamePatternName, that.currentGamePatternName) && Objects.equals(currentGamePattern, that.currentGamePattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamePatterns, currentGamePatternName, currentGamePattern);
    }

    public GamePattern getCurrentGamePattern() {
        return currentGamePattern;
    }

    public void setCurrentGamePattern(GamePattern currentGamePattern) {
        this.currentGamePattern = currentGamePattern;
    }


    public GamePatternList(){
        gamePatterns = new HashMap<>();
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

    @JsonIgnore
     public Set<String> getKeySet() {
        return gamePatterns.keySet();
    }
}


