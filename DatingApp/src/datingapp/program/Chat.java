package datingapp.program;

import java.util.LinkedList;

public class Chat {
    private LinkedList<Message> messages;
    private int size;

    public Chat() {
        messages = new LinkedList<>();
        size = 0;
    }

    public void addMessage(Message m) {
        messages.add(m);
        size++;
        maintainList();
    }

    public LinkedList<Message> getMessages () {
        return messages;
    }

    public void maintainList() {
        while (size > 50) {
            messages.removeLast();
        }
    }
}
