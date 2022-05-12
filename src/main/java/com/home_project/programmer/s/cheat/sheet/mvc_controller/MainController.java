package com.home_project.programmer.s.cheat.sheet.mvc_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class MainController {
    @GetMapping("/")
    public String menu (Model model){
        model.addAttribute("main", "Начало работы");
        return "main";
    }
    @GetMapping("/begin")
    public String begin(Model model){
        return "begin/begin_studies";
    }
}
