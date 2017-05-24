import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShapeSelect extends Application {

    private BorderPane root = new BorderPane();
    private FlowPane bottom = new FlowPane();
    private RadioButton lineButton = new RadioButton();
    private RadioButton circleButton = new RadioButton();
    private RadioButton rectangleButton = new RadioButton();
    private RadioButton fillButton = new RadioButton();
    private ToggleGroup group = new ToggleGroup();
    private Canvas canvas = new Canvas(650, 650);
    private ComboBox<String> colorSelect = new ComboBox<String>();
    private Color color = Color.BLUE;
    private int shape = -1;
    private boolean fill = false;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        colorSelect.getItems().addAll("Blue", "Red", "Green", "Yellow", "Black", "White", "Orange", "Pink");

        colorSelect.setOnAction(event -> {

            switch (colorSelect.getValue()) {
                case "Blue":
                    color = Color.BLUE;
                    break;

                case "Red":
                    color = Color.RED;
                    break;

                case "Green":
                    color = Color.GREEN;
                    break;

                case "Yellow":
                    color = Color.YELLOW;
                    break;

                case "Black":
                    color = Color.BLACK;
                    break;

                case "White":
                    color = Color.WHITE;
                    break;

                case "Orange":
                    color = Color.ORANGE;
                    break;

                case "Pink":
                    color = Color.PINK;
                    break;
            }

            drawShapes(gc, fill, shape, color);
        });

        lineButton.setOnAction(event -> {
            shape = 0;
            drawShapes(gc, fill, shape, color);
        });

        circleButton.setOnAction(event -> {
            shape = 1;
            drawShapes(gc, fill, 1, color);
        });

        rectangleButton.setOnAction(event -> {
            shape = 2;
            drawShapes(gc, fill, shape, color);
        });

        fillButton.setOnAction(event -> {
            if(fillButton.isSelected())
                fill = true;
            else
                fill = false;

            drawShapes(gc, fill, shape, color);
        });

        lineButton.setToggleGroup(group);
        circleButton.setToggleGroup(group);
        rectangleButton.setToggleGroup(group);
        bottom.setAlignment(Pos.CENTER);
        bottom.setHgap(5);
        bottom.getChildren().addAll(new Label("Select a Color"), colorSelect, new Label("Filled"), fillButton, new Label("Line"), lineButton, new Label("Circle"), circleButton, new Label("Rectangle"), rectangleButton);

        root.setBottom(bottom);
        root.setCenter(canvas);
        root.setPrefSize(700, 700);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void drawShapes(GraphicsContext gc, boolean fill, int shape, Color color) {

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(color);
        gc.setStroke(color);

        if (shape == 0) { // draw a line
            gc.setLineWidth(5);
            gc.strokeLine(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() / 2 + 300, canvas.getHeight() / 2 + 300);
        }

        if(!fill && shape == 1)
            gc.strokeOval(canvas.getWidth() / 2, canvas.getHeight() / 2, 200, 200);


        if (fill && shape == 1)
            gc.fillOval(canvas.getWidth() / 2, canvas.getHeight() / 2, 200, 200);


        if(shape == 2 && !fill)
            gc.strokeRect(canvas.getWidth() / 2, canvas.getHeight() / 2, 200, 200);

        if(shape == 2 && fill)
            gc.fillRect(canvas.getWidth() / 2, canvas.getHeight() / 2, 200, 200);

    }


}
