
// Абстрактный класс — родитель
abstract class Animal {
    public abstract void speak(); // Абстрактный метод
}

// Конкретный подкласс Dog
class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Dog say: Gav!");
    }
}

// Основной класс
public class Main {
    public static void main(String[] args) {
        Animal animal; // Переменная ссылочного типа Animal (типа родительский)

        animal = new Dog(); // Присваиваем объект Dog переменной ссылочного типа Animal
        animal.speak(); //  Вызов метода идёт по ссылке Animal, но выполняется метод Dog.speak() — динамическое связывание
    }
}
