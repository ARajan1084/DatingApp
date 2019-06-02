package datingapp.program;

import datingapp.exceptions.MessageLengthException;

/**
 * constructs a Message object for the messaging system
 */
public class Message {
    private String message;
    private Person to;
    private Person from;

    /**
     * constructs a Message object
     * @param message the message's text
     * @param recipient the message's recipient
     * @param sender the message's sender
     * @throws MessageLengthException in case message is too long for safe server storage
     */
    public Message (String message, Person sender, Person recipient) throws  MessageLengthException
    {
        if (message.length() > 150) {
            throw new MessageLengthException();
        }
        to = recipient;
        from = sender;
        this.message = message;
    }

    /**
     * gets the message
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * get's the recipient of the message
     * @return the message's recipient
     */
    public Person getRecipient()
    {
        return to;
    }

    /**
     * gets the sender of the message
     * @return the message's sender
     */
    public Person getSender()
    {
        return from;
    }
}
