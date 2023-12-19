package com.example.democursach;

import com.example.democursach.Classes.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.democursach.Classes.Data.a_list;
import static com.example.democursach.Classes.Data.t_list;

public class HelloController implements Initializable {
    @FXML
    private TextField A_textField;
    @FXML
    private AnchorPane hello_scene;

    public static final Integer t = 864000; // время

    public void plus(ActionEvent event) throws IOException {
        double k =Math.log(2)/4320000;
        double A0 = Integer.parseInt(A_textField.getText()); // Начальное значение A

        // Решение дифференциального уравнения
        solveDifferentialEquation(k, A0, t);

        switchToGraphScene(event);

    }
    public static void solveDifferentialEquation(double k, double A0, double dt) {
        double A = A0;
        Data.A = A;
        for (int i = 0; i <= dt*10; i+=dt) {
            // Вычисление нового значения A с использованием метода Эйлера
            A = Data.A * Math.exp(-k * i);
            a_list.add(A);
            t_list.add(i);
        }
    }


    public void switchToGraphScene(ActionEvent event) throws IOException {
        AnchorPane graph_scene = FXMLLoader.load(getClass().getResource("graph-view.fxml"));
        hello_scene.getChildren().removeAll();
        hello_scene.getChildren().setAll(graph_scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}