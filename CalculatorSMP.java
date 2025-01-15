public class CalculatorSMP {
    public static void main(String[] args) {
        String question = Operations.MakeFormula();
        System.out.println("Fórmula generada: " + question);
        try {
            String result = Operations.Solve(question);
            System.out.println("Resultado: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error al resolver fórmula: " + e.getMessage());
        }
    }
}
