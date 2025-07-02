В Java можно реализовать эту идею посредством интерфейсов

Чтобы дать возможность над типом T, совершать какие-то дополнительные операции, непредусмотренные в базовом классе,
которые также семантически присущи типу MyType можно использовать ограниченную универсальность. 
Можно создать интерфейс, который будет базово задавать возможные операции.
  Например
public interface Summable<T> {
    T sum(T other);
}
Любой класс, элементы которого мы хотим складывать, должен реализовать Summable
Ещё раз - это обобщённый интерфейс, и его можно реализовать для любых типов:
MyInteger implements Summable<MyInteger>
Vector<T> implements Summable<Vector<T>>
  и т.д.
Очень просто: 
Я — объект типа T.
Я умею складываться с другим T.
И результат — тоже T.
  
Реализация класса Vector<T> выглядеть сможет уже вот таким образом
Сразу попытаюсь объяснить за верхнюю строчку
класс Vector принимает параметр T, который(T) должен быть наследником (extends) General (класса) и одновременно реализовать интерфейс Summable<T>
Vector наследуется от универсального класса Any, а Vector<T> реализует интерфейс, т.е. можно складывать Vector<T> + Vector<T>.
public class Vector<T extends Summable<T> & General> extends Any implements Summable<Vector<T>> {
  private final List<T> elements;
  // Конструктор.
  public Vector(List<T> elements) {
        this.elements = elements;
    }
  // Геттер
  public List<T> getElements() {
        return elements;
    }

  @Override
  public Vector<T> sum(Vector<T> other) {
    if (this.elements.size() != other.elements.size()) {
            return null; // проверяем, можно ли сложить векторы — только если они одинаковой длины.
    }

    List<T> result = new ArrayList<>(); //Создаём новый список, в который кладём поэлементную сумму двух векторов.
        for (int i = 0; i < elements.size(); i++) {
            result.add(elements.get(i).sum(other.elements.get(i)));
        } // Здесь срабатывает полиморфизм: каждый элемент T знает, как себя сложить с другим T, потому что реализует Summable<T>.
        return new Vector<>(result);
    }

  @Override
  public String toString() {
        return elements.toString();
    }
}


Пример применения
(Обязательный компонент, если нужно использовать Vector<T>, где T — целочисленный тип, поддерживающий операцию сложения)
public class IntBox extends Any implements Summable<IntBox> {
    private final int value;

    public IntBox(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public IntBox sum(IntBox other) {
        return new IntBox(this.value + other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

Проверка вложенных векторов
public class Test {
    public static void main(String[] args) {
        var v1 = new Vector<>(List.of(new IntBox(1), new IntBox(2)));
        var v2 = new Vector<>(List.of(new IntBox(3), new IntBox(4)));
        var sum1 = v1.sum(v2);
        System.out.println(sum1); // [4, 6]

        var vv1 = new Vector<>(List.of(v1, v2));
        var vv2 = new Vector<>(List.of(v2, v1));
        var sum2 = vv1.sum(vv2);
        System.out.println(sum2); // [[4,6],[4,6]]

        var vvv1 = new Vector<>(List.of(vv1));
        var vvv2 = new Vector<>(List.of(vv2));
        var sum3 = vvv1.sum(vvv2);
        System.out.println(sum3); // [[[4,6]]]
    }
}
