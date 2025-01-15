

public class CalculatorSMP {

    public static void main(String[] args) {
        // Generar y resolver una fórmula aleatoria
        String question = Operations.MakeFormula();
        System.out.println("Fórmula generada: " + question);
        String ret = Operations.Solve(question);
        System.out.println("Resultado: " + ret);
    }
}

