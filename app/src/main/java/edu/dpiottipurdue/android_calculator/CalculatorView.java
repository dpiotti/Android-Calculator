package edu.dpiottipurdue.android_calculator;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dpiotti on 12/1/15.
 */
public class CalculatorView implements CalculatorViewInterface {

    private TextView response;
    private int color = 0;

    public CalculatorView (TextView response) {
        this.response = response;
    }

    public void display(String val) {

        if (color == Color.RED) {
            response.setBackgroundColor(0);
        }

        response.setText(val);

    }

    public void invalid() {

        response.setBackgroundColor(Color.RED);
        color = Color.RED;

    }
}
