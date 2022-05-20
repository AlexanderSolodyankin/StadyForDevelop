package com.home_project.programmer.s.cheat.sheet.service.impl;

import com.home_project.programmer.s.cheat.sheet.model.hint.ShepherdGame;
import com.home_project.programmer.s.cheat.sheet.service.ShepherdGameService;

import java.util.ArrayList;
import java.util.List;

public class ShepherdGameServiceImpl implements ShepherdGameService {
    @Override
    public ShepherdGame run() {
        ShepherdGame game = new ShepherdGame();
        game.setQuestName("Пастух");
        game.setDescription("Стоит пастух, коза, капуста и волк. Перед ними река и одна лодка в которой только два места." +
                "\n Пастуху нужно переправить волка козу и капусту на другой берег. Но если оставить козу с волком то волк съест козу," +
                " или если оставить козу и капусту то коза съест капусту как пастуху переправить всех через реку? (Подсказка: Лодка без пастуха не плывет!!!)");

        game.setStep(0);

        String[] shore1 = new String[4];
        shore1[0] = "Пастух";
        shore1[1] = "Коза";
        shore1[2] = "Волк";
        shore1[3] = "Капуста";
        game.setShore1(shore1);

        String[] boat = new String[2];
        boat[0] = "Пусто";
        boat[1] = "Пусто";
        game.setBoat(boat);

        String[] shore2 = new String[4];
        shore2[0] = "Пусто";
        shore2[1] = "Пусто";
        shore2[2] = "Пусто";
        shore2[3] = "Пусто";
        game.setShore2(shore2);

        game.setAction("Кого вы посадите в лодку?");

        String[] shoreActive = new String[5];

        for (int i = 0; i < shore1.length; i++) {
            shoreActive[i] = shore1[i];
        }
        shoreActive[4] = "Отправить лодку";
        game.setShoreActive(shoreActive);

        game.setShorePoint(0);

        game.setStatus(true);
        game.setFailure(true);

        return game;
    }

