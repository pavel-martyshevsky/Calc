
import java.io.IOException;
import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите пример, используя только арабские или только римские числа от 0 до 10 (от O до X).\n"
                + "Вам доступны операции: сложение(+), вычитание(-), умножение(*), деление(/). \n"
                + "Калькулятор работает только с целыми числами. \n"
                + "Введите выражение через пробел:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 3)
            throw new Exception("Неверный формат примера. Он должен содержать только две переменные и математическую операцию.");
        String operation = String.valueOf(parts[1].charAt(0));
        try {
            int arabicNumber1 = Integer.parseInt(parts[0]);
            int arabicNumber2 = Integer.parseInt(parts[2]);
            if ((arabicNumber1 < 1) || (arabicNumber1 > 10))
                throw new IOException("Вы ввели первое арабское число меньше 1 или больше 10.");
            else if (arabicNumber2 < 1 || arabicNumber2 > 10)
                throw new IOException("Вы ввели второе арабское число меньше 1 или больше 10.");
            else return String.valueOf(calculation(arabicNumber1, arabicNumber2, operation));
        } catch (NumberFormatException e) {
            int romanianNumber1 = 0;
            int romanianNumber2 = 0;
            String RomanianNumber1 = parts[0];
            String RomanianNumber2 = parts[2];
            boolean indicatorRomanianNumber1 = false;
            boolean indicatorRomanianNumber2 = false;
            for (RomanNumbers element1 : RomanNumbers.values()) {
                if (RomanianNumber1.equals(element1.name())) {
                    if ((element1.getTranslationToArabic() < 1) || (element1.getTranslationToArabic() > 10)) {
                        throw new IOException("Вы ввели первое римское число меньше I или больше X.");
                    } else romanianNumber1 = element1.getTranslationToArabic();
                    indicatorRomanianNumber1 = true;
                }
            }
            if (!indicatorRomanianNumber1)
                throw new IOException("Выражение должно состоять только из римских или только из целых арабских чисел.");

            for (RomanNumbers element2 : RomanNumbers.values()) {
                if (RomanianNumber2.equals(element2.name())) {
                    if ((element2.getTranslationToArabic() < 1) || (element2.getTranslationToArabic() > 10)) {
                        throw new IOException("Вы ввели второе римское число меньше I или больше X.");
                    } else romanianNumber2 = element2.getTranslationToArabic();
                    indicatorRomanianNumber2 = true;
                }
            }
            if (!indicatorRomanianNumber2)
                throw new IOException("Выражение должно состоять только из римских или только из целых арабских чисел.");
            int resultOfCalculation = calculation(romanianNumber1, romanianNumber2, operation);
            String result = null;
            if (resultOfCalculation == 0) throw new Exception("Результат вычисления римских чисел не положительный.");
            for (RomanNumbers element : RomanNumbers.values()) {
                if (resultOfCalculation == element.getTranslationToArabic())
                    result = String.valueOf(element);
            }
            if (result == null) throw new Exception("Результат вычисления римских чисел не положительный.");
            return result;
        }
    }

    private static int calculation(int a, int b, String operation) throws IOException {
        if (operation.equals("+")) return a + b;
        else if (operation.equals("-")) return a - b;
        else if (operation.equals("*")) return a * b;
        else if (operation.equals("/")) return a / b;
        else throw new IOException("Вы ввели неверную математическую операцию");
    }
}
