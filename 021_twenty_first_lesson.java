7) Наследование реализации (implementation inheritance)
Класс-потомок наследует полностью реализованный класс-предок, добавляя свою уникальную абстракцию структуры данных. Наследование именно программной реализации. 

public class LinkedStack<T> extends LinkedList<T> {
    public void push(T value) {
        addLast(value);
    }

    public T pop() {
        return isEmpty() ? null : removeLast();
    }
}

  
8) Льготное наследование (facility inheritance)
Класс-предок содержит некоторый стандартный набор компонентов, объединённых в одно целое для удобства. Его потомки реализуют конкретные частные случаи.

public class MyAppException extends Exception {
    public MyAppException(String message) {
        super(message);
    }
}
