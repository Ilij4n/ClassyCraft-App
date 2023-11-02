package raf.dsw.classycraft.app.Loggers;

import raf.dsw.classycraft.app.MessageGenerator.Message;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileLogger implements Logger, ISubscriber {
    @Override
    public void log(String text) {
        BufferedWriter bw = null;

        try {
            File file = new File("src\\main\\resources\\log.txt");
            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(text);
            bw.write("\n");
        } catch (Exception e) {
            System.err.println("File nije nadjen");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                System.err.println("Greska pri zatvaranju");
            }
        }
    }

    @Override
    public void update(Object o) {
        if(o instanceof Message){
            Message message = (Message)o;
            log(message.toString());
        }
    }
}
