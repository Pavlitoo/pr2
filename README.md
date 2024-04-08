### Використання командного рядка Java для обчислення кількість повних тетрад у двійковому поданні заданого

Даний Java-застосунок дозволяє обчислювати кількість повних тетрад у двійковому поданні заданого
десяткового числа, та тестувати різноманітні функції через командний рядок.

## Приклад використання:



    ```bash
    Аргумент 1: 5
    Аргумент 2: 10
    ```

## BinaryNumber.java

```java
package pr2;


public class BinaryNumber {
    public static void main(String[] args) {
        int decimalNumber = 123; // Заданное десятичное число
        int binaryOnesCount = countOnesInBinary(decimalNumber);
        int fullQuartets = binaryOnesCount / 4; // Количество полных тетрад
        System.out.println("Number of full quartets in binary representation of " + decimalNumber + " is " + fullQuartets);
    }

    private static int countOnesInBinary(int decimalNumber) {
        String binaryString = Integer.toBinaryString(decimalNumber);
        int count = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}
```

## CalculationResult.java

```java
package pr2;

import java.io.Serializable;

public class CalculationResult implements Serializable {
    private int parameter1;
    private int parameter2;
    private int result;

    public CalculationResult(int parameter1, int parameter2, int result) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.result = result;
    }

    // Геттеры и сеттеры для всех полей

    public int getResult() {
        return result;
    }

    public int getParameter1() {
        return parameter1;
    }

    public int getParameter2() {
        return parameter2;
    }

    @Override
    public String toString() {
        return "CalculationResult{" +
                "parameter1=" + parameter1 +
                ", parameter2=" + parameter2 +
                ", result=" + result +
                '}';
    }
}
```

## SerializationDemo.java

```java
package pr2;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        CalculationResult result = new CalculationResult(10, 20, 200);

        // Сохранение объекта в файл
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("calculation_result.ser"))) {
            outputStream.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Восстановление объекта из файла
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("calculation_result.ser"))) {
            CalculationResult restoredResult = (CalculationResult) inputStream.readObject();
            System.out.println("Restored result: " + restoredResult);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## CalculationTest.java

```java
package Test;

import java.io.*;

import pr2.CalculationResult;

public class CalculationTest {
    public static void main(String[] args) {
        // Создаем объект CalculationResult с заданными параметрами
        CalculationResult result = new CalculationResult(5, 10, 50);

        // Тестирование результатов вычислений
        if (result.getResult() != 50) {
            throw new AssertionError("Calculation result is incorrect");
        } else {
            System.out.println("Calculation result is correct: " + result.getResult());
        }

        // Тестирование сериализации/десериализации
        try {
            // Сериализация объекта result
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(result);

            // Десериализация объекта из сериализованных данных
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            CalculationResult deserializedResult = (CalculationResult) objectInputStream.readObject();

            // Проверка десериализованного объекта
            if (deserializedResult.getParameter1() != 5 || deserializedResult.getParameter2() != 10 || deserializedResult.getResult() != 50) {
                throw new AssertionError("Deserialization failed: unexpected parameters or result");
            } else {
                System.out.println("Deserialization successful:");
                System.out.println("Parameter1: " + deserializedResult.getParameter1());
                System.out.println("Parameter2: " + deserializedResult.getParameter2());
                System.out.println("Result: " + deserializedResult.getResult());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

### Ось результат ↓

![Результат](/screenshot/Pr2.jpg)




