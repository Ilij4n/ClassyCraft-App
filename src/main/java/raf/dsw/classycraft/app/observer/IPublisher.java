package raf.dsw.classycraft.app.observer;

import java.util.List;

public interface IPublisher {
    void addSub(ISubscriber subscriber);

    void removeSub(ISubscriber subscriber);

    void notifySubs(Object o);
}