    @Override
    public ShepherdGame run(ShepherdGame game, String value) {
            if (value.equals("Отправить лодку")) {
                int checkEat = 0;
                int checkEat2 = 0;
                String[] arrayFail = new String[1];
                arrayFail[0] = "Начать с начала";
                for (int i = 0; i < game.getShore1().length; i++) {
                    if (game.getShore1()[i].equals("Коза")) {
                        checkEat++;
                        checkEat2++;

                    }
                    if (game.getShore1()[i].equals("Капуста")) {
                        checkEat2++;

                    }
                    if (game.getShore1()[i].equals("Волк")) {
                        checkEat++;
                    }
                }
                if (checkEat >= 2) {
                    game.setAction("Волк съел Козу. Игра закончена");
                    game.setShoreActive(arrayFail);
                    return game;
                }
                if (checkEat2 >= 2) {
                    game.setAction("Коза съела капусту. Игра Закончена");
                    game.setShoreActive(arrayFail);
                    return game;
                }
                checkEat = 0;
                checkEat2 = 0;

                for (int i = 0; i < game.getShore2().length; i++) {
                    if (game.getShore2()[i].equals("Коза")) {
                        checkEat++;
                        checkEat2++;

                    }
                    if (game.getShore2()[i].equals("Капуста")) {
                        checkEat2++;

                    }
                    if (game.getShore2()[i].equals("Волк")) {
                        checkEat++;
                    }
                }
                if (checkEat >= 2) {
                    game.setAction("Волк съел Козу. Игра закончена");
                    game.setShoreActive(arrayFail);
                    return game;
                }
                if (checkEat2 >= 2) {
                    game.setAction("Коза съела капусту. Игра Закончена");
                    game.setShoreActive(arrayFail);

                    return game;
                }


                if (game.getBoat()[0].equals("Пастух") || game.getBoat()[1].equals("Пастух")) {
                    for (int i = 0; i < game.getBoat().length; i++) {
                        if (!game.getBoat()[i].equals("Пусто")) {
                            if (game.getShorePoint() == 0) {
                                for (int j = 0; j < game.getShore2().length; j++) {
                                    if (game.getShore2()[j].equals("Пусто")) {
                                        game.getShore2()[j] = game.getBoat()[i];
                                        game.getBoat()[i] = "Пусто";
                                        break;
                                    }
                                }

                                game.setAction("Лодка на правом берегу. И что теперь делать?");
                            } else {
                                for (int j = 0; j < game.getShore1().length; j++) {
                                    if (game.getShore1()[j].equals("Пусто")) {
                                        game.getShore1()[j] = game.getBoat()[i];
                                        game.getBoat()[i] = "Пусто";
                                        break;
                                    }
                                }
                                game.setAction("Лодка на левом берегу. И что теперь делать?");

                            }
                        }
                    }
                    List<String> arrayShoreAction = new ArrayList<>();
                    if (game.getShorePoint() == 0) {
                        game.setShorePoint(1);
                        for (int i = 0; i < game.getShore2().length; i++) {
                            if (!game.getShore2()[i].equals("Пусто")) {
                                arrayShoreAction.add(game.getShore2()[i]);
                            }
                        }
                    } else {
                        game.setShorePoint(0);
                        for (int i = 0; i < game.getShore1().length; i++) {
                            if (!game.getShore1()[i].equals("Пусто")) {
                                arrayShoreAction.add(game.getShore1()[i]);
                            }
                        }
                    }
                    arrayShoreAction.add("Отправить лодку");
                    game.setShoreActive(arrayShoreAction.toArray(new String[arrayShoreAction.size()]));
                } else {
                    game.setAction("Без пастуха лодка не поплывет");
                    String[] arrayShore;
                    String[] arrayBoat = game.getBoat();
                    if (game.getShorePoint() == 0) {
                        arrayShore = game.getShore1();
                        for (int i = 0; i < arrayBoat.length; i++) {
                            for (int j = 0; j < arrayShore.length; j++) {
                                if (arrayShore[j].equals("Пусто")) {
                                    arrayShore[j] = arrayBoat[i];
                                    arrayBoat[i] = "Пусто";
                                    break;
                                }
                            }
                        }
                        game.setShore1(arrayShore);
                    } else {
                        arrayShore = game.getShore2();
                        for (int i = 0; i < arrayBoat.length; i++) {
                            for (int j = 0; j < arrayShore.length; j++) {
                                if (arrayShore[j].equals("Пусто")) {
                                    arrayShore[j] = arrayBoat[i];
                                    arrayBoat[i] = "Пусто";
                                    break;
                                }
                            }
                        }
                        game.setShore2(arrayShore);
                    }
                }
            } else {

                int countBag = 0;
                for (int i = 0; i < game.getBoat().length; i++) {
                    if (game.getBoat()[i].equals("Пусто")) {
                        countBag++;
                    }
                }
                if (countBag == 0) {
                    game.setAction("В лодке только два места. Отправьте лодку на другой берег");
                    return game;
                }
                String[] array;
                if (game.getShorePoint() == 0) {
                    array = game.getShore1();

                    for (int i = 0; i < array.length; i++) {
                        if (array[i].equals(value)) {
                            array[i] = "Пусто";
                        }
                    }
                    game.setShore1(array);
                    for (int i = 0; i < game.getBoat().length; i++) {
                        if (game.getBoat()[i].equals("Пусто")) {
                            game.getBoat()[i] = value;
                            break;
                        }
                    }
                } else {
                    array = game.getShore2();
                    for (int i = 0; i < array.length; i++) {
                        if (array[i].equals(value)) {
                            array[i] = "Пусто";
                        }
                    }
                    game.setShore2(array);
                    for (int i = 0; i < game.getBoat().length; i++) {
                        if (game.getBoat()[i].equals("Пусто")) {
                            game.getBoat()[i] = value;
                            break;
                        }
                    }
                }
            }
            return game;
        }



}
