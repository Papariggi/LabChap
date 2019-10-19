package units.observer;

import army.Army;
import org.w3c.dom.events.EventListener;
import units.AllUnits;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

public class Subscriber implements Observer
{
    @Override
    public void handleEvent(AllUnits unit) {
        try {
            File soundFile = new File("C:\\Users\\Laplass\\IdeaProjects\\LabChap\\src\\main\\resources\\sounds\\fanfare.wav"); //Звуковой файл

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();

            clip.open(ais);

            clip.setFramePosition(0);
            clip.start();

            Thread.sleep(500);
            clip.stop();
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
