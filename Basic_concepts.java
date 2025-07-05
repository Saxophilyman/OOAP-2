Полиморфизм
Объект переменной может быть экземпляром любого подкласса ожидаемого типа.
Animal a = new Cat(); // Cat — подтип Animal
Мы используем a полиморфно — не зная точный тип, но зная поведение через базовый интерфейс.

Ковариантность
Тип возвращаемого значения или параметра может меняться по иерархии — обычно "вниз". 
В Java ковариантность разрешена для возвращаемого типа при переопределении метода:
class Animal { }
class Cat extends Animal { }

class AnimalFactory {
    Animal create() { return new Animal(); }
}

class CatFactory extends AnimalFactory {
    @Override
    Cat create() { return new Cat(); } // ✅ Ковариантное возвращаемое значение
}

С параметрами так нельзя:
class WrongFactory extends AnimalFactory {
    Cat create(Cat c) { return c; } // ❌ Это перегрузка, не переопределение
}
