package com.home_project.programmer.s.cheat.sheet.model.hint;

import lombok.Data;

@Data
public class ShepherdGame extends Hints {
    private int step;
    private String[] brich1 = new String[4];
    private String[] brich2 = new String[4];
    private String[] boat = new String[2];
    private boolean status = false;
    private boolean failure = false;
    private String action;



    public ShepherdGame() {
        setQuestName("Пастух");
        setDescription("Стоит пастух, коза, капуста и волк. Перед ними река и одна лодка в которой только два места." +
                "\n Пастуху нужно переправить волка козу и капусту на другой берег. Но если оставить козу с волком то волк съест козу," +
                " или если оставить козу и капусту то коза съест капусту как пастуху переправить всех через реку? (Подсказка: Лодка без пастуха не плывет!!!");

        this.step = 0;

        this.brich1[0] = "Пастух";
        this.brich1[1] = "Коза";
        this.brich1[2] = "Волк";
        this.brich1[3] = "Капуста";

        this.boat[0] = "Пусто";
        this.boat[1] = "Пусто";

        this.brich2[0] = "Пусто";
        this.brich2[1] = "Пусто";
        this.brich2[2] = "Пусто";
        this.brich2[3] = "Пусто";

        this.action = "Кого вы посадите в лодку?";
    }


}
