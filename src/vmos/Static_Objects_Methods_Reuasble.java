package vmos;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public abstract class Static_Objects_Methods_Reuasble {
    static final String Mypath="D:\\Applications\\Projects & Practices\\Projects\\Java Projects\\VMOS\\src\\";
    static final String MypathForHTML="file:///D:/Applications/Projects%20&%20Practices/Projects/Java%20Projects/VMOS/src/";
    protected void click() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\Click.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        }

    }

    protected void start_s() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\Start_S.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        }
    }

    protected void next_clk() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\next.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        }

    }

    protected void snext() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\unsleep.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        }
    }

    protected void showclick() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\showis.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        }
    }

    protected void hideclick() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Mypath+"Audios\\hideis.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
        }

    }

}
