
public class Calculator {
    static int operation(int a, String op, int b) {
        int result;
        switch (op) {
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                result = a + b;
        }
        return result;
    }

}
