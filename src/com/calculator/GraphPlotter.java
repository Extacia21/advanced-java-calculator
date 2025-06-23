package com.calculator;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.application.Platform;

public class GraphPlotter {
    public static void plot(String function) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            NumberAxis xAxis = new NumberAxis(-10, 10, 1);
            NumberAxis yAxis = new NumberAxis(-10, 10, 1);
            LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
            chart.setTitle("Graph of " + function);

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(function);

            for (double x = -10; x <= 10; x += 0.1) {
                String expr = function.replace("x", String.valueOf(x));
                String yStr = new CalculatorEngine().evaluate(expr);
                try {
                    double y = Double.parseDouble(yStr);
                    series.getData().add(new XYChart.Data<>(x, y));
                } catch (Exception ignored) {}
            }

            chart.getData().add(series);
            Scene scene = new Scene(chart, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Function Plot");
            stage.show();
        });
    }
}
