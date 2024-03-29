package ru.nsu.fit.g18214.shatalov;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class GameLoop extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    ArrayList<String> input = new ArrayList<>();
    Grid grid = new Grid(512, 512);
    Snake snake = new Snake(grid.getCols() / 2, grid.getRows() / 2, grid);
    ArrayList<Food> foods = new ArrayList<>();

    primaryStage.setTitle("Snake The Game");

    Group root = new Group();

    Canvas canvas = new Canvas(512, 512);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    root.getChildren().add(canvas);
    Scene theScene = new Scene(root);
    primaryStage.setResizable(false);
    primaryStage.setScene(theScene);
    primaryStage.show();

    theScene.setOnKeyPressed((new EventHandler<KeyEvent>() {
          public void handle(KeyEvent keyEvent) {
        /*String code = keyEvent.getCode().toString();
        if (!input.contains(code)) {
          input.add(code);
        }
      }
    }));

    theScene.setOnKeyReleased(
        new EventHandler<KeyEvent>() {
          public void handle(KeyEvent e) {
            String code = e.getCode().toString();
            input.remove(code);
          }
        }
    );*/
            switch (keyEvent.getCode()) {
              case UP:
                snake.dirUp();
                break;
              case DOWN:
                snake.dirDown();
                break;
              case LEFT:
                snake.dirLeft();
                break;
              case RIGHT:
                snake.dirRight();
                break;
            }
          }
        }));


    Timeline gameLoop = new Timeline();
    gameLoop.setCycleCount(Timeline.INDEFINITE);
    Random r = new Random();
    for (int i = 0; i < 5; i++) {
      foods.add(new Food(r.nextInt(grid.getCols()), r.nextInt(grid.getRows())));
    }


    KeyFrame kf = new KeyFrame(
        Duration.seconds(0.05),
        new EventHandler<ActionEvent>() {
          public void handle(ActionEvent actionEvent) {
            //gc.clearRect(0, 0, 512, 512);
            boolean trigger = false;
            gc.setFill(Grid.COLOR);
            gc.fillRect(0, 0, 512, 512);
            gc.setFill(Food.COLOR);

            foods.forEach(food -> food.getSprite().render(gc));
            /*if (input.contains("UP")) {
              snake.dirUp();
            }
            if (input.contains("DOWN")) {
              snake.dirDown();
            }
            if (input.contains("RIGHT")) {
              snake.dirRight();
            }
            if (input.contains("LEFT")) {
              snake.dirLeft();
            }*/
            for (int i = 0; i < 5; i++) {
              if (foods.get(i).getSprite().intersects(snake.getHead())) {
                snake.grow(snake.getHead().positionX + snake.getxVelocity(),
                    snake.getHead().positionY + snake.getyVelocity());
                foods.get(i).getSprite().setPosition(r.nextInt(grid.getCols()), r.nextInt(grid.getRows()));

              }
            }

            snake.move();
            gc.setFill(Snake.COLOR);
            snake.tail.forEach(sprite -> sprite.render(gc));

          }
        });

    gameLoop.getKeyFrames().add(kf);
    gameLoop.play();

  }

}