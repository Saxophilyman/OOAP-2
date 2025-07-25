Попробую поэкспериментировать, используя помощника, чтобы ещё раз выявить для себя суть, но не скатиться в копипасту.
На Java имеется возможность использовать ковариантность и контравариантность черз дженерики (обобщённые типы), но только через ограничения wildcard ? extends/super.
Как самый яркий и доступный пример эти варианты иллюстрируются на структурах данных таких как списки. Но в них имеется определённые ограничения.
Чтобы отследить ошибки на этапе компиляции и обеспечить типовую безопасность (соответствие типов) в случае ковариантности через ? extends разрешено чтение из структуры, однако нельзя сделать запись.
В случае с контрвариантностью всё наоборот ? super T —  можно добавлять объект и всех его подклассов, но при извлечении — тип будет не определён и доступ возможен только как Object
  
Примеры кода:
КОВАРИАНТНОСТЬ
class Animal {
    void speak() {
        System.out.println("Животное говорит");
    }
}

class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("Кот мяукает");
    }
}

public class CovarianceExample {
    public static void main(String[] args) {
        List<Cat> cats = List.of(new Cat(), new Cat());
        
        // Ковариантность — можно передать список Cat в метод, принимающий List<? extends Animal>
        printAnimals(cats);
    }

    // Метод, принимающий List<?> где ? — подтип Animal
    public static void printAnimals(List<? extends Animal> animals) {
        for (Animal a : animals) {
            a.speak();  // Можно читать
        }

        // animals.add(new Cat());  //  Ошибка компиляции! — нельзя добавлять, неизвестно, какой именно подтип
        // Компилятор не может гарантировать, что добавляемый элемент совместим с точным типом, которым параметризован список
    }
}

КОНТРВАРИАНТНОСТЬ
public class ContravarianceExample {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        
        // Контравариантность — список, который может содержать Cat и его супертипы
        List<? super Cat> catList = animals;
        
        catList.add(new Cat());  // Можно добавлять Cat
        // catList.add(new Animal());  // Нельзя — не гарантировано, что super именно Animal

        // Но при чтении доступен только тип Object
        Object obj = catList.get(0);  // Можно читать только как Object
    }
}

К каком-то смысле wildcard (?) — это неизвестный подтип или супертип, а не конкретный. Это значит, что мы не можем с ним работать "на равных", как с точным типом T.
И здесь стоит вспомнить про Инвариантность в Java
По умолчанию дженерики в Java инвариантны. Это значит, что List<Cat> не является подтипом List<Animal>, даже если Cat extends Animal.
List<Cat> cats = new ArrayList<>();
List<Animal> animals = cats; //  Ошибка компиляции

Также помимо структур данных имеются и другие случаи ипользования ковариантности и контравариантности, например
1. Функциональные интерфейсы
2. Методы с обобщёнными параметрами
3. Интерфейсы и абстракции
4. Делегаты и замыкания (пока вообще не понимаю что это такое)
5. API-интерфейсы, DTO и обработчики
Но и в них присутствует общее правило ограничений 
Можно возвращать (как бы «читать» тип) — если он ? extends
Можно принимать (как бы «записывать») — если он ? super
Эти ограничения всё равно нужны, чтобы компилятор мог гарантировать типобезопасность




  
