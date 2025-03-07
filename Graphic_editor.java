



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa główna aplikacji graficznego edytora.
 * @author Hanna Ramanouskaya
 * @version 1.0
 */
public class Graphic_editor extends Application {
    /**
     * enum Shape_Status pszechowyje typy figur które możemy utworzyć
     */
    public enum Shape_Status {
        /**
         * Kolo
         */
        CIRCLE,
        /**
         * com.example.demo11.Rectangle
         */
        RECTANGLE,
        /**
         * Polygon
         */
        POLYGON
    }
    /**
     * Typ figury
     */
    public Shape_Status shape;
    /**
    /**
     * List wszystkich figur
     */
    private List<Figure> allFigures = new ArrayList<>();
    /**
     * ArrayList zawiera punkty wielokąta
     */
    public ArrayList<Double> points = new ArrayList<>();
    /**
    /**
     * Figura która została utworzona
     */
    private Figure currFigure;
    /**
     * Figura która została wybrana
     */
    private Figure selectedFigure = null;
    /**
     * Nowe polożenie objekta po zdarzeniu po X
     */
    private double prevMouseX;
    /**
     * Nowe polożenie objekta po zdarzeniu po Y
     */
    private double prevMouseY;

    /**
     * Status możliwości współdziałać z figurami
     */
    private boolean active = false;
    public MenuItem rotate_item = new MenuItem("Rotate");
    /**
     * Menu z ColorPicker
     */
    public MenuItem colour_item = new MenuItem("Change color");
    /**
     * ContextMenu które otwiera się po przycisku lewem przyciskiem, zawiera funkcje zmiany koloru i rotate
     */
    public ContextMenu contextMenu = new ContextMenu(rotate_item, colour_item);
    /**
     * ColorPicker do zmiany koloru
     */
    public ColorPicker colorPicker = new ColorPicker();
    /**
     * Figyra która w tym czasie jest aktywna
     */
    public Shape myshape;
    /**
     * Stage główny
     */
    Stage primaryStage;
    /**
     * Pane glówny
     */
    Pane root = new Pane();
    MenuBar menuBar = new MenuBar();
    /**
     * Wszystko co dotyczy pliku
     */
    Menu file = new Menu("File");
    /**
     * Do zapisywania pliku
     */
    MenuItem save_menu = new MenuItem("Save");
    /**
     * Do otwierania pliku
     */
    MenuItem open_menu = new MenuItem("Open");
    /**
     * Do uzyskania informacji
     */
    MenuItem info_menu = new MenuItem("Information");
    /**
     * Do wybierania figury
     */
    Menu shape_menu = new Menu("Shape");
    /***
     * Wybrany jest prostokąt
     */
    MenuItem rectangle = new MenuItem("Rectangle");
    /**
     * Wybrany jest polygon
     */
    MenuItem polygon = new MenuItem("Polygon");
    /**
     * Wybrano jest koło
     */
    MenuItem circle = new MenuItem("Circle");
    /**
     * Przycisk dla aktywacji figur
     */
    Button activate_button = new Button("Activate");
    /**
     * Przecisk dla skończenia utworzenia poligonu
     */
    Button poligon_finish = new Button("Finish");
    /**
     * Pole do rysowania
     */
    Pane drawArea = new Pane();






    /**
     * Metoda start() uruchamia graficzny edytor,tworzy scenę i widok główny
     * @param primaryStage Główny kontener dla sceny
     * @throws IOException Wyjątek występujący w przypadku problemów z załadowaniem pliku fxml
     */
    @Override    public void start(Stage primaryStage) throws IOException {
        root.setPrefHeight(400);
        root.setPrefWidth(600);
        menuBar.setLayoutY(2.0);
        menuBar.setViewOrder(Double.NEGATIVE_INFINITY);
        save_menu.setOnAction(e -> save());
        open_menu.setOnAction(e-> open());
        info_menu.setOnAction(e-> information());
        file.getItems().addAll(save_menu,open_menu,info_menu);
        shape_menu.setOnAction(e-> Shape_Status());
        shape_menu.getItems().addAll(rectangle,polygon,circle);
        menuBar.getMenus().addAll(file,shape_menu);
        activate_button.setLayoutX(534.0);
        activate_button.setLayoutY(2.0);
        activate_button.setOnAction(e -> setActive());
        poligon_finish.setLayoutX(534.0);
        poligon_finish.setLayoutY(40);
        poligon_finish.setOnAction(e -> setPolygon_finish());
        drawArea.setMinHeight(400);
        drawArea.setMinWidth(600);
        drawArea.setOnMouseClicked(e -> handleMousePressed(e));
        drawArea.setOnMouseDragged(e -> handleMouseDragged(e));
        drawArea.setOnScroll(e -> handleMouseScroll(e));
        root.getChildren().addAll(drawArea,menuBar,activate_button,poligon_finish);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graphic editor");
        primaryStage.show();
    }



