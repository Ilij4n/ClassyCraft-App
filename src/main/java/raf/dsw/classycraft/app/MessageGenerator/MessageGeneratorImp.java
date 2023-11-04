package raf.dsw.classycraft.app.MessageGenerator;

import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImp implements MessageGenerator, IPublisher {

    List<ISubscriber> subscribers;

    public MessageGeneratorImp() {
        subscribers = new ArrayList<>();
    }

    @Override
    public void generateMessage(String text, MessageType type) {
        Message message = new Message(text,type);
        notifySubs(message);
    }

    @Override
    public void addSub(ISubscriber subscriber) {
        // Nadjamo se da necemo imati duplikat subove jer ih mi vezujemo u kodu...
        subscribers.add(subscriber);
    }

    @Override
    public void removeSub(ISubscriber subscriber) {
        /*FIXME
             potencijalno cemo morati za svaku klasu morati da override equals
             ako bude problema oko brisanja vrv je ovde. Uraditi @HashAndEquals u lombok
             anotaciji ako za to bude potrebe.
         */
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubs(Object o) {
        for(ISubscriber sub: subscribers){
            sub.update(o);
        }
    }
}
