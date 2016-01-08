package edu.dpiottipurdue.android_calculator;

import java.util.Stack;


/**
 * Created by dpiotti on 12/3/15.
 */

public class Calculator {

    private CalculatorViewInterface view;
    private Stack<String> stack = new Stack<String>();
    private boolean equals = false;

    public Calculator(CalculatorViewInterface view) {
        this.view = view;
    }


    public void inputDigit(char act) {

        String str;
        String lastSpecial = "";

        if (stack.size() == 0)
            stack.push("");


        if (equals) {
            stack.push(Character.toString(act));
            this.view.display(stack.peek());
        }

        if (!equals) {
            if (stack.size() > 0 && stack.peek().contains(".")) {
                str = stack.peek();

                if (str.length() > 3 && str.charAt(str.length() - 3) == '.') {
                    char[] charArray = str.toCharArray();
                    for (char c : charArray) {
                        if (!Character.isDigit(c))
                            lastSpecial = Character.toString(c);
                    }

                    if (lastSpecial.equals("."))
                        this.view.invalid();
                } else {
                    stack.push(str + act);
                    this.view.display(stack.peek());
                }

            } else if (stack.size() == 0) {
                stack.push("" + act);
                this.view.display(stack.peek());

            } else if (stack.size() > 0) {
                stack.push(stack.peek() + act);
                this.view.display(stack.peek());
            }
        }
        equals = false;
    }


    public void equal() {

        String str;
        String num1 = "";
        String num2 = "";
        String resultS = "";
        double resultD = 0;
        boolean valid = true;

        if (stack.size() > 0)
            str = stack.peek();

        else str = "";

        if (!str.contains("+") && !str.contains("-") && !str.contains("*") && !str.contains("/")) {
            this.view.invalid();
            valid = false;
        }

        if (valid) {
            if (str.contains("+") && !str.substring(str.length() - 1).equals("+")) {
                num1 = str.substring(0, str.indexOf("+"));
                num2 = str.substring(str.indexOf("+") + 1);
                resultD = Double.parseDouble(num1) + Double.parseDouble(num2);


            } else if (str.contains("-") && !str.substring(str.length() - 1).equals("-")) {
                num1 = str.substring(0, str.indexOf("-"));
                num2 = str.substring(str.indexOf("-") + 1);
                resultD = Double.parseDouble(num1) - Double.parseDouble(num2);


            } else if (str.contains("*") && !str.substring(str.length() - 1).equals("*")) {
                num1 = str.substring(0, str.indexOf("*"));
                num2 = str.substring(str.indexOf("*") + 1);
                resultD = Double.parseDouble(num1) * Double.parseDouble(num2);


            } else if (str.contains("/") & !str.substring(str.length() - 1).equals("/")) {
                num1 = str.substring(0, str.indexOf("/"));
                num2 = str.substring(str.indexOf("/") + 1);

                if (Double.parseDouble(num1) == 0 && Double.parseDouble(num2) == 0)
                    resultS = "NaN";

                else if (Double.parseDouble(num1) > 0 && Double.parseDouble(num2) == 0)
                    resultS = "Infinity";

                else if (Double.parseDouble(num1) < 0 && Double.parseDouble(num2) == 0)
                    resultS = "-Infinity";

                else {
                    resultD = Double.parseDouble(num1) / Double.parseDouble(num2);
                }
            } else {
                this.view.invalid();
                valid = false;
            }
        }
        if (valid) {
            if (resultS.equals(""))
                resultS = String.format("%.02f", resultD);

            if (resultS.equals(""))
                this.view.invalid();

            else this.view.display(resultS);
            stack.push(resultS);

            equals = true;
        }
    }

    public void dot() {

        String lastSpecial = "";

        if (stack.size() == 0)
            this.view.invalid();

        else if (stack.peek().contains(".")) {

            char[] charArray = stack.peek().toCharArray();
            for (char c : charArray) {
                if (!Character.isDigit(c))
                    lastSpecial = Character.toString(c);
            }
            if (lastSpecial.equals("."))
                this.view.invalid();

            else this.view.display(stack.push(stack.peek() + "."));

        } else {
            stack.push(stack.peek() + ".");
            this.view.display(stack.peek());
        }
    }

    public void delete() {

        try {

            stack.pop();
            this.view.display(stack.peek());

        } catch (Exception e) { this.view.invalid(); }

    }



    public void operator(char op) {

        String str = "";
        String lastChar = "";

        if (stack.size() == 0)
            this.view.invalid();

        else {
            str = stack.peek();
            lastChar = str.substring(str.length() - 1);

            if (lastChar.equals("N") || lastChar.equals("y")) {
                this.view.invalid();

            } else if (lastChar.equals(" ")) {
                stack.pop();
                stack.push(stack.peek() + " " + op + " ");
                this.view.display(stack.peek());
                equals = false;


            } else if (stack.peek().contains("+") ||
                    stack.peek().contains("-") ||
                    stack.peek().contains("*") ||
                    stack.peek().contains("/")) {
                this.view.invalid();
                equals = false;
            }

            else {
                stack.push(stack.peek() + " " + op + " ");
                this.view.display(stack.peek());
                equals = false;

            }

        }

    }
}
