package com.home_project.programmer.s.cheat.sheet.mvc_controller;

import com.home_project.programmer.s.cheat.sheet.model.hint.ShepherdGame;
import com.home_project.programmer.s.cheat.sheet.service.ShepherdGameService;
import com.home_project.programmer.s.cheat.sheet.service.impl.ShepherdGameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/game")
public class HintController {
    ShepherdGameService run = new ShepherdGameServiceImpl();

    @GetMapping("/game/shepherd_game")
    public String game1(Model model){
        ShepherdGame game1 = run.run();
        model.addAttribute("game", game1);
        return "hint/game";
    }
    @PostMapping("/game/shepherd_game")
    public String gameRun(@ModelAttribute("game") ShepherdGame game,@RequestParam String test, Model model){

        if(game.getShoreActive()[0].equals("Начать с начала")){
            return "redirect:/game/shepherd_game";
        }
        game = run.run(game,test);
        int count = 0;
        for (int i = 0; i < game.getShore2().length; i++) {
            if (game.getShore2()[i].equals("Коза")) count++;
            if (game.getShore2()[i].equals("Волк")) count++;
            if (game.getShore2()[i].equals("Пастух")) count++;
            if (game.getShore2()[i].equals("Капуста")) count++;

        }
        if(count==4) return "hint/hint_page";
        model.addAttribute("game", game);



        return "hint/game";
    }
}
