package edu.dpiottipurdue.android_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView result;
    private Calculator calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.answer);

        CalculatorView cv = new CalculatorView(result);

        calc = new Calculator(cv);

    }

    public void zero(View view) {

        calc.inputDigit('0');

    }

    public void one(View view) {

        calc.inputDigit('1');

    }

    public void two(View view) {

        calc.inputDigit('2');

    }

    public void three(View view) {

        calc.inputDigit('3');

    }

    public void four(View view) {

        calc.inputDigit('4');

    }

    public void five(View view) {

        calc.inputDigit('5');


    }

    public void six(View view) {

        calc.inputDigit('6');

    }

    public void seven(View view) {

        calc.inputDigit('7');

    }

    public void eight(View view) {

        calc.inputDigit('8');

    }

    public void nine(View view) {

        calc.inputDigit('9');

    }

    public void div(View view) {

        calc.operator('/');

    }

    public void mult(View view) {

        calc.operator('*');

    }

    public void add(View view) {

        calc.operator('+');

    }

    public void sub(View view) {

        calc.operator('-');

    }

    public void equals(View view) {

        calc.equal();

    }

    public void dot(View view) {

        calc.dot();

    }

    public void del(View view) {

        calc.delete();
    }

}
