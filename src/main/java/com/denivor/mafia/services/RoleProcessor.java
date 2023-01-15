package com.denivor.mafia.services;

import com.denivor.mafia.models.GamePattern;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleProcessor {
    private GamePattern gamePattern;
    private ArrayList<String> players;
    public RoleProcessor(GamePattern gamePattern, ArrayList<String> players) {
        this.gamePattern = gamePattern;
        this.players = new ArrayList<>(players);
    }

    protected void shrinkArrayToFit(int fit, String pattern){
        int size = players.size();
        if (size == fit)
            return;
        if (size > fit){
            players = (ArrayList<String>) players.subList(0,fit-1);
            return;
        }
        for (int i = size; i < fit; i++) {
            players.add(pattern + " №"+ String.valueOf(i+1));
        }

    }
    protected void shrinkArrayToFit(int fit){
        shrinkArrayToFit(fit, "player");
    }


    public List<String> generateStringList() {
        //массив ролей в игре (поштучно)
        ArrayList<String> roles = new ArrayList<>();

        //добавляем в массив активные роли
        for (Map.Entry<String, Integer> pair : this.gamePattern.getActiveRolesQuantity().entrySet()) {
            for (int i = 0; i < pair.getValue(); i++) {
                roles.add(pair.getKey());
            }
        }

        //добавляем обычных мирных жителей, если они есть
        int count_of_active_roles = roles.size();
        for (int i = count_of_active_roles; i < this.gamePattern.getCountOfPlayers(); i++) {
                roles.add("Regular citizen");
        }


        Collections.shuffle(roles); //перемешиваем
        shrinkArrayToFit(roles.size()); //добавляем шаблонные имена, если нужо

        // Формируем итоговый список
        for (int i = 0; i < roles.size(); i++) {
            roles.set(i, String.valueOf(i+1) + ") "+this.players.get(i) + " - " + roles.get(i));
        }
        return roles;
    }
}
