package edu.ib.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * klasa zarzadzająca interfejsem graficznym aplikacji wyświetlającej kalkulator
 */
public class MainActivity extends AppCompatActivity {

    Calculate x = new Calculate();
    private String operation = "";
    private boolean previousStep = true;

    /**
     * metoda pozwalajaca odtworzyc dzialanie aplikacji, np. po zmianie ulozenia ekranu
     *
     * @param savedInstanceState zachowanie ostatnich danych aplikacji
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            x.setValue(savedInstanceState.getString("value"));
            operation = savedInstanceState.getString("operation");
            previousStep = savedInstanceState.getBoolean("operation");
        }
    }

    /**
     * metoda zmieniajaca widoczny tekst po kliknieciu "="
     * dodaje wybrana ostatnio liczbe oraz  "=" do zapisanego rownania
     * oraz powoduje wykonanie wybranego wcześniej działania
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void calculate(View view) {
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

    /**
     * metoda zmieniajaca widoczny teskt po kliknięciu "<--"
     * usuwa ostatnia widoczna cyfre jesli wynikiem operacji nie jest tylko 0
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void undo(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        if (!textInput.getText().equals("0")) {
            textInput.setText(textInput.getText().toString().substring(0, textInput.getText().toString().length() - 1));
            if (textInput.getText().equals(""))
                textInput.setText("0");
            previousStep = false;
        }
    }

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "CE"
     * usuwa ostatni wynik dzialan
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void clear(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText("0");
        if (operation.equals("="))
            textPrevious.setText("");
    }

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "C"
     * resetuje dotychczasowe operacje i ich wyniki
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void clearAll(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText("0");
        textPrevious.setText("");
        x.setValue("");
        operation = "";
    }

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "+"
     * dodaje wybrana ostatnio liczbe oraz  "+" do zapisanego rownania
     * oraz wykonuje działanie dodawania
     *
     * @param view przenosi informacje o wywołaniu metody
     */
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

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "-"
     * dodaje wybrana ostatnio liczbe oraz  "-" do zapisanego rownania
     * oraz wykonuje działanie odejmowania
     *
     * @param view przenosi informacje o wywołaniu metody
     */
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

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "/"
     * dodaje wybrana ostatnio liczbe oraz  "-" do zapisanego rownania
     * oraz wykonuje działanie dzielenia
     *
     * @param view przenosi informacje o wywołaniu metody
     */
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

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "*"
     * dodaje wybrana ostatnio liczbe oraz  "*" do zapisanego rownania
     * oraz wykonuje działanie mnożenia
     *
     * @param view przenosi informacje o wywołaniu metody
     */
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

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "%"
     * zamienia wybrana ostatnio liczbę na procent tej liczby (dzieli liczbę przez 100)
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void percent(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.pc(textInput.getText().toString()));
        previousStep = true;
        textPrevious.setText(textInput.getText());
    }


    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "1/x"
     * zamienia wybrana ostatnio liczbę odwrotnosc tej liczby
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void reciprocal(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.reverse(textInput.getText().toString()));
        previousStep = false;
    }

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "x^2"
     * zamienia wybrana ostatnio liczbę na druga potege tej liczby
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void toSquare(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.pow(textInput.getText().toString()));
        previousStep = false;
    }

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "sqrt(x)"
     * zamienia wybrana ostatnio liczbę na pierwiastek drugiego stopnia tej liczby
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void extractRoot(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.sqrt(textInput.getText().toString()));
        previousStep = false;
    }

    /**
     * metoda zmieniajaca widoczny teskt po kliknieciu "+/-"
     * zamienia znak wybranej licbzy na przeciwny
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void changeOfSign(View view) {
        TextView textInput = (TextView) findViewById(R.id.txtInput);

        textInput.setText(x.sign(textInput.getText().toString()));
        previousStep = false;
    }

    /**
     * dopisuje "0" do wpisywanej liczby lub zamienia wynik na "0"
     *
     * @param view przenosi informacje o wywołaniu metody
     */
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

    /**
     * dopisuje cyfrę do wpisywanej liczby lub zamienia wynik na wybraną cyfrę
     *
     * @param view przenosi informacje o wywołaniu metody
     */
    public void write(View view) {
        TextView textPrevious = (TextView) findViewById(R.id.txtPrevious);
        TextView textInput = (TextView) findViewById(R.id.txtInput);
        Button btn = (Button) findViewById(view.getId());

        if (textPrevious.getText().toString().endsWith("=")) {
            textInput.setText("");
            textPrevious.setText("");
        }

        switch (btn.getText().toString()) {
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

    /**
     * metoda pozwalajaca zapisac stan aplikacji
     *
     * @param outState zachowanie ostatnich danych aplikacji
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", x.getValue());
        outState.putString("operation", operation);
        outState.putBoolean("previousStep", previousStep);
    }
}