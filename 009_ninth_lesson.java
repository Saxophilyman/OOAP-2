В данной задаче без GPT тоже не обошлось, иногда приходит ступор, где даже не понятно каким образом это делать.
Свой изначальный вариант мне показался слишком примитивным и не соответствующим глубокому копированию
Воспользуюсь подсказкой, но буду полагаться на эталонный вариант

import java.io.*;
import java.util.Objects;

public abstract class General implements Cloneable, Serializable {

    // 1. Глубокое копирование (deep copy)
    public General deepCopy() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
            oos.flush();

            try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                 ObjectInputStream ois = new ObjectInputStream(bis)) {
                return (General) ois.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при глубоком копировании", e);
        }
    }

    // 2. Клонирование
    @Override
    public General clone() {
        try {
            return (General) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Ошибка при клонировании", e);
        }
    }

    // 3. Сравнение (equals + hashCode)
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // можно заменить на Objects.equals(this, obj), если будет полиморфизм
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // 4. Сериализация в строку
    public String serializeToString() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
            return bos.toString(); // в реальности лучше Base64
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сериализации", e);
        }
    }

    // 5. Десериализация из строки (Base64 безопаснее!)
    public static General deserializeFromBytes(byte[] data) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (General) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при десериализации", e);
        }
    }

    // 6. Печать (наглядное представление)
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    }

    // 7. Проверка типа и получение класса
    public boolean isInstanceOf(Class<?> clazz) {
        return clazz.isInstance(this);
    }

    public Class<?> getRealType() {
        return this.getClass();
    }
}

public class Any extends General {
    // Пока пустой — сюда можно будет добавлять общее поведение в будущем
}
