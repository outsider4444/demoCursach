package com.example.democursach;

import com.example.democursach.Classes.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.democursach.Classes.Data.*;
import static com.example.democursach.HelloController.t;

public class GraphController implements Initializable {
    @FXML
    public static LineChart lineChart;
    @FXML
    public AnchorPane graph_scene;

    @FXML
    public Button b_button;
    @FXML
    public Button d_button;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Power");

        lineChart = new LineChart<>(xAxis, yAxis);
        if (Data.a_list.size()>0 && Data.t_list.size()>0){
            XYChart.Series<Number, Number> data = new XYChart.Series<Number, Number>();
            data.setName("A");
            for (int i = 0; i < t_list.size(); i++){
                data.getData().add(new XYChart.Data<>(t_list.get(i), a_list.get(i)));
                System.out.println(t_list.get(i) + " " + a_list.get(i));
            }
            lineChart.getData().add(data);
            graph_scene.getChildren().add(lineChart);
        }
    }

    public void AddB(ActionEvent event) throws IOException{
        double k1 =Math.log(2)/4320000;

        solveDifferentialEquationB(k1, t);
        if (Data.b_list.size()>0 && Data.t_list.size()>0){
            XYChart.Series<Number, Number> data = new XYChart.Series<Number, Number>();
            data.setName("B");
            for (int i = 0; i <= 10; i++){
                data.getData().add(new XYChart.Data<>(t_list.get(i), b_list.get(i)));
            }
            lineChart.getData().add(data);
            b_button.setDisable(true);
        }
    }

    public void AddD(ActionEvent event) throws IOException{
        double k1 =Math.log(2)/4320000;
        double k2 =Math.log(2)/1500;

        // Решение дифференциального уравнения
        solveDifferentialEquationD(k1, k2, t);
        if (Data.d_list.size()>0 && Data.t_list.size()>0){
            XYChart.Series<Number, Number> data = new XYChart.Series<Number, Number>();
            data.setName("D");
            for (int i = 0; i <= 10; i++){
                data.getData().add(new XYChart.Data<>(t_list.get(i), d_list.get(i)));
            }
            lineChart.getData().add(data);
            d_button.setDisable(true);
        }
    }
    public static void solveDifferentialEquationD(double k1, double k2, double dt) {
        double D = 0;
        double A = Data.A;

        for (int i = 0; i <= dt*10; i+=dt) {
            D = A*(k1/(k2-k1))*(Math.exp(-k1*i)-Math.exp(-k2*i));
            System.out.println("D:"+D + " " + i);
            d_list.add(D);
            t_list.add(i);
        }
    }
    public static void solveDifferentialEquationB(double k, double dt) {
        double B = 0;
        double A = Data.A;

        for (int i = 0; i <= dt*10; i+=dt) {
            B = A * (1-Math.exp(-k * i));
            b_list.add(B);
            t_list.add(i);
        }
    }

    public void switchToGraphScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("table-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
