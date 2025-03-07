


import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Klasa abstrakcyjna figur
 */
public abstract class Figure {
    /**
     * Punkt X w którym została stworzona figura
     */
    protected double x;
    /**
     * Punkt Y w którym została stworzona figura
     */
    protected double y;
    /**
     * Figura którą utwolrzyli
     */
    protected Shape figure;

    protected Color color = new Color(0, 0, 0, 0);

    /**
     * Sprawdza czy zawiera figura punkt(x,y)
     * @param x punkt x
     * @param y punkt y
     * @return czy zawiera figura punkt(x,y)
     */
    public abstract boolean containsPoint(double x, double y);

    /**
     * Zapisuje parametry figury
     * @return String z parametrami figury
     */
    public abstract String signature();

    /**
     * Sciąga polożenie figury po X
     * @return Polożenie figury po X
     */
    public abstract double gettX();
    /**
     * Sciąga polożenie figury po Y
     * @return Polożenie figury po Y
     */
    public abstract double gettY();

    /**
     * Sciąga figurę którą utworzyli
     * @return Figura która została utworzona
     */
    public abstract Shape getFigure();

    /**
     * Sciąga kolor figury
     * @return Kolor figury
     */
    public abstract Color getColor();

    /**
     * Przepisuje kolor
     * @param color Przepisywany kolor
     */
     public abstract void setColor(Color color);

    /**
     * Ustawia polożenie po przysunięciu po X
     * @param x Odłeglość na którą przesuniemy figurę po x
     */
     public abstract void settX(double x);
    /**
     * Ustzwia polożenie po przysunięciu po X
     * @param y Odłeglość na którą przesuniemy figurę po y
     */
     public abstract void settY(double y);

    /**
     * Zmiana rozmiaru
     * @param deltay Na jaką wielkość zmieniamy rozmiar
     */
     public abstract  void scale(double deltay);

    /**
     * Obracanie
     */
     public abstract void setTransformation();

    /**
     * podzielenie parametrów
     * @param signature Parametry figury
     * @return Tabelka typu String która zawiera parametry
     */
    public static String[] paramsParser(String signature){
     return signature.split(" ");
    }

}
