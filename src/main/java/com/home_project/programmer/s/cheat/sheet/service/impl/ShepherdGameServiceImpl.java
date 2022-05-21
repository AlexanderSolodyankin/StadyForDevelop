package com.home_project.programmer.s.cheat.sheet.service.impl;

import com.home_project.programmer.s.cheat.sheet.model.hint.ShepherdGame;
import com.home_project.programmer.s.cheat.sheet.service.ShepherdGameService;

import java.util.ArrayList;
import java.util.List;

public class ShepherdGameServiceImpl implements ShepherdGameService {
    @Override
    public ShepherdGame run() { // Создание и заполнение класса игры для того чтобы ее начать (Тупо заполнение параметров)
        // возврощает клас игры в контроллер
        ShepherdGame game = new ShepherdGame(); // создаем игру
        game.setQuestName("Пастух"); // кидаем имя игры и ниже описание
        game.setDescription("Стоит пастух, коза, капуста и волк. Перед ними река и одна лодка в которой только два места." +
                "\n Пастуху нужно переправить волка козу и капусту на другой берег. Но если оставить козу с волком то волк съест козу," +
                " или если оставить козу и капусту то коза съест капусту как пастуху переправить всех через реку? (Подсказка: Лодка без пастуха не плывет!!!)");

        game.setStep(0); // приравниваем ходы к нулю

        String[] shore1 = new String[4]; // заполняем правый берег через обычный массив
        shore1[0] = "Пастух";
        shore1[1] = "Коза";
        shore1[2] = "Волк";
        shore1[3] = "Капуста";
        game.setShore1(shore1); //вкладываем в игру данный массив (лень было писать интерфейс так проще)

        String[] boat = new String[2]; // заполняем лодку чтобы пользователь видел что что то происходит
        boat[0] = "Пусто";
        boat[1] = "Пусто";
        game.setBoat(boat); // Кидаем в игру поля

        String[] shore2 = new String[4]; // создаем параметр правого берега
        shore2[0] = "Пусто";
        shore2[1] = "Пусто";
        shore2[2] = "Пусто";
        shore2[3] = "Пусто";
        game.setShore2(shore2); // кидаем массив в качестве параметра в игру

        game.setAction("Кого вы посадите в лодку?"); // описания дейсвий

        String[] shoreActive = new String[5]; // создаем маасив действий и на выдоче преаброзуем в кнопки на фронте

        for (int i = 0; i < shore1.length; i++) {
            shoreActive[i] = shore1[i];
        }
        shoreActive[4] = "Отправить лодку"; // по данной яччейке массива проиходит действие  (отправить лодку)
        game.setShoreActive(shoreActive); // вносим масив в качестве параметра в игру

        game.setShorePoint(0); // парамет положения лодки 0= левый берег, 1 = правый берег.

        game.setStatus(true);
        game.setFailure(true);

        return game; // возврощаем игру
    }

