import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverter extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        // Create layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Input label and text field
        Label inputLabel = new Label("Enter Value:");
        GridPane.setConstraints(inputLabel, 0, 0);

        TextField inputField = new TextField();
        GridPane.setConstraints(inputField, 1, 0);

        // Conversion options
        Label conversionLabel = new Label("Convert From:");
        GridPane.setConstraints(conversionLabel, 0, 1);

        ComboBox<String> fromUnit = new ComboBox<>();
        fromUnit.getItems().addAll("Meters", "Kilometers", "Centimeters");
        fromUnit.setValue("Meters");
        GridPane.setConstraints(fromUnit, 1, 1);

        Label toLabel = new Label("To:");
        GridPane.setConstraints(toLabel, 0, 2);

        ComboBox<String> toUnit = new ComboBox<>();
        toUnit.getItems().addAll("Meters", "Kilometers", "Centimeters");
        toUnit.setValue("Kilometers");
        GridPane.setConstraints(toUnit, 1, 2);

        // Convert button
        Button convertButton = new Button("Convert");
        GridPane.setConstraints(convertButton, 1, 3);

        // Output label
        Label outputLabel = new Label("Result: ");
        GridPane.setConstraints(outputLabel, 1, 4);

        // Conversion logic
        convertButton.setOnAction(e -> {
            try {
                double inputValue = Double.parseDouble(inputField.getText());
                String from = fromUnit.getValue();
                String to = toUnit.getValue();

                double result = convert(inputValue, from, to);
                outputLabel.setText("Result: " + result + " " + to);
            } catch (NumberFormatException ex) {
                outputLabel.setText("Invalid input. Please enter a number.");
            }
        });

        // Add all elements to the layout
        grid.getChildren().addAll(inputLabel, inputField, conversionLabel, fromUnit, toLabel, toUnit, convertButton, outputLabel);

        // Set up scene and stage
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double convert(double value, String fromUnit, String toUnit) {
        // Conversion factors
        double meters = switch (fromUnit) {
            case "Meters" -> value;
            case "Kilometers" -> value * 1000;
            case "Centimeters" -> value / 100;
            default -> value;
        };

        return switch (toUnit) {
            case "Meters" -> meters;
            case "Kilometers" -> meters / 1000;
            case "Centimeters" -> meters * 100;
            default -> meters;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
