import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DNDSort
{
    //One big HashMap that sorts them by chara name, first inner HashMap sorts by their type,
    //second inner HashMap sorts by the stat and the stat has an associated num value
    //      *Chara name      *Chara type      *Stat   *Stat level      
    public static HashMap <String, HashMap <String, HashMap <String, Integer>>> characters = new HashMap<String, HashMap <String, HashMap <String, Integer>>>();
    //public static HashMap <String, Integer> tempHashInner = new HashMap <String, Integer>();
    //public static HashMap <String, HashMap<String, Integer>> tempHashOuter = new HashMap <String, HashMap<String, Integer>>();
    
    public static void main(String[] args) throws FileNotFoundException
    {   
        readValues();
        System.out.println(characters.toString());
    }

    public static void readValues() throws FileNotFoundException
    {
        File file = new File("DND-Sorting-main\\characters.txt"); 
        Scanner scan = new Scanner(file); 

        //One big HashMap that sorts them by chara name, first inner HashMap sorts by their type,
        //second inner HashMap sorts by the stat and the stat has an associated num value
        //      *Chara name      *Chara type      *Stat   *Stat level      
        //HashMap <String, HashMap <String, HashMap <String, Integer>>> characters = new HashMap<String, HashMap <String, HashMap <String, Integer>>>();
        
        //Values to place in our HashMap
        String charaName = "";
        String charaType = "";
        String stat = "";
        Integer statLevel = null;
        //Used to test while reading our file so we find the right info for each above value
        char testChar;
        char testNextChar;
        String nullStatString = stat + " (" + statLevel + ")";
        HashMap <String, Integer> tempHashInner = new HashMap <String, Integer>();

        while (scan.hasNextLine())
        {
            //Reads a whole line at a time so we can operate on each line as we read
            String line = scan.nextLine();
            //Catch if-statement in case we read in a blank line
            if(line.length() == 0)
                continue;
            
            for(int i = 0; i < line.length(); i++)
            {
                testChar = line.charAt(i);
                if(i != line.length() - 1)
                    testNextChar = line.charAt(i + 1);
                else
                    testNextChar = '%';

                if(testChar == ' ' && testNextChar == '-')
                    charaType = line.substring(0, i);
                else if(testChar == '-' && testNextChar == ' ')
                {
                    charaName = line.substring(i + 2, line.length());
                    line = scan.nextLine();
                }
                else if(testChar == ':')
                {
                    stat = line.substring(0, i);
                    statLevel = Integer.parseInt(line.substring(i + 1, line.length()));
                    tempHashInner.put(stat, statLevel);
                    break;
                }
                //Checks if our stats come out null or with values, then forces another iteration if null
                String statString = stat + " (" + statLevel + ")";
                if(statString.equals(nullStatString))
                {
                    for(int j = 0; j < line.length(); j++)
                    {
                        char testChar2 = line.charAt(j);
                        if(testChar2 == ':')
                        {
                            stat = line.substring(0, j);
                            statLevel = Integer.parseInt(line.substring(j + 1, line.length()));
                            tempHashInner.put(stat, statLevel);
                            break;
                        }
                    }
                }
            } //End of for-loop
            //System.out.println(charaName + ", " + charaType + " : " + stat + " (" + statLevel + ")");
            //Call a method to place the found values for each chara in our HashMap
            insertValues(charaName, charaType, tempHashInner);
        } //End of while loop
    }
    //      *Chara name      *Chara type      *Stat   *Stat level
    //HashMap <String, HashMap <String, HashMap <String, Integer>>> characters
    public static void insertValues(String name, String type, HashMap <String, Integer> tempHashInner)
    {
        //HashMap <String, Integer> tempHashInner = new HashMap <String, Integer>();
        HashMap <String, HashMap<String, Integer>> tempHashOuter = new HashMap <String, HashMap<String, Integer>>();
        
        //Copy data from init to temp, clear init, temp will be overwritten each time init comes back with
        //new info, change argument variable name to initHashInner

        //Some kind of loop that for each specific type value, it adds the new tempHashInner?


        //tempHashInner.put(stat, level);
        tempHashOuter.put(type, tempHashInner);
        characters.put(name, tempHashOuter);

    }
}