    @Override
    public ShepherdGame run(ShepherdGame game, String value) { // логика действий игры
            if (value.equals("Отправить лодку")) { // проверка на получение параметра отправить лодку
                // нужна для того чтобы проверить на проигрышь игры
                int checkEat = 0; // счетчик проверки на волк сьел козу если раветн 2
                int checkEat2 = 0; // счетчик на капусту которую сьела коза если параметр равен 2
                String[] arrayFail = new String[1]; // масив для переопределения кнопки на случай проигрыша
                arrayFail[0] = "Начать с начала";
                for (int i = 0; i < game.getShore1().length; i++) { // проверка левого берега
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
                    game.setShoreActive(arrayFail); // переопределяем массив действи и отдаем только одну кнопку
                    return game;
                }
                if (checkEat2 >= 2) {
                    game.setAction("Коза съела капусту. Игра Закончена");
                    game.setShoreActive(arrayFail);// переопределяем массив действи и отдаем только одну кнопку
                    return game;
                }
                checkEat = 0; // обнуляем счетчики
                checkEat2 = 0;

                for (int i = 0; i < game.getShore2().length; i++) { // проверяем правц берег ( все тоже самое)
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

                    // проверка на положение пастуха в лодке ( Ведь лодка сама по себе не поплывет
                // если пастуха нет возврощаем значение на место
                if (game.getBoat()[0].equals("Пастух") || game.getBoat()[1].equals("Пастух")) {
                    for (int i = 0; i < game.getBoat().length; i++) {
                        //Пренос значений из лодки полсе того как лодка переплыла реку
                        if (!game.getBoat()[i].equals("Пусто")) {
                            if (game.getShorePoint() == 0) { // проверка положения лодки

                                for (int j = 0; j < game.getShore2().length; j++) {
                                    if (game.getShore2()[j].equals("Пусто")) {
                                        // если на берегу есть место куда перенести эемент
                                        game.getShore2()[j] = game.getBoat()[i];
                                        game.getBoat()[i] = "Пусто";
                                        break;
                                    }
                                }
                                // переопределение описания действий
                                game.setAction("Лодка на правом берегу. И что теперь делать?");
                            } else {
                                // перенос элементов на левый берег после того как она переплыла
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
                    // масив для переопределения значений кнопок
                    List<String> arrayShoreAction = new ArrayList<>();
                    if (game.getShorePoint() == 0) {
                        // если лодка переплыла с левого берега то задаем положения на правй берег
                        game.setShorePoint(1);
                        for (int i = 0; i < game.getShore2().length; i++) {
                            // цикл для переноса значений из правого берега в массив кнопок
                            if (!game.getShore2()[i].equals("Пусто")) {
                                arrayShoreAction.add(game.getShore2()[i]);
                            }
                        }
                    } else {
                        // если лодка переплыла с правого берега то задаем положения на правй берег
                        game.setShorePoint(0);
                        for (int i = 0; i < game.getShore1().length; i++) {
                            // цикл для переноса значений из левого берега в массив кнопок
                            if (!game.getShore1()[i].equals("Пусто")) {
                                arrayShoreAction.add(game.getShore1()[i]);
                            }
                        }
                    }
                    // обязательно постоянно добовляем параметр действия так как пересоздаем массив
                    arrayShoreAction.add("Отправить лодку");
                    // конвертирую лист в массив так как если сделать полностью везде лсты то параметры могут множится
                    // а берег растягиватся двойными значениями
                    game.setShoreActive(arrayShoreAction.toArray(new String[arrayShoreAction.size()]));
                } else {
                    // если пастуха в лодке нет то возвращачем все значение из лодки на берег на котором она находится
                    game.setAction("Без пастуха лодка не поплывет");
                    String[] arrayShore; // массив для переопределения (Бля сколько я их повторно создал надо потом это исправить
                    String[] arrayBoat = game.getBoat(); // масив для переопределения
                    if (game.getShorePoint() == 0) { // если лодка на левом берегу
                        // возврощаем все значения из лодки на левый берег
                        arrayShore = game.getShore1();
                        for (int i = 0; i < arrayBoat.length; i++) {
                            for (int j = 0; j < arrayShore.length; j++) {
                                if (arrayShore[j].equals("Пусто")) {
                                    arrayShore[j] = arrayBoat[i];
                                    arrayBoat[i] = "Пусто";
                                    break;
                                    // поскольку вложенный цикл всегда его стопаем чтобы не множились начения
                                }
                            }
                        }
                        // переопределяем значения массива в классе
                        game.setShore1(arrayShore);
                    } else {
                        // возврощаем все значение на прав берег
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

                // проверка на то чтобы в лодку не пускало больше двух элементов а то лодка не резиновая
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
                    // если лодка на левом берегу
                    array = game.getShore1();

                    for (int i = 0; i < array.length; i++) {
                        // переносим из лодки элементы
                        if (array[i].equals(value)) {
                            array[i] = "Пусто";
                        }
                    }
                    game.setShore1(array); // переобределяем начения левого берега
                    for (int i = 0; i < game.getBoat().length; i++) {
                        // делаем лодку пустой
                        if (game.getBoat()[i].equals("Пусто")) {
                            game.getBoat()[i] = value;
                            break;
                        }
                    }
                } else {
                    // правц берег
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
