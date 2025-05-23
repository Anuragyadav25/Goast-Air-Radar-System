import javax.sound.sampled.*;
import java.io.*;

public class SoundPlayer {
    public static void playAlertSound() {
        try {
            File soundFile = new File("resources/alert.wav");
            if (!soundFile.exists()) return;
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}