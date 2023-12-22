package com.example.democursach;

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
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.democursach.Data.*;
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

    Random random = new Random();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Секунды");

        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("Power");

        lineChart = new LineChart<>(xAxis, yAxis);
        if (Data.a_list.size()>0 && Data.t_list.size()>0){
            XYChart.Series<Number, Number> data = new XYChart.Series<Number, Number>();
            data.setName("A");
            for (int i = 0; i < t_list.size(); i++){
                data.getData().add(new XYChart.Data<>(t_list.get(i), a_list.get(i)));
//                System.out.println(t_list.get(i) + " " + a_list.get(i));
            }
            lineChart.getData().add(data);
            lineChart.setPrefWidth(550);
            graph_scene.getChildren().add(lineChart);
        }
    }

    public void AddB(ActionEvent event) throws IOException{
        int next_k = random.nextInt(10000000-100000+1)+100000;
        double k1 =Math.log(2)/next_k;

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
        int next_k1 = random.nextInt(10000000-100000+1)+100000;
        int next_k2 = random.nextInt(10000-1000+1)+1000;
        double k1 =Math.log(2)/next_k1;
        double k2 =Math.log(2)/next_k2;

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
    public void switchToHelloScene(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToIsotopeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("isotope-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToGraphScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("table-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
