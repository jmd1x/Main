import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Input:");
        String input = in.nextLine();
        System.out.println(calc(input));
    }
    public static String calc (String input) throws Exception {
        if (!(input.contains("+") || input.contains("-") || input.contains("/") || input.contains("*"))) {
            throw new Exception("не является математической операцией");
        }
        int count = 0;
        String[] expression = input.split(" ");
        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals("+") || expression[i].equals("-") || expression[i].equals("/") || expression[i].equals("*")) {
                count++;
            }
        }
        if (count>1) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String[] romanNumbers = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] romanTens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        int a = toArabic(romanNumbers, expression[0]);
        int b = toArabic(romanNumbers, expression[2]);
        int result = 0;
        String resultFinal = null;
        if ((a == -1 && b != -1) || (a != -1 && b == -1)) {
            throw new Exception("используются одновременно разные системы счисления");
        }
        else if (a != -1 && b != -1) {
            result = calculate(expression, a, b);
            if (result < 1) {
                throw new Exception("в римской системе нет отрицательных чисел");
            }
            else {
                resultFinal = (toRoman(romanNumbers, romanTens,result));
            }
        }
        else if (a == -1 && b == -1) {
            a = Integer.parseInt(expression[0]);
            b = Integer.parseInt(expression[2]);
            if (a > 10 || b > 10 || a < 1 || b < 1){
                throw new NumberFormatException("Ввод чисел только от 1 до 10");
            }
            resultFinal = (Integer.toString(calculate(expression, a, b)));
        }

        return resultFinal;
    }
    static int toArabic(String[] romanNumbers, String romanNum) {
        for (int i = 0; i < romanNumbers.length; i++) {
            if (romanNumbers[i].equals(romanNum)) {
                return i;
            }
        }
        return -1; // если цифра не найдена
    }
    static String toRoman (String[] romanNumbers, String[] romanTens, int result){
        int resultTens = result / 10;
        int resultNum = result % 10;
        return romanTens[resultTens] + romanNumbers[resultNum];
    }
    static int calculate(String[] expression, int a, int b) {
        int result = 0;
        if (expression[1].equals("+")) {
            result = a + b;
        } else if (expression[1].equals("-")) {
            result = a - b;
        } else if (expression[1].equals("*")) {
            result = a * b;
        } else if (expression[1].equals("/")) {
            result = a / b;
        }  return result;
    }
}

