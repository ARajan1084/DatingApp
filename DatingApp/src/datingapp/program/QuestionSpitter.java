package datingapp.program;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 *  Returns a randomized list of questions of each type.
 *  Chooses a random type of question from the text file and returns an array
 *  consisting of questions to be displayed in messages
 *
 *  @author  Laasya
 *  @version May 23, 2019
 */
public class QuestionSpitter
{
    public static String[] starters;
    public static String[] getKnow;
    public static String[] wdy;
    public static String[] fun;
    public static String[] random;
    public static String[] deeps;
    public static String[] finalList;

    /**
     * initializes each area that stores all the questions of each type in each
     * specific array
     */
    public static void initString()
    {
        starters = new String[16];
        getKnow = new String[13];
        wdy = new String[12];
        fun = new String[12];
        random = new String[18];
        finalList = new String[6];
        deeps = new String[18];
    }
    /**
     * Returns a randomized list of questions, one of each category
     * @return finalList the randomized list
     */
    public static String[] getRandomList()
    {
        int index1 = (int)(Math.random() * starters.length);
        int index2 = (int)(Math.random() * getKnow.length);
        int index3 = (int)(Math.random() * wdy.length);
        int index4 = (int)(Math.random() * random.length);
        int index5 = (int)(Math.random() * deeps.length);
        int index6 = (int)(Math.random() * fun.length);
        finalList[0] = starters[index1];
        finalList[1] = getKnow[index2];
        finalList[2] = wdy[index3];
        finalList[3] = random[index4];
        finalList[4] = deeps[index5];
        finalList[5] = fun[index6];
        return finalList;
    }

    /**
     * reads the text file and adds questions to their corresponding category
     * @param args default parameter
     * @throws IOException in case of issues when converting the profile picture to a Blob in the DATABASE
     */
    public static void main(String[] args) throws IOException
    {

        //questionSpitter x = new questionSpitter();
        QuestionSpitter.initString();
        Scanner inFile1 = new Scanner(new File("/Users/Laasya/Dropbox/workspaceAPCS/DatingApp-master/DatingApp/src/datingapp/program/qq.txt"));
        ///Users/Laasya/Dropbox/workspaceAPCS/DatingApp-master/DatingApp/src/datingapp/program/qq.txt
        String question = inFile1.nextLine().trim();
        int count = 0;
        while (inFile1.hasNextLine() && !(question.equals("get to know")))
        {
            //System.out.println(question);
            if (!(question.equals("starters")))
            {



                QuestionSpitter.starters[count] = question;

                count++;
            }
            question = inFile1.nextLine().trim();
        }
        count = 0;
        while (inFile1.hasNextLine() && !(question.equals("would you rather")))
        {
            if (!(question.equals("get to know")))
            {
                getKnow[count] = question;
                count++;
            }
            question = inFile1.nextLine().trim();
        }
        count = 0;
        while (inFile1.hasNextLine() && !(question.equals("random")))
        {
            if (!(question.equals("would you rather")))
            {
                wdy[count] = question;
                count++;
            }
            question = inFile1.nextLine().trim();
        }
        count = 0;
        while (inFile1.hasNextLine() && !(question.equals("deep")))
        {
            if (!(question.equals("random")))
            {
                deeps[count] = question;
                count++;
            }
            question = inFile1.nextLine().trim();
        }
        count = 0;
        while (inFile1.hasNextLine() && !(question.equals("fun")))
        {
            if (!(question.equals("deep")))
            {
                //System.out.println(count + " " + question);
                random[count] = question;
                count++;
            }
            question = inFile1.nextLine().trim();
        }
        count = 0;
        while (inFile1.hasNextLine())
        {
            if (!(question.equals("fun")))
            {
                fun[count] = question;
                count++;
            }
            question = inFile1.nextLine().trim();
        }
        //String[] roo = getRandomList();

    }

}
