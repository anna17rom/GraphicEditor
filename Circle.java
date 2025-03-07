


import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
/**
 * Klasa koła utworzona od {@link Figure}
 */
public class Circle extends Figure {
    /**
     * Radius
     */
    private double radius;
    /**
     * Figura jest kołem
     */
    private javafx.scene.shape.Circle figure;
    /**
     * Kpnstruktor glówny dla Koła
     * @param x środek po x
     * @param y środek po y
     * @param initialColor kolor
     * @param radius radius
     */
    public Circle(double x, double y, Color initialColor, double radius) {
        this.x = x;
        this.y = y;
        this.color = initialColor;
        this.radius = radius;
        figure = new javafx.scene.shape.Circle(x, y, radius);
        figure.setFill(initialColor);
    }
    /**
     * Kpnstruktor dla koła o czerwonym kolorze
     * @param x środek po x
     * @param y środek po y
     * @param radius radius
     */
    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.color = Color.RED;
        this.radius = radius;
        figure = new javafx.scene.shape.Circle(x, y, radius);
        figure.setFill(this.color);
    }
    /**
     * Konstruktor koła
     * @param params parametry zapisane do tabelki typu String
     */
    public Circle(String[] params) {
        this.x = Double.parseDouble(params[0]);
        this.y = Double.parseDouble(params[1]);
        this.radius = Double.parseDouble(params[2]);
        this.color = Color.valueOf(params[3]);
        figure = new javafx.scene.shape.Circle(this.x, this.y, this.radius);
        figure.setFill(this.color);
    }

    /**
     * Sprawdza czy zawiera koło punkt
     * @param x punkt x
     * @param y punkt y
     * @return czy zawiera koło punkt
     */
    @Override
    public boolean containsPoint(double x, double y) {
        double dx = x - this.x;
        double dy = y - this.y;
        return dx * dx + dy * dy <= radius * radius;
    }
    /**
     * Parametry zapisane w formacie String
     * @return parametry typu String
     */
    @Override
    public String signature() {
        return x + " " + y + " " + radius + " " + color;
    }
    /**
     * Sciąga polożenie środku po x
     * @return polożenie środku po x
     */
    @Override
    public double gettX() {
        return x;
    }

    /**
     * Sciąga polożenie środku po y
     * @return polożenie środku po y
     */
    @Override
    public double gettY() {
        return y;
    }
    /**
     * Sciąga koło
     * @return koło które zostało utworzone
     */
    @Override
    public javafx.scene.shape.Circle getFigure() {
        return figure;
    }
    /**
     * Ściąga kolor koła
     * @return Kolor koła
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Przepisuje kolor do koła
     * @param color Przepisywany kolor
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
        this.figure.setFill(color);
    }
    /**
     * Ustawia środek koła po x
     * @param deltaX Odłeglość na którą przesuniemy figurę po x
     */
    @Override
    public void settX(double deltaX) {
        this.x = x+deltaX;
        this.figure.setCenterX(x+deltaX);
    }

    /**
     * Ustawia środek koła po y
     * @param deltaY Odłeglość na którą przesuniemy figurę po y
     */
    @Override
    public void settY(double deltaY) {
        this.y = y+deltaY;
        this.figure.setCenterY(y+deltaY);
    }

    /**
     * Zmiana rozmiaru koła
     * @param deltay Na jaką wielkość zmieniamy rozmiar
     */
    @Override
    public void scale(double deltay) {
        this.radius = radius + deltay;
        this.figure.setRadius(radius+deltay);
    }

    /**
     * Obracanie koła
     */
    @Override
    public void setTransformation() {
        Rotate rotate = new Rotate();
        rotate.setAngle(10);
        rotate.setPivotX(figure.getCenterX());
        rotate.setPivotY(figure.getCenterY());
        this.figure.getTransforms().addAll(rotate);
    }

}
