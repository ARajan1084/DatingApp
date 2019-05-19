package datingapp.program;

import java.util.LinkedList;

public class Chat {
    private LinkedList<Message> messages;
    private Person person1;
    private Person person2;
    private int size;

    public Chat(Person p1, Person p2) {
        messages = new LinkedList<>();
        size = 0;
        person1 = p1;
        person2 = p2;
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

    public Person getPerson1 () {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void sendMessage (Message message) {
    }
}
