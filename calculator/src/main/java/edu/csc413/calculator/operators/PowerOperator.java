package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {

    @Override
   public int priority(){
       return 3;
   }
    @Override
    public Operand execute(Operand op1, Operand op2) {
       Operand Power = new Operand(power(op1.getValue(), op2.getValue()));
       return Power;
    }

    public int power (int i, int j){
       int Power = i;
       for (int count = 2; count <= j; count++){
           Power = Power*i;
       }
       return Power;
    }
}
