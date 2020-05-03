package graphics;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javafx.scene.shape.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 241);
        scene.setFill(Color.rgb(15, 127, 18));

        Polygon triangle = new Polygon(180, 20, 113, 173, 280, 173);
        root.getChildren().add(triangle);
        triangle.setFill(Color.rgb(255, 253, 56));

//        Polygon line1 = new Polygon(65, 20, 113, 173, 280, 173);
        Line line1 = new Line(64, 20, 120, 20);
        root.getChildren().add(line1);
        line1.setStroke(Color.BLUE);
        line1.setStrokeWidth(7.0);

        Line line2 = new Line(233, 20, 302, 20);
        root.getChildren().add(line2);
        line2.setStroke(Color.BLUE);
        line2.setStrokeWidth(7.0);

        Polyline polyline = new Polyline(new double[]{
            20.0, 19.0, 84.0, 222.0, 313.0, 222.0, 370.0, 19.0
        });
        root.getChildren().add(polyline);
        polyline.setStroke(Color.RED);
        polyline.setStrokeWidth(7.0);



        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
