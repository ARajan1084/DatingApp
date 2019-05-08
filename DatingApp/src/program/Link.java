package program;

/**
 * Represents a Link object. Each link has an answer and a reference to another Node.
 *
 * @author Laasya
 * @version 05/07/19
 */
public class Link {
    private String answer;
    private Node node;

    public Link (String ans, Node n) {
        answer = ans;
        node = n;
    }

    public String getAnswer() {
        return answer;
    }

    public Node getNode() {
        return node;
    }
}
