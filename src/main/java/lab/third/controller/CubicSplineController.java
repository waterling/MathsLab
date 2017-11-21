package lab.third.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import lab.third.algorithms.*;

import java.util.Map;

public class CubicSplineController {

    @FXML
    public LineChart<String, Double> tenPointsChart;

    @FXML
    public LineChart<String, Double> tenPointsWithOneErrorChart;

    @FXML
    public GridPane tenPointsWithOneErrorGridPane;

    @FXML
    public GridPane fourPointsGridPane;

    @FXML
    public GridPane tenPointsGridPane;

    @FXML
    public GridPane tenPointsWithLongDistanceGridPane;

    @FXML
    public GridPane sinGridPane;
    public TextField userPointTextField;
    public Button userPointButton;

    @FXML
    private LineChart<String, Double> sinChart;

    @FXML
    private LineChart<String, Double> fourPointsChart;
    private Label fourPointsLabel = new Label();
    private Label tenPointsLabel = new Label();
    private Label tenPointsWithOneErrorLabel = new Label();
    private Label tenPointsWithLongDistance = new Label();
    private CubicSpline cubicSplineForSin;
    private CubicSpline cubicSplineFor4Points;
    private CubicSpline cubicSplineFor10Points;
    private CubicSpline cubicSplineFor10PointsWith1Wrong;
    private CubicSpline cubicSplineFor10PointsWithLongDistance;

    @FXML
    private void initialize() {
        {
            sinGridPane.add(new Label("4 точки"),0,0);
            sinGridPane.add(new Label("10 точек"),1,0);
            sinGridPane.add(new Label("10 тп"),2,0);
            sinGridPane.add(new Label("10 тд"),3,0);
        }
        CubicSpline strategy;
       // strategy = new CubicSplineSinForTenPoints();
        cubicSplineFor10PointsWithLongDistance = new CubicSplineWithLongDistance();
       // fillChart(strategy,sinChart);
        fillChart(cubicSplineFor10PointsWithLongDistance, sinChart);
        loadPointsGridPane(cubicSplineFor10PointsWithLongDistance,tenPointsWithLongDistanceGridPane);
        strategy = new CubicSplineForSinus();
        fillChart(strategy, fourPointsChart);
        strategy = new CubicSplineForSinus();
        fillChart(strategy, tenPointsChart);
        strategy = new CubicSplineForSinus();
        fillChart(strategy, tenPointsWithOneErrorChart);

        cubicSplineFor4Points = new CubicSplineOfFourPoints();

        fillChart(cubicSplineFor4Points, fourPointsChart);
        loadPointsGridPane(cubicSplineFor4Points, fourPointsGridPane);

        cubicSplineFor10Points = new CubicSplineOfTenPoints();
        fillChart(cubicSplineFor10Points, tenPointsChart);
        loadPointsGridPane(cubicSplineFor10Points, tenPointsGridPane);

        cubicSplineFor10PointsWith1Wrong = new CubicSplineOfTenPointsWithOneBad();
        fillChart(cubicSplineFor10PointsWith1Wrong, tenPointsWithOneErrorChart);
        loadPointsGridPane(cubicSplineFor10PointsWith1Wrong, tenPointsWithOneErrorGridPane);
    }

    private void loadPointsGridPane(CubicSpline strategy, GridPane tenPointsWithOneErrorGridPane) {
        double[] coordinateX = strategy.getCoordinateX();
        if (strategy.getNumberOfPoints() == 0) {
            return;
        }
        StackPane stackPaneX = new StackPane();
        StackPane stackPaneY = new StackPane();

        Label x = new Label("X:");
        Label y = new Label("Y:");
        stackPaneX.getChildren().add(x);
        stackPaneY.getChildren().add(y);
        stackPaneX.setStyle("-fx-background-color: grey");
        stackPaneY.setStyle("-fx-background-color: grey");
        tenPointsWithOneErrorGridPane.add(stackPaneX, 0, 0);
        tenPointsWithOneErrorGridPane.add(stackPaneY, 0, 1);
        for (int i = 1; i <= strategy.getNumberOfPoints() + 1; i++) {
            tenPointsWithOneErrorGridPane.add(new Label(String.valueOf(coordinateX[i - 1])), i, 0);

        }

        double[] coordinateY = strategy.getCoordinateY();

        for (int i = 1; i <= strategy.getNumberOfPoints() + 1; i++) {
            tenPointsWithOneErrorGridPane.add(new Label(String.valueOf(coordinateY[i - 1])), i, 1);

        }
    }

    private void fillChart(CubicSpline strategy, LineChart<String, Double> chart) {
        strategy.calc();
        Map<String, Double> data = strategy.getFunctionPoints();
        Map<String, Double> xyAsMap = strategy.getCoordinateXCoordinateYAsMap();
        chart.getData().add(transferDataToXYChart(data));
        chart.getData().add(transferDataToXYChart(xyAsMap));
    }

    private XYChart.Series<String, Double> transferDataToXYChart(Map<String, Double> points) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        ObservableList<XYChart.Data<String, Double>> listPoints = FXCollections.observableArrayList();
        points.forEach((s, aDouble) -> listPoints.add(new XYChart.Data<>(s, aDouble)));

        series.getData().addAll(listPoints);
        return series;
    }

    public void calculateUserPoint() {
        try {

            sinGridPane.getChildren().removeAll(fourPointsLabel,tenPointsLabel,tenPointsWithOneErrorLabel,tenPointsWithLongDistance);

            Double userPoint = cubicSplineFor4Points.getUserCalculatedPoint(Double.parseDouble(userPointTextField.getText()));
            fourPointsLabel.setText(String.valueOf(userPoint));

            sinGridPane.add(fourPointsLabel, 0, 1);


            userPoint = cubicSplineFor10Points.getUserCalculatedPoint(Double.parseDouble(userPointTextField.getText()));
            tenPointsLabel.setText(String.valueOf(userPoint));
            sinGridPane.add(tenPointsLabel, 1, 1);

            userPoint = cubicSplineFor10PointsWith1Wrong.getUserCalculatedPoint(Double.parseDouble(userPointTextField.getText()));
            tenPointsWithOneErrorLabel.setText(String.valueOf(userPoint));
            sinGridPane.add(tenPointsWithOneErrorLabel, 2, 1);

            userPoint = cubicSplineFor10PointsWithLongDistance.getUserCalculatedPoint(Double.parseDouble(userPointTextField.getText()));
            tenPointsWithLongDistance.setText(String.valueOf(userPoint));
            sinGridPane.add(tenPointsWithLongDistance, 3, 1);

        } catch (NumberFormatException e) {
            //Do nothing
        }


    }
}
