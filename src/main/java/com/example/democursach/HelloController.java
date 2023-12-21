package com.example.democursach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;

import static com.example.democursach.Data.*;

public class HelloController implements Initializable {
    @FXML
    private TextField A_textField;
    @FXML
    private Pane hello_scene;

    public static final Integer t = 864000; // время

    public void plus(ActionEvent event) throws IOException {
        Random random = new Random();
        int next_k = random.nextInt(10000000-1000000+1)+1000000;
        double k =Math.log(2)/next_k;
        double A0 = Integer.parseInt(A_textField.getText()); // Начальное значение A

        // Решение дифференциального уравнения
        solveDifferentialEquation(k, A0, t);
        switchToGraphScene(event);

    }

    public static void solveDifferentialEquation(double k, double A0, double dt) {
        double A = A0;
        Data.A = A;
        a_list.clear();
        b_list.clear();
        d_list.clear();
        t_list.clear();

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

    public void switchToIsotopeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("isotope-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void copyExcel(ActionEvent event) throws IOException {
        String desktop_path = System.getProperty("user.home") + "/Desktop";
        File file = new File(System.getProperty("user.dir")+"/files/kinetika.xlsx");
        File new_file = new File(desktop_path+"/kinetika.xlsx");
        try {
            FileUtils.copyFile(file, new_file);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Статус");
            alert.setContentText("Экспортировано успешно!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // pass
    }
}