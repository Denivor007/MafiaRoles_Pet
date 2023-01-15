package com.denivor.mafia.models;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
Этот класс будет список активных ролей и количества людей
На его основе будет формироваться в бизнес логике список людей и отдаваться на сайт.
Если на сайт заходит новый человек, то ему отдаётся дефолтный игровой паттерн
* */
@Component
public class GamePattern {

    private Map<String, Integer> activeRolesQuantity;
    private Integer countOfPlayers ;
    public Map<String, Integer> getActiveRolesQuantity() {
        return activeRolesQuantity;
    }

    public void setActiveRolesQuantity(Map<String, Integer> activeRolesQuantity) {
        this.activeRolesQuantity = activeRolesQuantity;
    }

    public Integer getCountOfPlayers() {
        return countOfPlayers;
    }

    public void setCountOfPlayers(Integer countOfPlayers) {
        this.countOfPlayers = countOfPlayers;
    }



    // УБРАТЬ ЭТО УБОЖЕСТВО, ДЕФОЛТНЫЕ СЛОВАРИ НЕ ДОЛЖНЫ СУЩЕСТВОВАТЬ
    public GamePattern(){
        activeRolesQuantity = new HashMap<>();
        activeRolesQuantity.put("Regular mafia", 2);
        activeRolesQuantity.put("Mafia don", 1);
        activeRolesQuantity.put("Sheriff", 1);
        activeRolesQuantity.put("Doctor", 1);
        countOfPlayers = 10;
    }

    public GamePattern(Map<String, Integer> activeRolesQuantity, Integer countOfPlayers) {
        this.activeRolesQuantity = activeRolesQuantity;
        this.countOfPlayers = countOfPlayers;
    }
}

