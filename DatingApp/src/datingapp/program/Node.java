package datingapp.program;
import java.util.ArrayList;

/**
 * Represents a node of a DatingTree. Each node has a dating pool, a question, and a list of link objects associated
 * with it. Users are stored in nodes based on their answers to questions.
 *
 * @author Laasya
 * @version 05/08/19
 */
public class Node {
    private String argument;
    private ArrayList<Person> pool;
    private ArrayList<Node> children;

    /**
     * constructs a Node object with empty people and links
     */
    public Node(String question) {
        pool = new ArrayList<>();
        children = new ArrayList<Node>();
        this.argument = question;
    }

    /**
     * adds a person to the pool, unless they already exist
     * @param p person to add to the pool
     * @return returns true if operation was successful, false otherwise
     */
    public boolean addPerson(Person p) {
        if (pool.contains(p)) {
            return false;
        }
        pool.add(p);
        return true;
    }

    /**
     * returns the dating pool
     * @return pool of the node
     */
    public ArrayList<Person> getPeople ()
    {
        return pool;
    }

    /**
     * returns the question
     * @return the question
     */
    public String getQuestion () {
        return argument;
    }

    /**
     * sets the question to a specific value
     * @param question the new question
     */
    public void setQuestion (String question) {
        this.argument = question;
    }

    /**
     * adds a new node to the question tree
     * @param child the node to add
     */
    public void addChild(Node child)
    {
        children.add(child);
    }

    /**
     * returns the list of link objects
     * @return links of the node
     */
    public ArrayList<Node> getChildren ()
    {
        return children;
    }

    /**
     * finds a node using a specified tag
     * @param arg the tag to find the node with
     * @return the node that was being searched for; null if it wasn't found
     */
    public Node getNodeByArgument(String arg)
    {
        ArrayList<Node> children= this.getChildren();
        if (children.isEmpty())
        {
            return null;
        }
        for (Node a : children)
        {
            if ((a.getQuestion()).equals(arg))
            {
                return a;
            }
        }
        return null;
    }

}
