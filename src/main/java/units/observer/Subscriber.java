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
        try(FileInputStream fs = new FileInputStream(new File("\\src\\main\\resources\\sounds\\fan.mp3")))
        {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fs);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            System.out.print(String.format("{0} is dead. ", unit.getName()));
            Thread.sleep(500);

            audioStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
