import exceptions.IncorrectDigitsException;

public class Converter {
    int getDecimal(String digit) {
        int a = -1;
        try {
            a = Integer.parseInt(digit);
        } catch (Error ignore) {
        } finally {
            return a;
        }
    }

    int value(char r) throws IncorrectDigitsException {
        if (r == 'I' || r == 'i') return 1;
        if (r == 'V' || r == 'v') return 5;
        if (r == 'X' || r == 'x') return 10;
        if (r == 'L' || r == 'l') return 50;
        if (r == 'C' || r == 'c') return 100;
        if (r == 'D' || r == 'd') return 500;
        if (r == 'M' || r == 'm') return 1000;
        throw new IncorrectDigitsException(String.valueOf(r));
    }

    int getDecimalFromRoman(String str) throws IncorrectDigitsException {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            int s1 = value(str.charAt(i));
            if (i + 1 < str.length()) {
                int s2 = value(str.charAt(i + 1));
                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
            }
        }
        return res;
    }

    static int subDigit(char num1, char num2, int i, char[] c) {
        c[i++] = num1;
        c[i++] = num2;
        return i;
    }

    static int digit(char ch, int n, int i, char[] c) {
        for (int j = 0; j < n; j++) {
            c[i++] = ch;
        }
        return i;
    }

    String getRomanFromDecimal(int number) {
        char c[] = new char[10001];
        int i = 0;

        while (number != 0) {
            if (number >= 1000) {
                i = digit('M', number / 1000, i, c);
                number = number % 1000;
            } else if (number >= 500) {
                if (number < 900) {
                    i = digit('D', number / 500, i, c);
                    number = number % 500;
                } else {
                    i = subDigit('C', 'M', i, c);
                    number = number % 100;
                }
            } else if (number >= 100) {
                if (number < 400) {
                    i = digit('C', number / 100, i, c);
                    number = number % 100;
                } else {
                    i = subDigit('C', 'D', i, c);
                    number = number % 100;
                }
            } else if (number >= 50) {
                if (number < 90) {
                    i = digit('L', number / 50, i, c);
                    number = number % 50;
                } else {
                    i = subDigit('X', 'C', i, c);
                    number = number % 10;
                }
            } else if (number >= 10) {
                if (number < 40) {
                    i = digit('X', number / 10, i, c);
                    number = number % 10;
                } else {
                    i = subDigit('X', 'L', i, c);
                    number = number % 10;
                }
            } else if (number >= 5) {
                if (number < 9) {
                    i = digit('V', number / 5, i, c);
                    number = number % 5;
                } else {
                    i = subDigit('I', 'X', i, c);
                    number = 0;
                }
            } else if (number >= 1) {
                if (number < 4) {
                    i = digit('I', number, i, c);
                    number = 0;
                } else {
                    i = subDigit('I', 'V', i, c);
                    number = 0;
                }
            }
        }

        String result = "";
        for (int j = 0; j < i; j++) {
            result += c[j];
        }
        return result;
    }

}