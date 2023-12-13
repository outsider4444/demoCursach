package com.example.democursach;

import com.example.democursach.Classes.IsotopesOfChemical_Nb;
import com.example.democursach.Classes.Radionuclide;
import com.example.democursach.Classes.StableIsotope;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiFunction;

public class HelloController {
    @FXML
    private TextField A_textField;
    @FXML
    private TextField B_textField;

    private final Integer t = 864000; // время
    private Integer B_0=0;
    private Integer D_0=0;

    public void plus() {
        double initialValue = Integer.parseInt(A_textField.getText()); // Значение
        double initialTime = 1; // начало по времени
        double totalTime = t; // конец по времени
        double timeStep = 1; // Шаг

        BiFunction<Double, Double, Double> equation = (A, t) -> 2 * t; // Пример: dA/dt = 2t

        double result = solveDifferentialEquation(equation, initialValue, timeStep, totalTime);
        System.out.println("Решение уравнения: " + result);
    }

    public static double solveDifferentialEquation(BiFunction<Double, Double, Double> equation, double initialValue, double timeStep, double totalTime) {
        double currentValue = initialValue;
        double currentTime = 0;

        while (currentTime < totalTime) {
            double derivative = equation.apply(currentValue, currentTime);
            currentValue += derivative * timeStep;
            currentTime += timeStep;
        }

        return currentValue;
    }

//    public void switchToGraphScene(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("graph-view.fxml")));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}