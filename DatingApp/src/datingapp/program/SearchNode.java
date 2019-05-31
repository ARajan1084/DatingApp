package datingapp.program;

/**
 * a helper class for MatchRules
 * added to a HashMap that tells you who to match up each person with
 * @author Laasya
 */
public class SearchNode
{
    private String sexuality;
    private String gender;

    /**
     * constructs a search node and initializes the user;s gender and sexual orientation
     * @param s
     * @param g
     */
    public SearchNode(String s, String g)
    {
        sexuality = s;
        gender = g;
    }

    /**
     * returns the user's sexual orientation
     * @return the user's sexual orientation
     */
    public String getSexuality()
    {
        return sexuality;
    }

    /**
     * returns the user's gender
     * @return the user's gender
     */
    public String getGender()
    {
        return gender;
    }

}
