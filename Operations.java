import java.util.Stack;

public class Operations {
    private static String[] op = { "+", "-", "*", "/" }; // Operadores

    public static String MakeFormula() {
        StringBuilder build = new StringBuilder();
        int count = (int) (Math.random() * 2) + 1; // Generar cantidad aleatoria de operaciones
        int number1 = (int) (Math.random() * 99) + 1;
        build.append(number1);

        for (int i = 0; i < count; i++) {
            int operation = (int) (Math.random() * 4); // Selección de operador
            int number2 = (int) (Math.random() * 99) + 1;
            build.append(op[operation]).append(number2);
        }
        return build.toString();
    }

    public static String Solve(String formula) throws ArithmeticException {
        Stack<String> tempStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        int len = formula.length();
        int k = 0;
        for (int j = -1; j < len - 1; j++) {
            char formulaChar = formula.charAt(j + 1);
            if (j == len - 2 || "+-*/".indexOf(formulaChar) != -1) {
                if (j == len - 2) {
                    tempStack.push(formula.substring(k));
                } else {
                    if (k < j) {
                        tempStack.push(formula.substring(k, j + 1));
                    }
                    while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(formulaChar)) {
                        tempStack.push(String.valueOf(operatorStack.pop()));
                    }
                    operatorStack.push(formulaChar);
                }
                k = j + 2;
            }
        }

        while (!operatorStack.isEmpty()) {
            tempStack.push(String.valueOf(operatorStack.pop()));
        }

        Stack<String> calcStack = new Stack<>();
        for (String token : tempStack) {
            if (!"+-*/".contains(token)) {
                calcStack.push(token);
            } else {
                int b = Integer.parseInt(calcStack.pop());
                int a = Integer.parseInt(calcStack.pop());
                calcStack.push(String.valueOf(applyOperation(a, b, token)));
            }
        }

        return formula + "=" + calcStack.pop();
    }

    private static int precedence(char operator) {
        return (operator == '+' || operator == '-') ? 1 : 2;
    }

    private static int applyOperation(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) throw new ArithmeticException("División por cero");
                return a / b;
            default: throw new IllegalArgumentException("Operador desconocido: " + op);
        }
    }
}