    /**
     * Metoda Shape_Status sprawdza jaka figura została wybrana z menu
     * @return Jest zwracany typ figury
     */
    public Shape_Status Shape_Status() {
        active = false;
        resetActive();
        circle.setOnAction(e -> shape = Shape_Status.CIRCLE);
        rectangle.setOnAction(e -> shape = Shape_Status.RECTANGLE);
        polygon.setOnAction(e -> shape = Shape_Status.POLYGON);


        return shape;
    }

    /**
     * Metoda obsluguje drawArea kiedy klikamy na nią możemy stworzyć kolo,prostokąt czy wielokąt w tym miejscy w którym kliknęli
     *
     * @param event MouseEvent dla metody handleMousePressed
     */
    void handleMousePressed(MouseEvent event) {

        if (!active && event.getButton() == MouseButton.PRIMARY) {

            double x = event.getX();
            double y = event.getY();
            switch (shape) {
                case CIRCLE -> {
                    currFigure = new Circle(x, y, 50);
                    Shape shape = currFigure.getFigure();
                    drawArea.getChildren().addAll(shape);
                    allFigures.add(currFigure);
                }
                case RECTANGLE -> {
                    currFigure = new Rectangle(x, y, 100, 50);
                    Shape shape = currFigure.getFigure();
                    drawArea.getChildren().addAll(shape);
                    allFigures.add(currFigure);
                }
                case POLYGON -> {
                    points.add(x);
                    points.add(y);
                }
                default -> System.out.println("error");
            }

            return;
        }

        if (selectedFigure != null) resetActive();
        double x = event.getX();
        double y = event.getY();
        for (Figure figure : allFigures) {
            if (figure.containsPoint(x, y)) {
                selectedFigure = figure;
                prevMouseX = event.getX();
                prevMouseY = event.getY();
            }
        }

        if (event.getButton() == MouseButton.PRIMARY) {
            selectedFigure.getFigure().setStroke(Color.BLACK);
        }

        if (event.getButton() == MouseButton.SECONDARY && active) {
            contextMenu.show(drawArea, event.getScreenX(), event.getScreenY());
            colour_item.setOnAction((e1) -> {
                colorPicker.setLayoutX(selectedFigure.gettX());
                colorPicker.setLayoutY(selectedFigure.gettY());
                drawArea.getChildren().add(colorPicker);
                colorPicker.setOnAction((e2) -> {
                    selectedFigure.setColor(colorPicker.getValue());
                    drawArea.getChildren().remove(colorPicker);
                });
            });
        }
        rotate_item.setOnAction(rotatemouseEvent -> {
            selectedFigure.setTransformation();
        });
    }


