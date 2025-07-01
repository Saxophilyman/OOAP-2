В Java имеется возможность запрета переопределения методов в потомках, если в методе ставится ключевое слово final

public class Y {
//переопределяемый метод
    public String methodOpen() {
        return methodFinal();
    }
//непереопределяемый метод
    public final String methodFinal() {
        return "Базовая реализация";
    }
}

public class Z extends Y {
    @Override
    public String methodOpen() {
        return "Своя реализация в классе Z"; //стоит применять разумно
    }
}
