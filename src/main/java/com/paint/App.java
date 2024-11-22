package com.paint;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

    /**
     * JavaFX App
     *
     * Sherif Ashraf Ali Shawashen
     * CSC 210
     * fall Semester
     *
     */
    public class App extends Application {

        private boolean isErasing = false;
        private static Scene scene;

        public static void main(String[] args) {
            launch();
        }

        /**
         * Method to draw a dot or shape based on the selected brush type.
         * @param x The x-coordinate of the drawing point.
         * @param y The y-coordinate of the drawing point.
         * @param group The group to add the shape to.
         * @param brush The current brush configuration (color, size, type).
         */
        public void drawDot(int x, int y, Group group, Brush brush) {
            Node shape = null;
            switch (brush.getType()) {
                case CIRCLE:
                    Circle circle = new Circle();
                    circle.setCenterX(x);
                    circle.setCenterY(y);
                    circle.setFill(brush.getColor());
                    circle.setRadius(brush.getSize());
                    shape = circle;
                    break;
                case SQUARE:
                    Rectangle square = new Rectangle();
                    square.setX(x - brush.getSize() / 2);
                    square.setY(y - brush.getSize() / 2);
                    square.setWidth(brush.getSize()*2);
                    square.setHeight(brush.getSize()*2);
                    square.setFill(brush.getColor());
                    shape = square;
                    break;
                case TRIANGLE:
                    Polygon triangle = new Polygon();
                    double size = brush.getSize();
                    triangle.getPoints().addAll((double)x, y - size,  x - size , y + size ,
                            x + size , y + size  );
                    triangle.setFill(brush.getColor());
                    shape = triangle;
                    break; }
                group.getChildren().add(shape); }

        @Override
        public void start(Stage stage) throws IOException {
            stage.setTitle("Sherif Ashraf ALi Shawashen");
            Brush brush = new Brush();
            HBox hbox = new HBox();
            Group root = new Group();
            scene = new Scene(root, 640, 480);
            // Add a MouseEvent filter for dragging to draw shapes
            scene.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseEvent -> {
                int xCoord = (int) mouseEvent.getX();
                int yCoord = (int) mouseEvent.getY();
                if (yCoord > 40) { // Prevent drawing on the control bar
                        drawDot(xCoord, yCoord, root, brush);
                    }
            });

            // Add a MouseEvent filter for clicking to draw shapes
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                int xCoord = (int) mouseEvent.getX();
                int yCoord = (int) mouseEvent.getY();
                if (yCoord > 40) {// Prevent drawing on the control bar
                        drawDot(xCoord, yCoord, root, brush);
                }
            });

            // Create a "+" button to increase brush size
            Button increaseSize = new Button("+");
            increaseSize.setOnAction(event -> {
                brush.incrementSize();
            });

            // Create a "-" button to decrease brush size
            Button decreaseSize = new Button("-");
            decreaseSize.setOnAction(event -> {
                brush.decrementeSize();
            });

           // Brush type buttons (circle, square, triangle)
            Circle circleShape = new Circle();
            circleShape.setFill(brush.getColor());
            circleShape.setRadius(15);
            circleShape.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    brush.setType(Brush.BrushType.CIRCLE);
                }});

            Rectangle rectShape = new Rectangle();
            rectShape.setWidth(30);
            rectShape.setHeight(30);
            rectShape.setFill(brush.getColor());
            rectShape.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    brush.setType(Brush.BrushType.SQUARE);
                }});

            Polygon triangleShape = new Polygon();
            triangleShape.getPoints().addAll(
                    0.0, 0.0,    // Vertex 1
                    15.0, 30.0, // Vertex 2
                    -15.0, 30.0 // Vertex 3
            );
            triangleShape.setFill(brush.getColor());
            triangleShape.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    brush.setType(Brush.BrushType.TRIANGLE);
                }
            });


         // Color buttons (red, green, orange)
            Circle redColor = new Circle();
            redColor.setFill(Color.RED);
            redColor.setRadius(15);
            redColor.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    brush.setColor(Color.RED);
                    circleShape.setFill(brush.getColor());
                    rectShape.setFill(brush.getColor());
                    triangleShape.setFill(brush.getColor());
                }});

            Circle greenColor = new Circle();
            greenColor.setFill(Color.GREEN);
            greenColor.setRadius(15);
            greenColor.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    brush.setColor(Color.GREEN);
                    circleShape.setFill(brush.getColor());
                    rectShape.setFill(brush.getColor());
                    triangleShape.setFill(brush.getColor());
                }});

            Circle orangeColor = new Circle();
            orangeColor.setFill(Color.ORANGE);
            orangeColor.setRadius(15);
            orangeColor.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    brush.setColor(Color.ORANGE);
                    circleShape.setFill(brush.getColor());
                    rectShape.setFill(brush.getColor());
                    triangleShape.setFill(brush.getColor());
                }});
            // "Clear" button to clear all drawings
            Button clear = new Button("Clear");
            clear.setLayoutX(320);
             clear.addEventFilter(MouseEvent.MOUSE_CLICKED,mouseEvent -> {
                 root.getChildren().clear();
                 root.getChildren().add(hbox);
            });
         // Add all controls to the HBox
            hbox.setSpacing(10);
            hbox.getChildren().addAll(increaseSize,decreaseSize,redColor,greenColor,orangeColor,new HBox(),circleShape,rectShape,
                   triangleShape,clear);
           root.getChildren().add(hbox);
            stage.setScene(scene);
            stage.show();
        }
    }

