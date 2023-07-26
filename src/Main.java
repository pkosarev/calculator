import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        String[] elements = input.split(" ");
        if (elements.length != 3) {
            throw new IllegalArgumentException("Неправильный формат выражения");
        }

        String num1Str = elements[0];
        String operator = elements[1];
        String num2Str = elements[2];

        int num1, num2;

        boolean isRoman = false;
        try {
            num1 = Integer.parseInt(num1Str);
            num2 = Integer.parseInt(num2Str);
        } catch (NumberFormatException e) {
            num1 = RomanToArabicConverter.convert(num1Str);
            num2 = RomanToArabicConverter.convert(num2Str);
            isRoman = true;
        }

        if (!isRoman && (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10)) {
            throw new IllegalArgumentException("Калькулятор принимает только числа от 1 до 10 включительно");
        }

        int result;
        switch (operator) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> {
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль невозможно");
                }
                result = num1 / num2;
            }
            default -> throw new IllegalArgumentException("Неподдерживаемая арифметическая операция: " + operator);
        }

        if (!isRoman && result <= 0) {
            throw new IllegalArgumentException("Результат работы калькулятора с римскими числами может быть только положительным числом");
        }

        if (isRoman) {
            return RomanToArabicConverter.convert(result);
        } else {
            return String.valueOf(result);
        }
    }
}
