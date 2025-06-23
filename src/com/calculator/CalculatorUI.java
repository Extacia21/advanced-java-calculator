package com.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CalculatorUI extends Application {

    private CalculatorEngine engine = new CalculatorEngine();

    @Override
    public void start(Stage primaryStage) {
        TextField display = new TextField();
        display.setEditable(false);

        GridPane buttons = new GridPane();
        buttons.setHgap(5);
        buttons.setVgap(5);

        String[] keys = {
                "7", "8", "9", "/", "sqrt",
                "4", "5", "6", "*", "x^2",
                "1", "2", "3", "-", "log",
                "0", ".", "=", "+", "sin",
                "cos", "tan", "(", ")", "C"
        };

        int row = 0, col = 0;
        for (String key : keys) {
            Button btn = new Button(key);
            btn.setMinSize(50, 50);
            btn.setOnAction(e -> {
                String input = btn.getText();
                if ("=".equals(input)) {
                    String result = engine.evaluate(display.getText());
                    display.setText(result);
                } else if ("C".equals(input)) {
                    display.clear();
                } else {
                    display.appendText(input);
                }
            });
            buttons.add(btn, col, row);
            col++;
            if (col > 4) {
                col = 0;
                row++;
            }
        }

        Button graphBtn = new Button("Plot Graph");
        graphBtn.setOnAction(e -> GraphPlotter.plot(display.getText()));

        VBox root = new VBox(10, display, buttons, graphBtn);
        root.setStyle("-fx-padding: 10px");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Advanced Calculator");
        primaryStage.show();
    }
}
