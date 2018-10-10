/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubictron.traficlight.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import rubictron.traficlight.controller.lightController;

/**
 * FXML Controller class
 *
 * @author rubictron
 */
public class DashboardController implements Initializable {

    @FXML
    private Circle redA;
    @FXML
    private Circle yelA;
    @FXML
    private Circle greenAR;
    @FXML
    private Circle greenAL;
    @FXML
    private Circle greenCL;

    @FXML
    private Circle yelC;
    @FXML
    private Circle redC;
    @FXML
    private Circle greenDL;
    @FXML
    private Circle greenDR;
    @FXML
    private Circle yelD;
    @FXML
    private Circle redD;
    @FXML
    private Circle redB;
    @FXML
    private Circle yelB;
    @FXML
    private Circle greenBR;
    @FXML
    private Circle greenBL;
    @FXML
    private Circle greenCR;

    enum Road {
        AL, AR, BL, BR
    };

    @FXML
    private JFXTextField flowRA;
    @FXML
    private JFXTextField flowRC;
    @FXML
    private JFXTextField flowRD;
    @FXML
    private JFXTextField flowRB;
    @FXML
    private JFXButton btnStart;
    @FXML
    private JFXButton btnStop;

    lightController lController;
//    SerialCom arduino=new SerialCom("/dev/ttyACM0", 9600);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allRoadClose();
        lController = new lightController();

        // TODO
    }

    @FXML
    private void btnStartClick(ActionEvent event) {
        double A, B, C, D;

        A = Double.parseDouble(flowRA.getText());
        B = Double.parseDouble(flowRB.getText());
        C = Double.parseDouble(flowRC.getText());
        D = Double.parseDouble(flowRD.getText());
        lController.setRate(A, B, C, D);
        double[] data = lController.getData();
        System.out.println("BL = " + dtoSecond(data[0])
                + "\nAL = " + dtoSecond(data[1])
                + "\nBR = " + dtoSecond(data[2])
                + "\nAR = " + dtoSecond(data[3]));
        
        String str=""+ dtoSecond(data[0])
                + dtoSecond(data[1])
                + dtoSecond(data[2])
                + dtoSecond(data[3])+"\n";
        System.out.println(str);


        runOnetime(data[0],
                data[1],
                data[2],
                data[3]
        );

    }

    long dtoMill(double d) {
        Double obj = new Double(d);
        long l = obj.longValue();
        return l * 1000;
    }

    long dtoSecond(double d) {
        Double obj = new Double(d);
        long l = obj.longValue();
        return l;
    }

    void runOnetime(double a, double b, double c, double d) {

        TimerTask alert = new TimerTask() {
            public void run() {
                alertRoad(Road.AL);
            }
        };

        TimerTask alert2 = new TimerTask() {
            public void run() {
                alertRoad(Road.BL);
            }
        };

        TimerTask alert3 = new TimerTask() {
            public void run() {
                alertRoad(Road.AL);
            }
        };

        TimerTask task = new TimerTask() {
            public void run() {
                openRoad(Road.AL);
            }
        };

        TimerTask task2 = new TimerTask() {
            public void run() {
                openRoad(Road.BR);
            }
        };

        TimerTask task3 = new TimerTask() {
            public void run() {
                openRoad(Road.AR);
            }
        };

        TimerTask task4 = new TimerTask() {
            public void run() {
                allLightOff();
            }
        };

        Timer timer = new Timer("Timer");

        long delay = dtoMill(a * 0.9);
        openRoad(Road.BL);
        timer.schedule(alert, delay);
        delay += dtoMill(a * 0.1);
        timer.schedule(task, delay);

        delay += dtoMill(b * 0.9);
        timer.schedule(alert2, delay);
        delay += dtoMill(b * 0.1);
        timer.schedule(task2, delay);

        delay += dtoMill(c * 0.9);
        timer.schedule(alert3, delay);
        delay += dtoMill(c * 0.1);
        timer.schedule(task3, delay);

        delay += dtoMill(d * 0.9);
        timer.schedule(task4, delay);
//        delay+=dtoL(d*0.1);
//        timer.schedule(alert, delay);

    }

    @FXML
    private void btnStopClick(ActionEvent event) {

        System.out.print("stop");

    }

    void openRoad(Road road) {
        allRoadClose();
        switch (road) {

            case AL:
                redA.setStyle("-fx-fill: black");
                redC.setStyle("-fx-fill: black");
                greenAL.setStyle("-fx-fill: green");
                greenCL.setStyle("-fx-fill: green");
                break;
            case AR:
                redA.setStyle("-fx-fill: black");
                redC.setStyle("-fx-fill: black");
                greenAR.setStyle("-fx-fill: green");
                greenCR.setStyle("-fx-fill: green");
                break;
            case BL:
                redB.setStyle("-fx-fill: black");
                redD.setStyle("-fx-fill: black");
                greenBL.setStyle("-fx-fill: green");
                greenDL.setStyle("-fx-fill: green");
                break;
            case BR:
                redB.setStyle("-fx-fill: black");
                redD.setStyle("-fx-fill: black");
                greenBR.setStyle("-fx-fill: green");
                greenDR.setStyle("-fx-fill: green");
                break;

        };

    }

    void alertRoad(Road r) {

        switch (r) {
            case AL:
                r = Road.AR;
            case AR:
                yelA.setStyle("-fx-fill: yellow");
                yelC.setStyle("-fx-fill: yellow");
                redA.setStyle("-fx-fill: black");
                redC.setStyle("-fx-fill: black");
                break;
            case BL:
                r = Road.BR;
            case BR:
                yelB.setStyle("-fx-fill: yellow");
                yelD.setStyle("-fx-fill: yellow");
                redB.setStyle("-fx-fill: black");
                redD.setStyle("-fx-fill: black");
                break;

        };

    }

    void allRoadClose() {

        allLightOff();

        redA.setStyle("-fx-fill: red");
        redB.setStyle("-fx-fill: red");
        redC.setStyle("-fx-fill: red");
        redD.setStyle("-fx-fill: red");

    }

    void allLightOff() {

        redA.setStyle("-fx-fill: black");
        redB.setStyle("-fx-fill: black");
        redC.setStyle("-fx-fill: black");
        redD.setStyle("-fx-fill: black");

        yelA.setStyle("-fx-fill: black");
        yelB.setStyle("-fx-fill: black");
        yelC.setStyle("-fx-fill: black");
        yelD.setStyle("-fx-fill: black");

        greenAL.setStyle("-fx-fill: black");
        greenBL.setStyle("-fx-fill: black");
        greenCL.setStyle("-fx-fill: black");
        greenDL.setStyle("-fx-fill: black");

        greenAR.setStyle("-fx-fill: black");
        greenBR.setStyle("-fx-fill: black");
        greenCR.setStyle("-fx-fill: black");
        greenDR.setStyle("-fx-fill: black");

    }

}
