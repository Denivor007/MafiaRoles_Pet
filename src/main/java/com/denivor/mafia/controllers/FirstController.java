package com.denivor.mafia.controllers;

import com.denivor.mafia.models.GamePattern;
import com.denivor.mafia.models.GamePatternsList;
import com.denivor.mafia.models.NameField;
import com.denivor.mafia.models.PlayersList;
import com.denivor.mafia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.denivor.mafia.services.RoleProcessor;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class FirstController {


    private GamePatternsList gamePatterns;
    private PlayersList playersList;
    private UserService userService;

    @Autowired
    public FirstController(GamePatternsList gamePatterns,
                           PlayersList playersList,
                           UserService userService){
        this.playersList = playersList;
        this.gamePatterns = gamePatterns;
        this.userService = userService;
    }

    private ArrayList<String> listofPlayersAndRoles = new ArrayList<>();

    @GetMapping()
    public String start(){
        return "redirect:/start";
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    private void addAllAtribute(Model model){
        model.addAttribute("playersWithRoleList", listofPlayersAndRoles);
        model.addAttribute("playersList", playersList);
        model.addAttribute("form", gamePatterns.getCurrentGamePattern());
        model.addAttribute("nameField", new NameField());
        model.addAttribute("gamePatterns", gamePatterns);
        model.addAttribute("currentUser", userService.loadUserByUsername(getCurrentUsername()));
    }

    @GetMapping("/start")
    public String startPage(Model model){
        addAllAtribute(model);
        return "start_page";

    }

    @PostMapping("/Refresh")
    public String refresh(){
        RoleProcessor roleProcessor = new RoleProcessor(
                gamePatterns.getCurrentGamePattern(),
                (ArrayList<String>) playersList.getPlayers()
        );
        listofPlayersAndRoles = (ArrayList<String>) roleProcessor.generateStringList();
        return "redirect:/start";
    }

    @PostMapping("/SetSettings")
    public String create(@ModelAttribute("form") GamePattern Quantity, Model model){
        gamePatterns.setCurrentGamePattern(Quantity);
        return "redirect:/start";
    }

    @PostMapping("/selectPattern")
    public String selectPattern(@ModelAttribute("gamePatterns") GamePatternsList gamePatterns1){
        gamePatterns = gamePatterns1;
        return "redirect:/start";
    }

    @PostMapping("/AddPerson")
    public String addPerson(@Valid @ModelAttribute("nameField") NameField nameField, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addAllAtribute(model);
            return "start_page";
        }

        playersList.add(nameField.getValue());
        return "redirect:/start";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
          playersList.delete(id);
          return "redirect:/start";
    }
}

