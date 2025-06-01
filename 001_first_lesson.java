// Персонаж как абстрактный класс (родитель)
abstract class Person {
    protected String name;
    protected Weapon weapon; // Оружие в качестве композиции

    public Person(String name, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    // Метод в качестве полиморфизма — изменяет форму в наследниках
    public abstract void attack();
}

// Оружие как родитель
abstract class Weapon {
    protected String name;

    public Weapon(String name) {
        this.name = name;
    }

    public abstract void use();
}

// Наследник оружия
class Sword extends Weapon {
    public Sword() {
        super("Меч");
    }

    @Override
    public void use() {
        System.out.println("Рубит мечом!");
    }
}

// Наследник оружия
class Staff extends Weapon {
    public Staff() {
        super("Магический посох");
    }

    @Override
    public void use() {
        System.out.println("Испускает магическую волну!");
    }
}

// Наследник персонажа (как конкретный класс)
class Warrior extends GameCharacter {
    public Warrior(String name, Weapon weapon) {
        super(name, weapon);
    }

    @Override
    public void attack() {
        System.out.println(name + " атакует как воин!");
        weapon.use(); // Композиция + Полиморфизм (оружие может быть разное)
    }
}

// Наследник персонажа (как конкретный класс)
class Mage extends GameCharacter {
    public Mage(String name, Weapon weapon) {
        super(name, weapon);
    }

    @Override
    public void attack() {
        System.out.println(name + " колдует заклинание!");
        weapon.use(); // Композиция + Полиморфизм
    }
}

// Главный класс
public class Game {
    public static void main(String[] args) {
        Weapon sword = new Sword();
        Weapon staff = new Staff();

        GameCharacter warrior = new Warrior("Торальд", sword); // Наследование + композиция
        GameCharacter mage = new Mage("Элиара", staff);         // Наследование + композиция

        // Полиморфный вызов метода attack()
        warrior.attack();
        mage.attack();
    }
}
