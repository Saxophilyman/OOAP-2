// Главный класс
public class Zoo {
    public static void main(String[] args) {
        Animal baseAnimal = new Animal();                   // базовый
        Animal customAnimal = new CommonAnimal("Гррр");     // расширение
        Animal cat = new Cat();                             // специализация

        baseAnimal.makeSound();
        customAnimal.makeSound();
        cat.makeSound();
    }
}

//Родительский класс
class Animal {
    public void makeSound() {
        System.out.println("Животное издаёт какой-то звук.");
    }
}

// Расширение класса-родителя (наследник задаёт более общий случай родителя)
class CommonAnimal extends Animal {
    private String sound;

    public CommonAnimal(String sound) {
        this.sound = sound;
    }

    // можно задать звук произвольно
    @Override
    public void makeSound() {
        System.out.println("Животное издаёт звук: " + sound);
    }
}

// Специализация класса-родителя (наследник задаёт более специализированный случай родителя)
class Cat extends Animal {
    // Задаём конкретный звук: кошка всегда только мяукает
    @Override
    public void makeSound() {
        System.out.println("Кошка мяукает: Мяу!");
    }
}
