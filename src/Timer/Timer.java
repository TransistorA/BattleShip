/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 21, 2017
* Time: 10:12:18 AM
*
* Project: csci205finalproject
* Package: Timer
* File: Timer
* Description:
*
* ****************************************
 */
package Timer;

import javafx.animation.AnimationTimer;

///**
// *
// * @author computer
// */
//public class Timer extends Task<Integer> {
//
//    private int time;
//
//    private Label timeLbl;
//
//    public Timer(int totalTime) {
//        this.time = totalTime;
//        this.timeLbl = new Label("");
//    }
//
//    public void setLabel(Label lbl) {
//        this.timeLbl = lbl;
//    }
//
//    public void countDown() {
//        while (this.time > 0) {
//            System.out.println(Integer.toString(time));
//            time -= 1;
//            this.timeLbl.setText(Integer.toString(time));
//        }
//    }
//
//    public Label getLabel() {
//        return this.timeLbl;
//    }
//
//    @Override
//    protected Integer call() throws Exception {
//        this.countDown();
//        return this.time;
//
//    }
//
//}
public class Timer extends AnimationTimer {

    @Override
    public void handle(long now) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
