package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.operators.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextField;



public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();

    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private boolean firstInput = true;
    private String num1 = "";
    private String num2 = "";
    private char operator;

    public void actionPerformed(ActionEvent arg0) {
        Evaluator evaluate = new Evaluator();
        String text = txField.getText();
        String str = String.valueOf(arg0.getActionCommand());
        char deliNum = str.charAt(0);

        switch (deliNum) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
            case '^':

                str = this.txField.getText() + arg0.getActionCommand();
                this.txField.setText(str);
                operator = deliNum;
                this.txField.setText(str);
                System.out.println(operator);
                if (firstInput) {
                    num1 = num1 + deliNum;
                    txField.setText(num1);
                } else {
                    num2 = num2 + deliNum;
                    txField.setText(num2);
                }
                break;

             case '=':
                 String newText = Integer.toString(evaluate.eval(text));
                 txField.setText(text + "=" + newText);
                 break;

            case 'C':
                txField.setText("");
                num1 = "";
                num2 = "";
                firstInput = true;
                break;
        }
    }
}
