Полиморфизм — это когда переменная базового типа ссылается на объект подкласса, и вызывается метод, переопределённый в этом подклассе.
class Animal {
    public void speak() {
        System.out.println("Some animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Woof!");
    }
}

public class PolyExample {
    public static void main(String[] args) {
        Animal a = new Dog(); // полиморфное присваивание
        a.speak();            // полиморфный вызов — выполнится метод Dog.speak()
    }
}
speak() — полиморфный вызов, потому что он вызывается через переменную Animal, но фактически исполняется версия из Dog
------------
Метод переопределён в подклассе
Работает на уровне вызова метода
Тип возвращаемого значения один и тот же в базе и потомке
  
  
  
Ковариантность — это когда переопределённый метод в подклассе возвращает более конкретный тип, чем в базовом классе.
Допускается замена типа на его подтип, без нарушения корректности программы.
Это разрешено для возвращаемых типов методов, но запрещено для параметров методов при переопределении.
class Animal { }

class Dog extends Animal { }

class AnimalFactory {
    public Animal create() {
        return new Animal();
    }
}

class DogFactory extends AnimalFactory {
    @Override
    public Dog create() { // ковариантный возврат
        return new Dog();
    }
}

public class CovariantExample {
    public static void main(String[] args) {
        AnimalFactory factory = new DogFactory();
        Animal animal = factory.create(); // ковариантный вызов
        System.out.println("Created: " + animal.getClass().getSimpleName());
    }
}
Здесь create() возвращает Dog, но переменная factory — типа AnimalFactory, и вызов factory.create() — ковариантный, потому что тип возвращаемого значения уточнён в подклассе.
--------
Метод возвращает уточнённый тип
Работает на уровне возврата значения
Тип возвращаемого значения в потомке — уточнённый тип
