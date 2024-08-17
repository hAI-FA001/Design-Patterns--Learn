// based on context, interpret something
// define a "grammar" for evaluating "expression"
// expression has 2 parts: terminal, non-terminal
// e.g. a * b: (a*b) is non-terminal (i.e. has children if draw expression as a tree), a & b are terminals (no further children)

package behavioral;

import java.util.HashMap;
import java.util.Map;

public class InterpreterPattern {
    public static void main(String[] args) {
        // initializer the context
        Context context = new Context();
        context.put("a", 2);
        context.put("b", 4);

        // create an expression: a * b
        AbstractExpression multiply = new MultiplyNonTerminalExpression(
                new NumberTerminalExpression("a"), new NumberTerminalExpression("b"));

        // under the given context, a is 2 and b is 4
        System.out.println(multiply.interpret(context));

        // change the context
        context.put("a", 100);
        context.put("b", -2);
        System.out.println(multiply.interpret(context));
    }
}

class Context {
    Map<String, Integer> contextMapping = new HashMap<>();

    void put(String strVal, int intVal) {
        contextMapping.put(strVal, intVal);
    }

    int get(String strVal) {
        return contextMapping.get(strVal);
    }
}

class NumberTerminalExpression implements AbstractExpression {
    String strVal;

    NumberTerminalExpression(String strVal) {
        this.strVal = strVal;
    }

    @Override
    public int interpret(Context context) {
        return context.get(this.strVal);
    }
}

class MultiplyNonTerminalExpression implements AbstractExpression {
    AbstractExpression leftExpression, rightExpression;

    public MultiplyNonTerminalExpression(AbstractExpression leftExpression, AbstractExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(Context context) {
        return leftExpression.interpret(context) * rightExpression.interpret(context);
    }

}

interface AbstractExpression {
    int interpret(Context context);
}