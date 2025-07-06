4) Наследования вариаций
  
Наследование с функциональной вариацией (functional variation inheritance) 
Переопределение родительского метода без изменения его сигнатуры (переопределяется только логика работы)

class Animal {
    public void sound() {
        System.out.println("Some generic sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Bark");
    }
}


Наследование с вариацией типа (type variation inheritance)
Переопределение сигнатуры родительского метода (так называемая перегрузка метода).

class Printer {
    public void print(String text) {
        System.out.println("Text: " + text);
    }
}

class SmartPrinter extends Printer {
    public void print(int number) {
        System.out.println("Number: " + number);
    }
}

5) Наследование с конкретизацией (reification inheritance)
Реализация общего родительского класса отложена (он абстрактный, например, или реализован частично), а конкретные законченные реализации выполнены в его классах-наследниках.

abstract class Shape {
    public abstract double area();
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

6) Структурное наследование (structure inheritance)
Добавление типу данных некоторого нового, автономного, формального свойства. 

class Book implements Iterable<String> {
    private final List<String> pages;

    public Book(List<String> pages) {
        this.pages = pages;
    }

    @Override
    public Iterator<String> iterator() {
        return pages.iterator();  
    }
}
