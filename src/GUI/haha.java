/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Joseph DiPalma, Annan Miao, and Ben Xu
* Date: Apr 30, 2017
* Time: 11:14:30 PM
*
* Project: csci205finalproject
* Package: GUI
* File: haha
* Description:
*
* ****************************************
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author am049
 */
public class haha extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        InputStream in = new FileInputStream(new File("a.txt"));
        //AudioStream as = new AudioStream(in);
        //AudioPlayer.player.start(as);
        //AudioPlayer.player.stop(as);
        /*final URL resource = getClass().getResource(
                "/nfs/unixspace/linux/accounts/student/a/am049/csci205FinalProject/src/furrow.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        primaryStage.setTitle("Audio Player 1");
        primaryStage.setWidth(200);
        primaryStage.setHeight(200);
        primaryStage.show();*/
    }
}
