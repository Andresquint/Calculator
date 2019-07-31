package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "() +-*/^ ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval( String expression ) {
      String token;
      this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

      while (this.tokenizer.hasMoreTokens()) {
          // filter out spaces
          if (!(token = this.tokenizer.nextToken()).equals(" ")) {
              // check if token is an operand
              if (Operand.check(token)) {
                  operandStack.push(new Operand(token));
              } else {
                  if (!Operator.check(token)) {
                      System.out.println("*****invalid token******");
                      throw new RuntimeException("*****invalid token******");
                  }

                  Operator newOperator = Operator.getOperator(token);

                  if (token.equals("(")) {
                      operatorStack.push(new LeftParenthesis());
                      continue;
                  } if (token.equals(")")) {
                      while(operatorStack.peek().priority() > 0) {
                          Operator oldOpr = operatorStack.pop();
                          Operand op2 = operandStack.pop();
                          Operand op1 = operandStack.pop();
                          operandStack.push(oldOpr.execute(op1, op2));

                      }   operatorStack.pop();continue;

                  }

                  if (operatorStack.isEmpty() || operatorStack.peek().priority() < newOperator.priority()) {
                      operatorStack.add(newOperator); continue;

                  } else {
                      Operator oldOpr = operatorStack.pop();
                      Operand op2 = operandStack.pop();
                      Operand op1 = operandStack.pop();
                      operandStack.push(oldOpr.execute(op1, op2));
                  }
                  operatorStack.push(newOperator);
              }
          }
      } while (!operatorStack.isEmpty()) {
          Operator oldOpr = operatorStack.pop();
          Operand op2 = operandStack.pop();
          Operand op1 = operandStack.pop();
          operandStack.push(oldOpr.execute(op1, op2));

      } if (operandStack.size() != 1) {
          operandStack.removeAllElements();
      }

      return operandStack.pop().getValue();
  }
}


// Control gets here when we've picked up all of the tokens; you must add
      // code to complete the evaluation - consider how the code given here
      // will evaluate the expression 1+2*3
      // When we have no more tokens to scan, the operand stack will contain 1 2
      // and the operator stack will have + * with 2 and * on the top;
      // In order to complete the evaluation we must empty the stacks (except
      // the init operator on the operator stack); that is, we should keep
      // evaluating the operator stack until it only contains the init operator;
      // Suggestion: create a method that takes an operator as argument and
      // then executes the while loop.



