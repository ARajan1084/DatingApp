package datingapp.program;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implements tree of nodes. Has methods to add questions and answers in the tree through nodes and links
 *
 * @author Laasya
 * @version 05/09/19
 */
public class Tree {
    private Node root;
    private int size;

    /**
     * constructor for the Tree object
     */
    public Tree () {
        size = 1;

        //create the root
        root = new Node(ConstantKey.ROOT_NAME);
        //children of root
        Node lterm = new Node(ConstantKey.COMMITMENT_LONG);
        Node sterm = new Node(ConstantKey.COMMITMENT_SHORT);
        //add root's children
        root.addChild(lterm);
        root.addChild(sterm);

        ArrayList<Node> children = root.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            child.addChild(new Node(ConstantKey.STRAIGHT));
            child.addChild(new Node(ConstantKey.GAY));
            child.addChild(new Node(ConstantKey.BI));

            //Now add Male and Female
            ArrayList<Node> sub_children = child.getChildren();
            for (int j = 0; j < sub_children.size(); j++) {
                Node sub_child = sub_children.get(j);
                sub_child.addChild(new Node(ConstantKey.FEMALE));
                sub_child.addChild(new Node(ConstantKey.MALE));
            }
        }

    }

    /**
     * returns all the potential matches of a specific user
     * @param profile the user
     * @return all the potential matches of a specific user
     */
    public ArrayList<Person> getMatches(Person profile, ArrayList<Person> p)
    {
        ArrayList<Person> returner = new ArrayList<>();
        boolean commit = profile.getStatus();
        String sexuality = profile.getSexuality();
        String gender = profile.getGender();
        MatchRules i = new MatchRules();
        HashMap<String, ArrayList<SearchNode>> rules = i.getRules();


        ArrayList<SearchNode> matchRule = rules.get(sexuality + "_" + gender);

        if (commit == true)
        {
            for (int rI = 0; rI < matchRule.size(); rI++)
            {
                SearchNode type = matchRule.get(rI);
                ArrayList<Person> add = getPoolbyParams(ConstantKey.COMMITMENT_LONG, type.getGender(), type.getSexuality());
                returner.addAll(add);
            }
        }
        else
        {
            for (int rI = 0; rI < matchRule.size(); rI++)
            {
                SearchNode type = matchRule.get(rI);
                ArrayList<Person> add = getPoolbyParams(ConstantKey.COMMITMENT_SHORT, type.getGender(), type.getSexuality());

                returner.addAll(add);
            }
        }
        if (returner.contains(profile)) {
            returner.remove(profile);
        }
        for (Person g : p)
        {
            if (returner.contains(g))
            {
                returner.remove(g);
            }
        }
        return returner;

    }

    /**
     * returns the ArrayList of all other users who match the current user's preference
     * @param commitment the user's commitment preference (long term or short term)
     * @param gender the user's gender
     * @param sexuality the user's sexual orientation
     * @return an ArrayList of the users who match the current user's preference
     */
    public ArrayList<Person> getPoolbyParams(String commitment, String gender, String sexuality)
    {
        Node second = this.root.getNodeByArgument(commitment);
        Node third = second.getNodeByArgument(sexuality);

        Node finals = third.getNodeByArgument(gender);
        return finals.getPeople();
    }

    /**
     * prints all the potential matches for a specific user
     * @param p the user
     * @return all the potential matches for a specific person
     */
    public String printMatches(Person p)
    {
        ArrayList<Person> i = getMatches(p, new ArrayList<Person>());
        String result = "";
        for (Person s : i) {
            result += s.getName();
        }

        return result;
    }

    /**
     * adds a new user to the tree
     * @param p the user that is to be added
     * @return an int that tells whether or not the addition was successful
     */
    public int addPerson(Person p)
    {
        Node commit_node = null;
        Node sexuality_node = null;
        Node gender_node = null;
        boolean status = p.getStatus();
        //person wants longterm relationship
        if (status == true) {
            commit_node = root.getNodeByArgument(ConstantKey.COMMITMENT_LONG);
        } else {
            commit_node = root.getNodeByArgument(ConstantKey.COMMITMENT_SHORT);
        }
        if (commit_node == null) {
            System.out.println("commit doesn't work");
            return -1;
        }
        //get sexuality
        String sexuality = p.getSexuality();
        if (sexuality.equals(ConstantKey.STRAIGHT)) {
            sexuality_node = commit_node.getNodeByArgument(ConstantKey.STRAIGHT);
        } else if (sexuality.equals(ConstantKey.GAY)) {
            sexuality_node = commit_node.getNodeByArgument(ConstantKey.GAY);
        } else if (sexuality.equals(ConstantKey.BI)) {
            sexuality_node = commit_node.getNodeByArgument(ConstantKey.BI);
        }
        if (sexuality_node == null) {
            System.out.println("sexuality doesn't work");
            return -1;
        }
        String gender = p.getGender();
        if (gender.equals(ConstantKey.FEMALE)) {
            gender_node = sexuality_node.getNodeByArgument(ConstantKey.FEMALE);
        } else if (gender.equals(ConstantKey.MALE)) {
            gender_node = sexuality_node.getNodeByArgument(ConstantKey.MALE);
        }
        if (gender_node == null) {
            System.out.println("gender doesn't work:" + gender);
            return -1;
        }
        gender_node.addPerson(p);
        return p.getAge();
    }

    /**
     * returns the size of the Tree (# of people)
     * @return the number of people in the Tree
     */
    public int getSize() {
        return size;
    }
}
