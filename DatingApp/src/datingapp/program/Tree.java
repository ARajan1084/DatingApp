package datingapp.program;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Implements tree of nodes. Has methods to add questions and answers in the tree through nodes and links
 *
 * @author Achintya and Laasya
 * @version 05/09/19
 */
public class Tree {
    private Node root;
    private int size;

    public Tree (String question, String[] answers) {
        root = new Node(question, answers);
        size = 1;
    }

    public void addNode(String question, String[] answers) {
        Node node = new Node(question, answers);
        Node iter = root;
        Stack<Node> stack = new Stack<>();

        while (true) {
            int checkNode = checkNode(iter);
            if (checkNode >= 0) {
                iter.getLink(checkNode).setNode(node);
                size++;
                return;
            }
            for (int linkIter = 0; linkIter < iter.getNumLinks(); linkIter++) {
                for (int i = linkIter + 1; i < iter.getNumLinks(); i++) {
                    stack.add(iter.getNode(i));
                }
                iter = iter.getNode(linkIter);
                checkNode = checkNode(iter);
                if (checkNode >= 0) {
                    iter.getLink(checkNode).setNode(node);
                    size++;
                    return;
                }
            }
            iter = stack.pop();
        }
    }

    /**
     * helper method for addNode. Returns the index of the next empty space or -1 if all links of the node are full
     * @param node node to check
     * @return index of the next empty link or -1 if all links are full
     */
    private int checkNode (Node node) {
        for (int i = 0; i < node.getNumLinks(); i++) {
            if (node.getNode(i) == null) {
                return i;
            }
        }
        return -1;
    }

    public int getSize() {
        return size;
    }
}
