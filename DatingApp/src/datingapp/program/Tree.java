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

    /*
    public void print_tree() {
        ArrayList<Node> children = root.getChildren();
        System.out.println(root.getQuestion());
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            System.out.println("\t" + child.getQuestion());
            //Now add Male and Female
            ArrayList<Node> sub_children = child.getChildren();
            for (int j = 0; j < sub_children.size(); j++) {
                Node sub_child = sub_children.get(j);
                System.out.println("\t\t" + sub_child.getQuestion());
                //male andfemale
                for (int k = 0; k < sub_child.getChildren().size(); k++) {
                    Node sub_sub_child = sub_child.getChildren().get(k);
                    System.out.println("\t\t\t" + sub_sub_child.getQuestion());
                    ArrayList<Person> list = sub_sub_child.getPeople();
                    for (Person a : list) {
                        System.out.println("\t\t\t\t" + a.getName());
                    }
                }
            }
        }
    }
    */


    /*
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
    */
    /*
    public static void main(String[] args)
    {
        Tree newT = new Tree();
        Person test = new Person("Jane Doe", 18, ConstantKey.FEMALE, ConstantKey.STRAIGHT, "jane.doe@gmail.com", "1234", false, "hello", null);
        Person test2 = new Person("Bob Billy", 29, ConstantKey.MALE, ConstantKey.STRAIGHT, "bob.billy@gmail.com", "1234", false, "hello", null);
        Person test3 = new Person("Donald Trump", 70, ConstantKey.MALE, ConstantKey.STRAIGHT, "donald.trump@gmail.com", "1234", false, "hello", null);
        Person test4 = new Person("JoJo Siwa", 54, ConstantKey.FEMALE, ConstantKey.BI, "jo.siwa@gmail.com", "1234", false, "hello", null);
        Person test5 = new Person("Justin Bieber", 68, ConstantKey.MALE, ConstantKey.GAY, "justin.bieber@gmail.com", "1234", false, "hello", null);
        int size = newT.getSize();
        newT.addPerson(test);
        newT.addPerson(test2);
        newT.addPerson(test3);
        newT.addPerson(test4);
        newT.addPerson(test5);
        //System.out.println(age);
        //newT.print_tree();
        System.out.println(newT.printMatches(test2));

    }
     */

    /**
     * returns all the potential matches of a specific user
     * @param profile the user
     * @return all the potential matches of a specific user
     */
    public ArrayList<Person> getMatches(Person profile)
    {
        ArrayList<Person> returner = new ArrayList<Person>();
        boolean commit = profile.getStatus();
        String sexuality = profile.getSexuality();
        String gender = profile.getGender();
        //MatchRules.InitRules();
        MatchRules i = new MatchRules();
        HashMap<String, ArrayList<SearchNode>> rules = i.getRules();


        ArrayList<SearchNode> matchRule = rules.get(sexuality + "_" + gender);

        if (commit) {

            for (int rI = 0; rI < matchRule.size(); rI++) {
                SearchNode type = matchRule.get(rI);
                //System.out.println(type.getGender() + type.getSexuality());
                ArrayList<Person> add = getPoolbyParams(ConstantKey.COMMITMENT_LONG, type.getGender(), type.getSexuality());
                returner.addAll(add);
            }
        } else {

            for (int rI = 0; rI < matchRule.size(); rI++) {
                SearchNode type = matchRule.get(rI);
                //System.out.println(type.getGender() + type.getSexuality());
                ArrayList<Person> add = getPoolbyParams(ConstantKey.COMMITMENT_SHORT, type.getGender(), type.getSexuality());
                //System.out.println(add.size());

                returner.addAll(add);
            }
        }
        /*
        AccountService as = new AccountService();
        for (Person p: returner) {
            as.addMatch(profile, p);
        }
        */
        if (returner.contains(profile)) {
            returner.remove(profile);
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
        //System.out.println(finals.getPeople());
        return finals.getPeople();
    }

    /**
     * prints all the potential matches for a specific user
     * @param p the user
     * @return all the potential matches for a specific person
     */
    public String printMatches(Person p)
    {
        ArrayList<Person> i = getMatches(p);
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
