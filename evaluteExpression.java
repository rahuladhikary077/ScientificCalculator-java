import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class evaluateExpression {
    private String evaluateExpression(String expression) {
        try {
            Calculable calculable = new ExpressionBuilder(expression).build();
            double result = calculable.calculate();
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }

    public static void main(String[] args) {
        evaluateExpression calc = new evaluateExpression();
        System.out.println(calc.evaluateExpression("2+3*4"));
    }
}