    /**
     * Metoda information wywołuje okno diałogowe na którym znajduje się informacja do wykorzystania aplikacji
     */
    void information() {
        Stage dialog = new Stage();
        dialog.setTitle("Information");
        Label label = new Label("""
                HELLO IN GRAPHIC REDACTOR!!!
                 Athor: Hanna Ramanouskaya
                If you click on the "Shape" menu, you can choose one of the shapes to create: "Polygon", "com.example.demo11.Rectangle", "com.example.demo11.Circle".
                To create a circle or rectangle, simply select them in the menu and click on the canvas,
                to create a polygon click "Polygon" menuitem and click on the points on the drawArea
                if you want to complete its creation, click "Finish".
                To start interacting with the shapes, click the "Activate" button.
                Now you can move the shapes by clicking on the right mouse button.
                You can use the scroll to change the size of the selected shape.
                 If you click on the left mouse button in the area of the selected shape,
                you can change its color by clicking on "Change color"by clicking on "Rotate" you can wrap the shape by 10 degrees
                .You can also save and open the file by clicking on the "Save" and "Open" buttons, respectively
                """);
        Font font = new Font(10);
        label.setFont(font);
        label.setTextAlignment(TextAlignment.CENTER);

        Button okbutton = new Button("OK");
        okbutton.setOnAction(e -> dialog.close());
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label, okbutton);
        vBox.setAlignment(Pos.CENTER);
        Scene dialogscene = new Scene(vBox, 600, 400);
        dialog.setScene(dialogscene);
        dialog.showAndWait();

    }

    /**
     * Przenoszenie figury
     *
     * @param event MouseEvent dla metody handleMouseDragged
     */
    void handleMouseDragged(MouseEvent event) {
        double deltaX = event.getX() - prevMouseX;
        double deltaY = event.getY() - prevMouseY;
        if (selectedFigure != null) {
            selectedFigure.settX(deltaX);
            selectedFigure.settY(deltaY);
        }
        prevMouseX = event.getX();
        prevMouseY = event.getY();
    }

    /**
     * Zmienia rozmiar figury
     *
     * @param event ScrollEvent dla metody handleMouseScroll
     */
    void handleMouseScroll(ScrollEvent event) {

        if (selectedFigure != null) {
            double deltaY = event.getDeltaY();
            selectedFigure.scale(deltaY);
        }

    }

    /**
     * Metoda save() otwiera okno dialogowe, w którym użytkownik może wybrać nazwę i lokalizację pliku do zapisania
     */

    void save() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
            );
            File fileName = fileChooser.showSaveDialog(primaryStage);
            PrintWriter writer = new PrintWriter((new FileWriter(fileName)));
            allFigures.forEach((figure) -> {
                writer.println(figure.signature());
            });
            writer.close();
        } catch (Exception e) {
        }

    }

    /**
     * Metoda open() otwiera okno dialogowe wyboru pliku i umożliwia użytkownikowi wybór pliku do otwarcia
     */

    void open() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TXTfiles (*.txt)", "*.txt")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                drawArea.getChildren().clear();
                String signature;
                while ((signature = reader.readLine()) != null) {
//rozdzielenie parametrów i zapisywanie ich do tablicy
                    String[] params = Figure.paramsParser(signature);

                    if (params.length == 4) {
                        System.out.println("create circle");
                        var circle = new Circle(params);
                        myshape = circle.getFigure();
                        drawArea.getChildren().addAll(myshape);
                        allFigures.add(circle);
                    } else if (params.length == 5) {
                        System.out.println("create rectangle");
                        var rectangle = new Rectangle(params);
                        myshape = rectangle.getFigure();
                        drawArea.getChildren().addAll(myshape);
                        allFigures.add(rectangle);
                    } else if (params.length > 5) {
                        System.out.println("create polygon");
                        var polygon = new MyPolygon(params);
                        myshape = polygon.getFigure();
                        drawArea.getChildren().addAll(myshape);
                        allFigures.add(polygon);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }

    /**
     * Możemy aktywować status możliwości współdziałania z figurami
     */
    public void setActive() {
        active = true;
    }

    /**
     * Kończymy tworzenie wielokąta
     */
    public void setPolygon_finish() {
        ArrayList<Double> pointsCopy = new ArrayList<>(points);
        currFigure = new MyPolygon(pointsCopy);
        Shape shape = currFigure.getFigure();
        drawArea.getChildren().addAll(shape);
        allFigures.add(currFigure);
        points.clear();
    }

    /**
     * Możemy skończyć aktywność statusu możliwości współdziałania z figurami
     */
    private void resetActive() {
        if (selectedFigure != null)
            selectedFigure.getFigure().setStroke(selectedFigure.color);
        selectedFigure = null;
    }

    /**
     * Metoda main() uruchamia aplikację, wywołując metodę launch()
     * @param args Argumenty wywołania programu
     */
    public static void main(String[] args) {launch();
    }}
