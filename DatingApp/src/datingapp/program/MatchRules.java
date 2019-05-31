package datingapp.program;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * sets up/defines the rules for the matching process
 * @author Laasya
 */
public class MatchRules
{
    static HashMap<String, ArrayList<SearchNode>> rules;

    /**
     *
     */
    public MatchRules()
    {
        rules = new HashMap<>();
        SearchNode sg = new SearchNode(ConstantKey.STRAIGHT, ConstantKey.FEMALE);
        SearchNode bg = new SearchNode(ConstantKey.BI, ConstantKey.FEMALE);
        SearchNode sb = new SearchNode(ConstantKey.STRAIGHT, ConstantKey.MALE);
        SearchNode bb = new SearchNode(ConstantKey.BI, ConstantKey.MALE);
        SearchNode gb = new SearchNode(ConstantKey.GAY, ConstantKey.MALE);
        SearchNode gg = new SearchNode(ConstantKey.GAY, ConstantKey.FEMALE);
        //System.out.println("hi bis");
        //straight boy
        ArrayList<SearchNode> sb_list = new ArrayList<SearchNode>();
        sb_list.add(sg);
        sb_list.add( bg );
        String sb_key = ConstantKey.STRAIGHT + "_" + ConstantKey.MALE;
        rules.put(sb_key, sb_list);
        //straight girl
        ArrayList<SearchNode> sg_list = new ArrayList<SearchNode>();
        sg_list.add(sb);
        sg_list.add(bb);
        String sg_key = ConstantKey.STRAIGHT + "_" + ConstantKey.FEMALE;
        rules.put(sg_key,sg_list );
        //bisexual boy
        ArrayList<SearchNode> bb_list = new ArrayList<SearchNode>();
        bb_list.add(bg);
        bb_list.add(bb);
        bb_list.add(sg);
        bb_list.add(gb);
        String bb_key = ConstantKey.BI + "_" + ConstantKey.MALE;
        rules.put(bb_key,bb_list );
        //bisexual girl
        ArrayList<SearchNode> bg_list = new ArrayList<SearchNode>();
        bg_list.add(bg);
        bg_list.add(bb);
        bg_list.add(sb);
        bg_list.add(gg);
        String bg_key = ConstantKey.BI + "_" + ConstantKey.FEMALE;
        rules.put(bg_key,bg_list );
        //gay girl
        ArrayList<SearchNode> gg_list = new ArrayList<SearchNode>();
        gg_list.add(gg);
        gg_list.add(bg);
        String gg_key = ConstantKey.GAY + "_" + ConstantKey.FEMALE;
        rules.put(gg_key,gg_list );
        //gay boy
        ArrayList<SearchNode> gb_list = new ArrayList<SearchNode>();
        gb_list.add(gb);
        gb_list.add(bb);
        String gb_key = ConstantKey.GAY + "_" + ConstantKey.MALE;
        rules.put(gb_key,gb_list );
    }

    /**
     *
     * @return
     */
    public static HashMap<String, ArrayList<SearchNode>> getRules()
    {
        return rules;
    }

}
