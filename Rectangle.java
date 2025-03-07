

import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 * Klasa prostokata utworzona od {@link Figure}
 */
public class Rectangle extends Figure{
    /**
     * Szerokosć
     */
    private double width;
    /**
     * Dlugość
     */
    private double height;
    /**
     * Figura jest prostokątem
     */
    private javafx.scene.shape.Rectangle figure;

    /**
     * Kpnstruktor glówny dla prostokąta
     * @param x lewy górny róg po x
     * @param y lewy górny róg po y
     * @param initialColor kolor
     * @param width szerokość
     * @param height długość
     */
    public Rectangle(double x, double y, Color initialColor, double width, double height) {
        this.x = x;
        this.y = y;
        this.color = initialColor;
        this.width = width;
        this.height = height;
        figure = new javafx.scene.shape.Rectangle(x, y,width,height);
        figure.setFill(initialColor);
    }


    /**
     * Kpnstruktor dla prostokąta o czerwonym kolorze
     * @param x lewy górny róg po x
     * @param y lewy górny róg po y
     * @param width szerokość
     * @param height długość
     */
    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.color = Color.RED;
        this.width = width;
        this.height = height;
        figure = new javafx.scene.shape.Rectangle(x, y, width,height);
        figure.setFill(this.color);
    }

    /**
     * Konstruktor prostokąta
     * @param params parametry zapisane do tabelki typu String
     */
    public Rectangle(String[] params) {
        this.x = Double.parseDouble(params[0]);
        this.y = Double.parseDouble(params[1]);
        this.width = Double.parseDouble(params[2]);
        this.height = Double.parseDouble(params[3]);
        this.color = Color.valueOf(params[4]);
        figure = new javafx.scene.shape.Rectangle(this.x, this.y, this.width,this.height);
        figure.setFill(this.color);
    }

    /**
     * Sprawdza czy zawiera prostokąt punkt
     * @param x punkt x
     * @param y punkt y
     * @return czy zawiera prostokąt punkt
     */
    @Override
    public boolean containsPoint(double x, double y) {
        double minX = Math.min(this.x, this.x + width);
        double maxX = Math.max(this.x, this.x + width);
        double minY = Math.min(this.y, this.y + height);
        double maxY = Math.max(this.y, this.y + height);

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    /**
     * Parametry zapisane w formacie String
     * @return parametry typu String
     */
    @Override
    public String signature() {
        return x + " " + y + " " + width + " " + height +" "+ color;
    }

    /**
     * Sciąga polożenie lewego górnego rogu po x
     * @return polożenie lewego górnego rogu po x
     */
    @Override
    public double gettX() {
        return this.x;
    }
    /**
     * Sciąga polożenie lewego górnego rogu po y
     * @return polożenie lewego górnego rogu po y
     */
    @Override
    public double gettY() {
        return this.y;
    }
    /**
     * Sciąga prostokąt
     * @return Prostokąt który został utworzony
     */
    @Override
    public javafx.scene.shape.Rectangle getFigure() {return figure;}
    /**
     * Ściąga kolor prostokąta
     * @return Kolor prostokąta
     */
    @Override
    public Color getColor() {
        return color;
    }
    /**
     * Przepisuje kolor prostokatu
     * @param color Przepisywany kolor
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
        this.figure.setFill(color);

    }
    /**
     * Ustawia polożenie prostokąta po przysunięciu po X
     * @param deltaX Odłeglość na którą przesuniemy figurę po x
     */
    @Override
    public void settX(double deltaX) {
        this.x = this.x + deltaX;
        this.figure.setX(x + deltaX);

    }

    /**
     * Ustawienie X
     * @param deltaY Odłeglość na którą przesuniemy figurę po y
     */
    @Override
    public void settY(double deltaY) {
        this.y = this.y + deltaY ;
        this.figure.setY(y + deltaY);

    }

    /**
     * Zmiana rozmiaru prostokąta
     * @param deltay Na jaką wielkość zmieniamy rozmiar
     */
    @Override
    public void scale(double deltay) {
        this.width = width + deltay;
        this.height = height + deltay;
        this.figure.setWidth(width + deltay);
        this.figure.setHeight(height+deltay);
    }

    /**
     * Obracanie prostokąta
     */
    @Override
    public void setTransformation() {
        Rotate rotate = new Rotate();
        rotate.setAngle(10);
        rotate.setPivotX(this.gettX());
        rotate.setPivotY(this.gettY());
        figure.getTransforms().addAll(rotate);
        figure.getTransforms().addAll(rotate);











    }
}
