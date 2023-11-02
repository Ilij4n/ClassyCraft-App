package raf.dsw.classycraft.app.Loggers;

import raf.dsw.classycraft.app.MessageGenerator.Message;
import raf.dsw.classycraft.app.observer.ISubscriber;

public class ConsoleLogger implements Logger, ISubscriber {
    @Override
    public void log(String text) {
        System.out.println(text);
    }

    @Override
    public void update(Object o) {
        if(o instanceof Message){
            Message message = (Message)o;
            log(message.toString());
        }
    }
}
