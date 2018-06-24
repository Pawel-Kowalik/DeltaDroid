package com.pawel14157.deltadroid;

import android.content.Context;
import android.icu.math.BigDecimal;
import android.icu.text.DecimalFormat;
import android.icu.text.StringPrepParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.pawel14157.deltadroid.R.string.A;
import static com.pawel14157.deltadroid.R.string.inputData;

public class Resolve extends AppCompatActivity {
    private double  a;
    private double b;
    private double c;
    private double fDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolve);


    }
    /**
     * calcualte delta
     * @param a
     * @param b
     * @param c
     * @return delta
     */
    double calculateDelta(double a, double b, double c){
        double delta;
        delta = (b*b) - (4*a*c);
        return delta;
    }

    /**
     * calculate x1 and x2 for delta > 0
     * @param delta
     * @param a
     * @param b
     * @return x1x2[0] for x1 and x1x2[1] for x2
     */
    double[] deltaGreater(double delta, double a, double b){
        double sqrtDelta = Math.sqrt(delta);
        double [] x1x2 = new double[2];
        x1x2[0] = ((-b-sqrtDelta)/(2*a));
        x1x2[1] = ((-b+sqrtDelta)/(2*a));

        return x1x2;
    }

    /**
     * calculate x for delta = 0 (x1=x2)
     * @param delta
     * @param a
     * @param b
     * @return x
     */
    double deltaEquals(double delta, double a, double b){
        double x;
        x = -((b)/(2*a));
        return x;
    }

    /**
     * calculate p and q, W(p,q)
     * @param delta
     * @param a
     * @param b
     * @return pq[0] for p and pq[1] for q
     */
    double[] top(double delta, double a, double b){
        double [] pq = new double[2];
        pq[0] = -((b)/(2*a));
        pq[1] = -((delta)/(4*a));
        return pq;
    }

    double round(double number){
        number = Math.round(number * 100);
        number = number/100;
        return number;
    }

    /**
     *
     * @param toast
     */
    void toast(Toast toast){
        toast.show();
    }

    /**
     *
     * @param view
     */
    public void resolveDelta(View view) {
        EditText inputA = (EditText) findViewById(R.id.inputA);
        EditText inputB = (EditText) findViewById(R.id.inputB);
        EditText inputC = (EditText) findViewById(R.id.inputC);

        TextView delta = (TextView) findViewById(R.id.delta);
        TextView sqrtDelta = (TextView) findViewById(R.id.sqrtDelta);
        TextView x1 = (TextView) findViewById(R.id.x1);
        TextView x2 = (TextView) findViewById(R.id.x2);
        TextView p = (TextView) findViewById(R.id.p);
        TextView q = (TextView) findViewById(R.id.q);
        TextView imageA = (TextView) findViewById(R.id.imageA);
        TextView imageB = (TextView) findViewById(R.id.imageB);
        TextView imageC = (TextView) findViewById(R.id.imageC);

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


        if(inputA.getText().toString().isEmpty() || inputB.getText().toString().isEmpty() || inputC.getText().toString().isEmpty())
            toast(Toast.makeText(getApplicationContext(), R.string.inputData, Toast.LENGTH_SHORT));
        else if(inputA.getText().toString().equals("0"))
            toast(Toast.makeText(getApplicationContext(), R.string.A, Toast.LENGTH_SHORT));
        else{
            a = Double.parseDouble(inputA.getText().toString());
            b = Double.parseDouble(inputB.getText().toString());
            c = Double.parseDouble(inputC.getText().toString());

            imageA.setText(String.valueOf(checkNumber(a)));
            if (b >= 0)
                imageB.setText("+" + String.valueOf(checkNumber(b)));
            else
                imageB.setText(String.valueOf(checkNumber(b)));

            if (c >= 0)
                imageC.setText("+" + String.valueOf(checkNumber(c)));
            else
                imageC.setText(String.valueOf(checkNumber(c)));

            fDelta = calculateDelta(a, b, c);
            delta.setText("Δ="+String.valueOf(round(fDelta)));

            if(fDelta>=0)
                sqrtDelta.setText("√Δ="+String.valueOf(round(Math.sqrt(fDelta))));

            if(fDelta>0) {
                    x1.setText("x1=" + String.valueOf(round(deltaGreater(fDelta, a, b)[0])));
                    x2.setText("x2=" + String.valueOf(round(deltaGreater(fDelta, a, b)[1])));
            }
            else if(fDelta == 0){
                x1.setText("x="+String.valueOf(round(deltaEquals(fDelta, a, b))));
                x2.setText("");
            }
            else if(fDelta < 0) {
                x1.setText(R.string.notExist);
                x2.setText("");
            }

            p.setText("p="+String.valueOf(round(top(fDelta, a, b)[0])));
            q.setText("q="+String.valueOf(round(top(fDelta, a, b)[1])));
        }
        }

        public String checkNumber(double number){
            String checkNumberString;
            StringBuilder stringBuilder = new StringBuilder();
            if(number % 1.0 == 0){
                checkNumberString = String.valueOf(number);
                for (int i=0; i<checkNumberString.length(); i++){
                    if(checkNumberString.charAt(i) == '.'){
                        break;
                    }
                    else {
                        stringBuilder.append(checkNumberString.charAt(i));
                    }
                }
            }
            else {
                return String.valueOf(number);
            }
            return stringBuilder.toString();
        }

}
