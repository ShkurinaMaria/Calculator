import exceptions.IncorrectDigitsException;
import exceptions.IncorrectOperationException;
import exceptions.IncorrectStringException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IncorrectStringException, IncorrectOperationException, IncorrectDigitsException {
        List<String> operations = Arrays.asList("-", "+", "*", "/");
        Converter converter = new Converter();
        Scanner in = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String[] expression = in.nextLine().trim().split(" ");

        if (expression.length != 3) {
            throw new IncorrectStringException("Incorrect input string");
        }

        if (!operations.contains(expression[1])) {
            throw new IncorrectOperationException("Incorrect operation " + expression[1]);
        }

        boolean decimal = converter.getDecimal(expression[0]) != -1 && converter.getDecimal(expression[2]) != -1;
        boolean roman = true;

        try {
            converter.getDecimalFromRoman(expression[0]);
            converter.getDecimalFromRoman(expression[2]);
        } catch (IncorrectDigitsException ignore) {
            roman = false;
        }

        int a = 0, b = 0;
        if (decimal) {
            a = converter.getDecimal(expression[0]);
            b = converter.getDecimal(expression[2]);
            int result = Calculator.operation(a, expression[1], b);
            System.out.println(result);
        } else if (roman) {
            a = converter.getDecimalFromRoman(expression[0]);
            b = converter.getDecimalFromRoman(expression[2]);
            int result = Calculator.operation(a, expression[1], b);
            System.out.println(converter.getRomanFromDecimal(result));
        } else {
            throw new IncorrectDigitsException(Arrays.toString(expression));
        }
    }
}
