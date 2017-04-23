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

import javafx.concurrent.Task;
import javafx.scene.control.Label;

/**
 *
 * @author computer
 */
public class Timer extends Task<Integer> {

    private int time;

    private Label timeLbl;

    public Timer(int totalTime) {
        this.time = totalTime;
    }

    public void setLabel(Label lbl) {
        this.timeLbl = lbl;
    }

    public void countDown() {
        while (this.time > 0) {
            time -= 1;
            this.timeLbl.setText(Integer.toString(time));
        }
    }

    @Override
    protected Integer call() throws Exception {
        this.countDown();
        return this.time;

    }

}
