Возможно в данном случае примером может являться создание персонажа.
Например у него должна быть раса и класс.
В этом случае будет несколько критериев классификации некоторой родительской сущности и активное комбинирование этих критериев

Допустим главный класс:
public class Character {
    private final Race race;
    private final GameClass gameClass;

    public Character(Race race, GameClass gameClass) {
        this.race = race;
        this.gameClass = gameClass;
    }

    public void describe() {
        System.out.println("Раса: " + race.getName());
        System.out.println("Класс: " + gameClass.getName());
    }
}


Тогда саму расу можно сделать родителем и предоставить имеющиеся варианты
public abstract class Race {
    public abstract String getName();
}

public class Human extends Race {
    @Override
    public String getName() {
        return "Человек";
    }
}

public class Elf extends Race {
    @Override
    public String getName() {
        return "Эльф";
    }
}

public class Orc extends Race {
    @Override
    public String getName() {
        return "Орк";
    }
}

Тоже самое и с классами

ublic abstract class GameClass {
    public abstract String getName();
}

public class Warrior extends GameClass {
    @Override
    public String getName() {
        return "Воин";
    }
}

public class Mage extends GameClass {
    @Override
    public String getName() {
        return "Маг";
    }
}

public class Archer extends GameClass {
    @Override
    public String getName() {
        return "Лучник";
    }
}

Получается две иерархии классов
