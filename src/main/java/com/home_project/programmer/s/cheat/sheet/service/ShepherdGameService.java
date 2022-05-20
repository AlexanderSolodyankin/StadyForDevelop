package com.home_project.programmer.s.cheat.sheet.service;

import com.home_project.programmer.s.cheat.sheet.model.hint.ShepherdGame;

public interface ShepherdGameService
{
    ShepherdGame run();
    ShepherdGame run(ShepherdGame game, String value);
}
