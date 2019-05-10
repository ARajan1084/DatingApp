package datingapp.program;

public class Message {
    private String message;
    private Person to;
    private Person from;

    public Message (String message, Person recipient, Person sender) {
        to = recipient;
        from = sender;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Person getRecipient () {
        return to;
    }

    public Person getSender() {
        return from;
    }
}
