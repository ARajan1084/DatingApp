package datingapp.program;

/**
 * Represents a Link object. Each link has an answer and a reference to another Node.
 *
 * @author Laasya
 * @version 05/07/19
 */
public class Link {
    private String answer;
    private Node node;

    /**
     * constructs a Link object with a given answer and reference to a node, or null if it's a leaf
     * @param ans answer
     * @param n reference to a node
     */
    public Link (String ans, Node n) {
        answer = ans;
        node = n;
    }

    /**
     * returns the answer of the Link
     * @return answer
     */
    public String getAnswer()
    {
        return answer;
    }

    /**
     * returns the reference to the Node of the Link
     * @return node
     */
    public Node getNode()
    {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
