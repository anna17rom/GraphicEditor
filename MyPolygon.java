


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;


/**
 * Klasa wielokąta utworzona od {@link Figure}
 */
public class MyPolygon extends Figure {

    /**
     * Punkty wielokąta
     */
    private ArrayList<Double> points;
    /**
     * Figura jest wielokątem
     */
    private javafx.scene.shape.Polygon figure;

    /**
     * Konstruktor główny dla wielokąta
     *
     * @param points punkty wielokąta
     * @param initialColor kolor
     */
    public MyPolygon(ArrayList<Double> points, Color initialColor) {
        this.points = points;
        this.color = initialColor;
        figure = new javafx.scene.shape.Polygon();
        setPolygonPoints(figure);
        this.x = points.get(0);
        this.y = points.get(1);

        figure.setFill(initialColor);
    }

    /**
     * Konstruktor dla wielokata o czerwonym kolorze
     *
     * @param points punkty wielokąta
     */
    public MyPolygon(ArrayList<Double> points) {
        this.points = points;
        this.color = Color.RED;
        figure = new javafx.scene.shape.Polygon();
        setPolygonPoints(figure);
        this.x = points.get(0);
        this.y = points.get(1);
        figure.setFill(this.color);
    }

    /**
     * Konstruktor wielokąta
     *
     * @param params parametry zapisane do tabelki typu String
     */
    public MyPolygon(String[] params) {
        this.points = new ArrayList<>();
        for (int i = 0; i < params.length - 1; i++) {
            points.add(Double.parseDouble(params[i]));
        }
        this.color = Color.valueOf(params[params.length - 1]);
        figure = new javafx.scene.shape.Polygon();
        setPolygonPoints(figure);
        this.x = points.get(0);
        this.y = points.get(1);
        figure.setFill(this.color);
    }

    /**
     * Ustawienie punktów wielokąta
     *
     * @param figure wielokąt
     */
    private void setPolygonPoints(Polygon figure) {
        this.figure = figure;
        figure.getPoints().addAll(points);
    }

    /**
     * Sprawdza czy zawiera wielokąt punkt
     *
     * @param x punkt x
     * @param y punkt y
     * @return czy zawiera wielokąt punkt
     */
    @Override
    public boolean containsPoint(double x, double y) {

        int pointsCount = points.size() / 2;
        boolean isInside = false;

        int j = pointsCount - 1;
        for (int i = 0; i < pointsCount; i++) {
            double X1 = points.get(2 * i);
            double Y1 = points.get(2 * i + 1);
            double X2 = points.get(2 * j);
            double Y2 = points.get(2 * j + 1);

            if ((Y1 < y && Y2 >= y) || (Y2 < y && Y1 >= y)) {
                if (X1 + (y - Y1) / (Y2 - Y1) * (X2 - X1) < x) {
                    isInside = true;
                }
            }

            j = i;
        }

        return isInside;


    }

    /**
     * Parametry zapisane w formacie String
     *
     * @return parametry typu String
     */
    @Override
    public String signature() {
        StringBuilder sb = new StringBuilder();
        for (double point : points) {
            sb.append(point).append(" ");
        }
        sb.append(color);
        return sb.toString();
    }

    /**
     * Sciąga polożenie pierwszego punktu po x
     *
     * @return polożenie lewego pierwszego punktu po x
     */
    @Override
    public double gettX() {
        return this.x;
    }
    /**
     * Sciąga polożenie pierwszego punktu po y
     *
     * @return polożenie lewego pierwszego punktu po y
     */
    @Override
    public double gettY() {
        return this.y;
    }

    /**
     * Sciąga wielokąt
     *
     * @return Wielokąt który został utworzony
     */
    @Override
    public javafx.scene.shape.Polygon getFigure() {
        return figure;
    }

    /**
     * Ściąga kolor wielokąta
     *
     * @return Kolor wielokąta
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Przepisuje kolor do wielokąta
     *
     * @param color Przepisywany kolor
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
        figure.setFill(color);
    }

    /**
     * Ustawia polożenie wielokatu po przysunięciu po X
     *
     * @param deltaX Odłeglość na którą przesuniemy figurę po x
     */
    @Override
    public void settX(double deltaX) {
        for (int i = 0; i < points.size(); i += 2) {
            double newX = points.get(i) + deltaX;
            points.set(i, newX);
        }

    }

    /**
     * Ustawia polożenie wielkata po przysunięciu po Y
     *
     * @param deltaY Odłeglość na którą przesuniemy figurę po y
     */
    @Override
    public void settY(double deltaY) {
        for (int i = 1; i < points.size(); i += 2) {
            double newY = points.get(i) + deltaY;
            points.set(i, newY);
        }

        figure.getPoints().setAll(points);

    }

    /**
     * Zmiana rozmiaru prostokąta
     *
     * @param delta Na jaką wielkość zmieniamy rozmiar
     */
    @Override
    public void scale(double delta) {
        double scaleX = figure.getScaleX() + delta * 0.01;
        double scaleY = figure.getScaleY() + delta * 0.01;
        figure.setScaleX(scaleX);
        figure.setScaleY(scaleY);

    }

    /**
     * Obracanie wielokąta
     */
    @Override
    public void setTransformation() {
        Rotate rotate = new Rotate();
        rotate.setAngle(10);
        rotate.setPivotX(points.get(0));
        rotate.setPivotY(points.get(1));
        figure.getTransforms().addAll(rotate);

    }
}



