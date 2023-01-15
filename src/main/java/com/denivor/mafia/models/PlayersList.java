package com.denivor.mafia.models;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PlayersList {
    private List<String> players;
    public void delete(int id){
        players.remove(id);
    }
    public boolean isInPlayers(String player){
        return players.contains(player);
    }
    public void add(String player){
        players.add(player);
    }
    public List<String> getPlayers(){
        return new ArrayList<>(players);
    }

}
