/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubictron.traficlight.controller;

/**
 *
 * @author rubictron
 */
public class lightController {

    double A, B, C, D;
    double cycleTime = 100;

    public void setRate(double A, double B, double C, double D) {

        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;

    }

    double[] calqs(double A, double B, double C, double D) {
        double[] q = new double[4];

        q[0] = (0.415 * D > 0.415 * B) ? 0.415 * D : 0.415 * B;
        q[1] = (0.381 * C > 0.381 * A) ? 0.381 * C : 0.381 * A;
        q[2] = (0.2625 * D > 0.2625 * B) ? 0.2625 * D : 0.2625 * B;
        q[3] = (0.3675 * D > 0.3675 * B) ? 0.3675 * D : 0.3675 * B;
        
        
        

        return q;
    }

    double[] calGreenTimeRatio(double[] q) {

        double[] gt = new double[5];

        double total = q[0] + q[1] + q[2] + q[3];
        
        this.cycleTime= 24/(1-0.0005847*total);

        gt[0] = q[0] / total;
        gt[1] = q[1] / total;
        gt[2] = q[2] / total;
        gt[3] = q[3] / total;
        gt[4] = total;
        return gt;

    }

    public double[] getData() {
        double[] data = new double[5];
        double[] qs = calqs(this.A, this.B, this.C, this.D);
        double[] gt = calGreenTimeRatio(qs);

        data[0] = gt[0] * this.cycleTime; //g1 ratio*100 = BL
        data[1] = gt[1] * this.cycleTime; //g2 ratio*100 = AL
        data[2] = gt[2] * this.cycleTime; //g3 ratio*100 = BR
        data[3] = gt[3] * this.cycleTime; //g4 ratio*100 = AR
        data[4] = this.cycleTime;

        return data;

    }

}
