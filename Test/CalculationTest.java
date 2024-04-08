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
