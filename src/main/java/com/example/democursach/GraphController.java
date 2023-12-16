package com.example.democursach;

import com.example.democursach.Classes.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.democursach.Classes.Data.*;
import static com.example.democursach.HelloController.t;

public class GraphController implements Initializable {
    @FXML
    public static LineChart lineChart;
    @FXML
    public AnchorPane graph_scene;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        var xAxis = new NumberAxis();
        xAxis.setLabel("days");

        var yAxis = new NumberAxis();
        yAxis.setLabel("Power");

        lineChart = new LineChart<>(xAxis, yAxis);
        if (Data.a_list.size()>0 && Data.t_list.size()>0){
            var data = new XYChart.Series<Number, Number>();
            data.setName("A");
            for (int i = 0; i < 10; i++){
                data.getData().add(new XYChart.Data<>(t_list.get(i), a_list.get(i)));
                System.out.println(t_list.get(i) + " " + a_list.get(i));
            }
            lineChart.getData().add(data);
            graph_scene.getChildren().add(lineChart);
        }
    }

    public void AddB(ActionEvent event) throws IOException{
        double k =Math.log(2)/4320000;
        System.out.println("Решение уравнения: " + k);
        double B0 = 0; // Начальное значение A

        // Решение дифференциального уравнения
        solveDifferentialEquationB(k, B0, t);
        if (Data.b_list.size()>0 && Data.t_list.size()>0){
            var data = new XYChart.Series<Number, Number>();
            data.setName("B");
            for (int i = 0; i < 10; i++){
                data.getData().add(new XYChart.Data<>(t_list.get(i), b_list.get(i)));
            }
            lineChart.getData().add(data);
        }
    }
    public static void solveDifferentialEquationB(double k, double A0, double dt) {
        double B = 0;
        double A = Data.A;

        for (int i = 0; i <= dt*10; i+=dt) {
            // Вычисление нового значения A с использованием метода Эйлера
            B = A * (1-Math.exp(-k * i));
            b_list.add(B);
            t_list.add(i);
            // Вывод результатов
            System.out.println("Step " + i + ": B = " + B);
        }
    }


//    public void switchToMainScene(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
}
