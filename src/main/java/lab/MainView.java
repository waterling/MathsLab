package lab;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lab.fourth.view.ODENode;
import lab.third.view.CubicSplineNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainView extends Application {

    private Map<String, Node> nodes = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Math");
        FlowPane rootFlowPane = new FlowPane();
        rootFlowPane.setAlignment(Pos.CENTER);


        Scene scene = new Scene(rootFlowPane, 1200, 850);

        rootFlowPane.getChildren().addAll(setButtons(rootFlowPane, scene));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private List<Button> setButtons(FlowPane rootFlowPane, Scene scene) {
        List<Button> buttonList = new ArrayList<>();
        StrategyClient strategyClient = new StrategyClient();

        //Height = 25
        scene.getStylesheets().add(MainView.class.getResource("/lab/third/css/chart.css").toExternalForm());
        try {
            rootFlowPane.getChildren().remove(5);
        } catch (Exception e) {
//                e.printStackTrace();
        }

        Button buttonLab3 = new Button("Загрузить графики");
        buttonLab3.setPrefWidth(240);
        buttonLab3.setOnMouseClicked(keyEvent -> {

            strategyClient.setConcreteNode(new CubicSplineNode());
            Node node = strategyClient.doDraw();
            if (rootFlowPane.getChildren().size() >= 2) {
                rootFlowPane.getChildren().remove(0);
            }

            rootFlowPane.getChildren().add(0, node);
            nodes.put("3", node);
            buttonLab3.setText("Перезагрузить");
        });
        buttonList.add(buttonLab3);
        Button buttonLab4 = new Button("Лаба 4");
        buttonLab4.setPrefWidth(240);
        buttonLab4.setOnMouseClicked(mouseEvent -> {
            scene.getStylesheets().add(MainView.class.getResource("/lab/third/css/chart.css").toExternalForm());
            try {
                rootFlowPane.getChildren().remove(0);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            if (nodes.containsKey("4")) {
                rootFlowPane.getChildren().add(0,nodes.get("4"));
            } else {
                strategyClient.setConcreteNode(new ODENode());
                Node node = strategyClient.doDraw();
                rootFlowPane.getChildren().add(0,node);
                nodes.put("4", node);
            }

        });
        buttonList.add(buttonLab4);

        return buttonList;
    }


}
