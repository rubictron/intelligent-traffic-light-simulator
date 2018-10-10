
import rubictron.traficlight.service.SerialCom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rubictron
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        SerialCom s=new SerialCom("/dev/ttyACM0", 9600);
        int a=1;
        while(a==1){
            s.send("test");
            a++;
         
        }
          
       
    }
    
}
