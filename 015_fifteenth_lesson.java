// Как плохой пример с if/else
public class HistoryPeriod {
    public String era; // "ancient", "medieval", "modern"

    public void describe() {
        if ("ancient".equals(era)) {
            System.out.println("Жил в античные времена");
        } else if ("medieval".equals(era)) {
            System.out.println("Жил в средние века");
        } else if ("modern".equals(era)) {
            System.out.println("Жил в новое время");
        }
    }
}

//Вместо некоторого поля родительского класса с набором предопределённых значений (как в случае с полем female) применяется наследование.
//Родительский класс
public abstract class HistoryPeriod {
    private final String name;

    public HistoriPeriod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void describe();
}

//Конкретные классы
public class AncientPeriod extends HistoryPeriod {
    public AncientPeriod(String name) {
        super(name);
    }

    @Override
    public void describe() {
        System.out.println(getName() + " — жил в античные времена.");
    }
}

public class MedievalPeriod extends HistoryPeriod {
    public MedievalPeriod(String name) {
        super(name);
    }

    @Override
    public void describe() {
        System.out.println(getName() + " — жил в средние века.");
    }
}
