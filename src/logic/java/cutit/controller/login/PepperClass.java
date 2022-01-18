package cutit.controller.login;

public class PepperClass extends Thread{
    public void run(){

        for(int i = 0; i<10; i++){
            System.out.println("-----------Thread startato!" + i);

        }
    }
}
