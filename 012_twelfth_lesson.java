public class General implements Serializable {

    /**
     * Попытка присваивания — безопасное приведение типа.
     * Если объект совместим с требуемым типом, возвращается объект.
     * Иначе возвращается null (как "пустое значение").
     */
    public <T> T tryAssign(Class<T> clazz) {
        if (clazz.isInstance(this)) {
            return clazz.cast(this);  // безопасное приведение
        }
        return null;  
    }
    // Другие методы 
}

class Dog extends Any {
    public void bark() {
        System.out.println("Гав!");
    }
}

class Cat extends Any {
    public void meow() {
        System.out.println("Мяу!");
    }
}

public class Test {
    public static void main(String[] args) {
        Any animal = new Cat(); // неизвестный на первый взгляд тип

        Dog maybeDog = animal.tryAssign(Dog.class);
        Cat maybeCat = animal.tryAssign(Cat.class);

        if (maybeDog != null) {
            maybeDog.bark();  // Не выполнится
        }

        if (maybeCat != null) {
            maybeCat.meow();  // Выполнится
        }

        Any something = new Any();
        Cat failed = something.tryAssign(Cat.class);
        System.out.println(failed == null ? "Не кот" : "Это кот");
    }
}
