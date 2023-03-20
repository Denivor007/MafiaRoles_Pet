package com.denivor.mafia.controllers;

import com.denivor.mafia.entity.User;
import com.denivor.mafia.models.*;
import com.denivor.mafia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
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
public class FirstController
            implements ApplicationListener<InteractiveAuthenticationSuccessEvent>
{


    private GamePatternList gamePatterns;
    private PlayersList playersList;
    private UserService userService;

    private ArrayList<String> listofPlayersAndRoles = new ArrayList<>();

    @Autowired
    public FirstController(GamePatternList gamePatterns,
                           PlayersList playersList,
                           UserService userService){
        this.playersList = playersList;
        this.gamePatterns = gamePatterns;
        this.userService = userService;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) userService.loadUserByUsername(auth.getName());
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        User current = getCurrentUser();
        playersList = new PlayersList(current.getGamerList());
        gamePatterns = current.getGamePatterns();
    }

    private void addAllAttributes(Model model){
        model.addAttribute("playersWithRoleList", listofPlayersAndRoles);
        model.addAttribute("playersList", playersList);
        model.addAttribute("form", gamePatterns.getCurrentGamePattern());
        model.addAttribute("nameField", new NameField());
        model.addAttribute("roleFields", new GameRoleFields());
        model.addAttribute("gamePatterns", gamePatterns);
        model.addAttribute("currentUser", getCurrentUser());
    }

    @GetMapping()
    public String start(){
        return "redirect:/start";
    }

    @GetMapping("/start")
    public String startPage(Model model){
        addAllAttributes(model);
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
        this.saveGamePattern();
        return "redirect:/start";
    }

    @PostMapping("/selectPattern")
    public String selectPattern(@ModelAttribute("gamePatterns") GamePatternList gamePatterns1){

        gamePatterns = gamePatterns1;
        return "redirect:/start";
    }

    @GetMapping("/createNewPattern")
    public String createNewPattern(Model model){
        model.addAttribute("newGamePattern", new NewGamePatternField());

        return "create_pattern";
    }

    @PostMapping("/createNewPattern")
    public String createNewPattern(@ModelAttribute("newGamePattern") NewGamePatternField value){
        gamePatterns.addGamePattern(value.getValue(), new GamePattern());
        saveGamePattern();
        return "redirect:/start";

    }


    @GetMapping("/pattern/delete/{value}")
    public String deleteRole(@PathVariable String value){
        gamePatterns.getCurrentGamePattern().getActiveRolesQuantity().remove(value);
        this.saveGamePattern();
        return "redirect:/start";
    }

    @PostMapping("/pattern/addRole")
    public String addRole(@Valid @ModelAttribute("roleFields") GameRoleFields value, BindingResult result, Model model){
        if (result.hasErrors()) {
            addAllAttributes(model);
            return "start_page";
        }

        gamePatterns.getCurrentGamePattern().getActiveRolesQuantity().put(value.getKey(), value.getValue());
        this.saveGamePattern();
        return "redirect:/start";
    }

    @PostMapping("/AddPerson")
    public String addPerson(@Valid @ModelAttribute("nameField") NameField nameField, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addAllAttributes(model);
            return "start_page";
        }

        playersList.add(nameField.getValue());
        this.savePlayersList();
        return "redirect:/start";
    }


    public void saveGamePattern(){
        User user = getCurrentUser();
        user.setGamePatterns(gamePatterns);

        userService.updateUser(user);
    }

    public void savePlayersList(){
        User user = getCurrentUser();
        user.setGamerList(this.playersList.getPlayers());
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
          playersList.delete(id);
          return "redirect:/start";
    }

}

/*
https://ru.stackoverflow.com/questions/1187761

судячи з всього то доведеться мені все-ж постійно питати в контексту користувача
 */