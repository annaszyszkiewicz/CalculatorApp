package edu.ib.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Calculate x = new Calculate();
    private String operation = "";
    private boolean previousStep = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            x.setValue(savedInstanceState.getString("value"));
            operation = savedInstanceState.getString("operation");
            previousStep = savedInstanceState.getBoolean("operation");
        }
    }

    public void calculate(View view){
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (textPrevious.getText().toString().contains("=")) {
            String o = textPrevious.getText().toString();
            switch (operation) {
                case "+":
                    textInput.setText(x.add(textInput.getText().toString(), o.substring(o.lastIndexOf("+") + 1, o.length() - 1)));
                    o = o.substring(o.lastIndexOf("+") + 1, o.length() - 1);
                    break;
                case "-":
                    textInput.setText(x.sub(textInput.getText().toString(), o.substring(o.lastIndexOf("-") + 1, o.length() - 1)));
                    o = o.substring(o.lastIndexOf("-") + 1, o.length() - 1);
                    break;
                case "/":
                    textInput.setText(x.div(textInput.getText().toString(), o.substring(o.lastIndexOf("/") + 1, o.length() - 1)));
                    o = o.substring(o.lastIndexOf("/") + 1, o.length() - 1);
                    break;
                case "*":
                    textInput.setText(x.multi(textInput.getText().toString(), o.substring(o.lastIndexOf("*") + 1, o.length() - 1)));
                    o = o.substring(o.lastIndexOf("*") + 1, o.length() - 1);
                    break;
            }
            if (!textPrevious.getText().equals("0="))
                textPrevious.setText(String.format("%s%s%s=", x.getValue(), operation, o));
        } else {
            textPrevious.setText(String.format("%s%s=", textPrevious.getText().toString(), textInput.getText()));
            switch (operation) {
                case "+":
                    textInput.setText(x.add(textInput.getText().toString(), x.getValue()));
                    break;
                case "-":
                    textInput.setText(x.sub(textInput.getText().toString(), x.getValue()));
                    break;
                case "/":
                    textInput.setText(x.div(textInput.getText().toString(), x.getValue()));
                    break;
                case "*":
                    textInput.setText(x.multi(textInput.getText().toString(), x.getValue()));
                    break;
            }
        }

    }

    public void undo(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (!textInput.getText().equals("0")) {
            textInput.setText(textInput.getText().toString().substring(0, textInput.getText().toString().length() - 1));
            if (textInput.getText().equals(""))
                textInput.setText("0");
            previousStep = false;
        }
    }

    public void clear(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText("0");
        if (operation.equals("="))
            textPrevious.setText("");
    }

    public void clearAll(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText("0");
        textPrevious.setText("");
        x.setValue("");
        operation = "";
    }

    public void addition(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (textPrevious.getText().toString().contains("="))
            textPrevious.setText(String.format("%s+", x.getValue()));
        else if (!previousStep || !operation.equals("+")) {
            textPrevious.setText(String.format("%s%s+", textPrevious.getText().toString(), textInput.getText()));
            if (x.getValue().isEmpty())
                x.setValue(textInput.getText().toString());
            else if (!textPrevious.getText().toString().endsWith("+"))
                textPrevious.setText(String.format("%s+", textPrevious.getText().toString().substring(0, textPrevious.length() - 1)));
            else {
                switch (operation) {
                    case "+":
                        textInput.setText(x.add(textInput.getText().toString(), x.getValue()));
                        break;
                    case "-":
                        textInput.setText(x.sub(textInput.getText().toString(), x.getValue()));
                        break;
                    case "/":
                        textInput.setText(x.div(textInput.getText().toString(), x.getValue()));
                        break;
                    case "*":
                        textInput.setText(x.multi(textInput.getText().toString(), x.getValue()));
                        break;
                }
            }
        }
        operation = "+";
        previousStep = true;
    }

    public void subtraction(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (textPrevious.getText().toString().contains("="))
            textPrevious.setText(String.format("%s-", x.getValue()));
        else if (!previousStep || !operation.equals("-")) {
            textPrevious.setText(String.format("%s%s-", textPrevious.getText().toString(), textInput.getText()));
            if (x.getValue().isEmpty())
                x.setValue(textInput.getText().toString());
            else if (!textPrevious.getText().toString().endsWith("-"))
                textPrevious.setText(String.format("%s-", textPrevious.getText().toString().substring(0, textPrevious.length() - 1)));
            else {
                switch (operation) {
                    case "+":
                        textInput.setText(x.add(textInput.getText().toString(), x.getValue()));
                        break;
                    case "-":
                        textInput.setText(x.sub(textInput.getText().toString(), x.getValue()));
                        break;
                    case "/":
                        textInput.setText(x.div(textInput.getText().toString(), x.getValue()));
                        break;
                    case "*":
                        textInput.setText(x.multi(textInput.getText().toString(), x.getValue()));
                        break;
                }
            }
        }
        operation = "-";
        previousStep = true;
    }

    public void division(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (textPrevious.getText().toString().contains("="))
            textPrevious.setText(String.format("%s/", x.getValue()));
        else if (!previousStep || !operation.equals("/")) {
            textPrevious.setText(String.format("%s%s/", textPrevious.getText().toString(), textInput.getText()));
            if (x.getValue().isEmpty())
                x.setValue(textInput.getText().toString());
            else if (!textPrevious.getText().toString().endsWith("/"))
                textPrevious.setText(String.format("%s/", textPrevious.getText().toString().substring(0, textPrevious.length() - 1)));
            else {
                switch (operation) {
                    case "+":
                        textInput.setText(x.add(textInput.getText().toString(), x.getValue()));
                        break;
                    case "-":
                        textInput.setText(x.sub(textInput.getText().toString(), x.getValue()));
                        break;
                    case "/":
                        textInput.setText(x.div(textInput.getText().toString(), x.getValue()));
                        break;
                    case "*":
                        textInput.setText(x.multi(textInput.getText().toString(), x.getValue()));
                        break;
                }
            }
        }
        operation = "/";
        previousStep = true;
    }

    public void multiplication(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (textPrevious.getText().toString().contains("="))
            textPrevious.setText(String.format("%s*", x.getValue()));
        else if (!previousStep || !operation.equals("*")) {
            textPrevious.setText(String.format("%s%s*", textPrevious.getText().toString(), textInput.getText()));
            if (x.getValue().isEmpty())
                x.setValue(textInput.getText().toString());
            else if (!textPrevious.getText().toString().endsWith("*"))
                textPrevious.setText(String.format("%s*", textPrevious.getText().toString().substring(0, textPrevious.length() - 1)));
            else {
                switch (operation) {
                    case "+":
                        textInput.setText(x.add(textInput.getText().toString(), x.getValue()));
                        break;
                    case "-":
                        textInput.setText(x.sub(textInput.getText().toString(), x.getValue()));
                        break;
                    case "/":
                        textInput.setText(x.div(textInput.getText().toString(), x.getValue()));
                        break;
                    case "*":
                        textInput.setText(x.multi(textInput.getText().toString(), x.getValue()));
                        break;
                }
            }
        }
        operation = "*";
        previousStep = true;
    }

    public void percent(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.pc(textInput.getText().toString()));
        previousStep = true;
        textPrevious.setText(textInput.getText());
    }

    public void reciprocal(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.reverse(textInput.getText().toString()));
        previousStep = false;
    }

    public void toSquare(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.pow(textInput.getText().toString()));
        previousStep = false;
    }

    public void extractRoot(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.sqrt(textInput.getText().toString()));
        previousStep = false;
    }

    public void changeOfSign(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.sign(textInput.getText().toString()));
        previousStep = false;
    }

    public void zero(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (previousStep) {
            textInput.setText("0");
            previousStep = false;
        } else if (textPrevious.getText().toString().endsWith("=")) {
            textInput.setText("0");
            textPrevious.setText("");
            previousStep = false;
        } else if (!textInput.getText().equals("0")) {
            textInput.setText(String.format("%s0", textInput.getText()));
            previousStep = false;
        }
    }

    public void write(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);
        Button btn = (Button) findViewById(view.getId());

        if (textPrevious.getText().toString().endsWith("=")) {
            textInput.setText("");
            textPrevious.setText("");
        }

        switch ( btn.getText().toString()) {
            case "1":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("1");
                else
                    textInput.setText(String.format("%s1", textInput.getText()));

                break;
            case "2":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("2");
                else
                    textInput.setText(String.format("%s2", textInput.getText()));

                break;
            case "3":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("3");
                else
                    textInput.setText(String.format("%s3", textInput.getText()));

                break;
            case "4":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("4");
                else
                    textInput.setText(String.format("%s4", textInput.getText()));

                break;
            case "5":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("5");
                else
                    textInput.setText(String.format("%s5", textInput.getText()));

                break;
            case "6":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("6");
                else
                    textInput.setText(String.format("%s6", textInput.getText()));

                break;
            case "7":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("7");
                else
                    textInput.setText(String.format("%s7", textInput.getText()));

                break;
            case "8":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("8");
                else
                    textInput.setText(String.format("%s8", textInput.getText()));

                break;
            case "9":
                if (textInput.getText().equals("0") || previousStep)
                    textInput.setText("9");
                else
                    textInput.setText(String.format("%s9", textInput.getText()));

                break;
            case ".":
                if (!textInput.getText().toString().contains(".")) {
                    if (textInput.getText().toString().endsWith(operation) || previousStep)
                        textInput.setText("0.");
                    else
                        textInput.setText(String.format("%s.", textInput.getText()));
                }
                break;

        }

        previousStep = false;
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", x.getValue());
        outState.putString("operation", operation);
        outState.putBoolean("previousStep", previousStep);
    }
}