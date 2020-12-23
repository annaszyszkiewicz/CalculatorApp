package edu.ib.calculator;

/**
 * klasa wykonujaca obliczenia
 */
public class Calculate {

    private String value = "";

    /**
     * metoda zwracajaca wynik dodawania
     *
     * @param number         wybrana liczba, ktora dodajemy
     * @param previousNumber wynik poprzednich operacji, liczba do ktorej dodajemy
     * @return wynik dodawania
     */
    public String add(String number, String previousNumber) {
        String result = String.valueOf(Double.parseDouble(previousNumber) + Double.parseDouble(number));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca wynik odejmowania
     *
     * @param number         wybrana liczba, ktora odejmujemy
     * @param previousNumber wynik poprzednich operacji, liczba od ktorej odejmujemy
     * @return wynik odejmowania
     */
    public String sub(String number, String previousNumber) {
        String result = String.valueOf(Double.parseDouble(previousNumber) - Double.parseDouble(number));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca wynik dzielenia
     *
     * @param number         wybrana liczba, przez ktora dzielimy
     * @param previousNumber wynik poprzednich operacji, liczba ktora dzielimy
     * @return wynik dzielenia
     */
    public String div(String number, String previousNumber) {
        String result = String.valueOf(Double.parseDouble(previousNumber) / Double.parseDouble(number));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca wynik mnozenia
     *
     * @param number         wybrana liczba przez ktora mnozymy
     * @param previousNumber wynik poprzednich operacji, liczba ktora mnozymy
     * @return wynik mnozenia
     */
    public String multi(String number, String previousNumber) {
        String result = String.valueOf(Double.parseDouble(number) * Double.parseDouble(previousNumber));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca procent z liczby
     *
     * @param number wybrana liczba, ktora chcemy podzielic przez 100
     * @return procent z liczby
     */
    public String pc(String number) {
        String result = String.valueOf(Double.parseDouble(number) / 100);
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaco odwrotnosc liczby
     *
     * @param number wybrana liczba, ktorej chcemy odwrotnosc
     * @return odwrotnosc liczby
     */
    public String reverse(String number) {
        String result = String.valueOf(1 / Double.parseDouble(number));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca druga potege liczby
     *
     * @param number wybrana liczba, ktora chemy podnisc do kwadratu
     * @return druga potega liczby
     */
    public String pow(String number) {
        String result = String.valueOf(Double.parseDouble(number) * Double.parseDouble(number));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca pierwiastek drugiego stopnia z liczby
     *
     * @param number wybrana liczba, ktora chcemy spierwiastkowac
     * @return pierwiastek drugiego stopnia z liczby
     */
    public String sqrt(String number) {
        String result = String.valueOf(Math.sqrt(Double.parseDouble(number)));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca wybrana liczbe ze zmienionym znakiem
     *
     * @param number wybrana liczba, ktorej chcemy zmienic znak
     * @return liczba ze zmienionym znakiem
     */
    public String sign(String number) {
        String result = String.valueOf(Double.parseDouble(number) - 2 * Double.parseDouble(number));
        if (result.endsWith(".0"))
            result = result.substring(0, result.length() - 2);
        value = result;
        return result;
    }

    /**
     * metoda zwracajaca wynik po ostatniej operacji
     *
     * @return wartosc po ostatniej operacji
     */
    public String getValue() {
        return value;
    }

    /**
     * metoda ustawiajaca zmiane wyniku
     *
     * @param value wynik po ostatniej operacji
     */
    public void setValue(String value) {
        this.value = value;
    }
}
