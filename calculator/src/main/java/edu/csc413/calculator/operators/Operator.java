package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;
import java.util.Stack;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );
   private static HashMap<String, Operator> op; //never do the hashmap public

    static {
        op = new HashMap<String, Operator>();
        op.put("+", new AddOperator());
        op.put("/", new DivideOperator());
        op.put("*", new MultiplyOperator());
        op.put("^", new PowerOperator());
        op.put("-", new SubtractOperator());
        op.put("(", new LeftParenthesis());
        op.put(")", new RightParenthesis());
    }

    public abstract int priority();

    public abstract Operand execute(Operand op1, Operand op2 );

    public static boolean check(String token) {
        if(!op.containsKey(token))
            return false;
        return true;
        }

    public static Operator getOperator(String token) {
        return op.get(token);
    }
    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */

}