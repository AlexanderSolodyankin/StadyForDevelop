package com.home_project.programmer.s.cheat.sheet.mvc_controller;

import com.home_project.programmer.s.cheat.sheet.model.hint.Hints;
import com.home_project.programmer.s.cheat.sheet.model.hint.ShepherdGame;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/game")
public class HintController {

    @GetMapping("/game/shepherd_game")
    public String game1(Model model){
        ShepherdGame game1 = new ShepherdGame();
        model.addAttribute("game", game1);
        return "hint/game";
    }
    @PostMapping("/game/shepherd_game")
    public String gameRun(@ModelAttribute("game") ShepherdGame game,@RequestParam String test, Model model){

        System.out.println("Пришло "+game.getQuestName() + " " +test);


//        return "hint/game";
        return "main";
    }
}
