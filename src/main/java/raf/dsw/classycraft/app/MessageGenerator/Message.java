package raf.dsw.classycraft.app.MessageGenerator;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Message {
    private String text;
    private MessageType type;
    private String dateAndTime;

    public Message(String text, MessageType type) {
        this.text = text;
        this.type = type;
        this.dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "["+type.toString()+"]"+" ["+dateAndTime+"]"+" "+text;
    }
}
