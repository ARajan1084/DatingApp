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
    private String question;
    private ArrayList<Person> pool;
    private ArrayList<Link> links;

    /**
     * constructs a Node object with empty people and links
     */
    public Node(String question) {
        pool = new ArrayList<>();
        links = new ArrayList<>();
        this.question = question;
    }

    public Node (String question, String[] answers) {
        this.question = question;
        links = new ArrayList<>();
        pool = new ArrayList<>();
        for (String answer: answers) {
            links.add(new Link(answer, null));
        }
    }

    /**
     * constructs a Node object with the specified people and links
     * @param people specified people
     * @param links specified links
     */
    public Node(ArrayList<Person> people, ArrayList<Link> links) {
        pool = people;
        this.links = links;
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
     * adds a link to links
     * @param link link to add
     */
    public void addLink(Link link) {
        links.add(link);
    }

    /**
     * returns the Link at the specified index
     * @param i index
     * @return returns the Link or null if i is out of bounds
     */
    public Link getLink(int i) {
        if (i >= links.size()) {
            return null;
        }
        return links.get(i);
    }

    public Node getNode(int i) {
        if (i >= links.size()) {
            return null;
        }
        return links.get(i).getNode();
    }

    /**
     * returns the dating pool
     * @return pool of the node
     */
    public ArrayList<Person> getPeople () {
        return pool;
    }

    public String getQuestion () {
        return question;
    }

    public void setQuestion (String question) {
        this.question = question;
    }

    public int getNumLinks () {
        return links.size();
    }

    /**
     * returns the list of link objects
     * @return links of the node
     */
    public ArrayList<Link> getLinks () {
        return links;
    }
}
