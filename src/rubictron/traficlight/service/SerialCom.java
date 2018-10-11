/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubictron.traficlight.service;

import arduino.*;

/**
 *
 * @author rubictron
 */
public class SerialCom {

    private String ArduinoPort = "";
    private int BAUD_RATE = 9600;
    private Arduino arduino;

    public SerialCom() {

        arduino = new Arduino(ArduinoPort, BAUD_RATE);
//        arduino.openConnection();
//        arduino.serialWrite('1');
//        arduino.serialWrite('1', 20);
    }

    public SerialCom(String ArduinoPort, int BAUD_RATE) {
        this.ArduinoPort = ArduinoPort;
        this.BAUD_RATE = BAUD_RATE;

        arduino = new Arduino(this.ArduinoPort, this.BAUD_RATE);
//        arduino.openConnection();

    }

    public void send(String s) {

        this.getArduino().openConnection();
        this.getArduino().serialWrite(s + "\n");
        System.out.println("Serial Write = " + s);
        this.getArduino().closeConnection();

    }

    public String read() {
        String serialRead = this.getArduino().serialRead(10);
        return serialRead;
    }

    /**
     * @param ArduinoPort the ArduinoPort to set
     */
    public void setArduinoPort(String ArduinoPort) {
        this.ArduinoPort = ArduinoPort;
    }

    /**
     * @param BAUD_RATE the BAUD_RATE to set
     */
    public void setBAUD_RATE(int BAUD_RATE) {
        this.BAUD_RATE = BAUD_RATE;
    }

    /**
     * @return the arduino
     */
    public Arduino getArduino() {
        return arduino;
    }

}
