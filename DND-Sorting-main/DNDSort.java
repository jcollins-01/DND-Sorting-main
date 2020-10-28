import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DNDSort {
    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("characters.txt"); 
        Scanner scan = new Scanner(file); 

        //One big HashMap that sorts them by chara name, first inner HashMap sorts by their type,
        //second inner HashMap sorts by the stat and the stat has an associated num value
        //      *Chara name      *Chara type      *Stat   *Stat level      
        HashMap <String, HashMap <String, HashMap <String, Integer>>> characters = new HashMap<String, HashMap <String, HashMap <String, Integer>>>();
        
        //Values to place in our HashMap
        String charaName = "";
        String charaType = "";
        String stat = "";
        Integer statLevel = null;
        //Used to test while reading our file so we find the right info for each above value
        char testChar;
        char testNextChar;

        //We have to read in the information and put it in the put statement effectively
        //barbarian.put("");
        while (scan.hasNextLine())
        {
            //Reads a whole line at a time so we can operate on each line as we read
            String line = scan.nextLine();
            //Catch if-statement in case we read in a blank line
            if(line.length() == 0)
                continue;
            
            for(int i = 0; i <line.length(); i++)
            {
                testChar = line.charAt(i);
                testNextChar = line.charAt(i + 1);

                if(testChar == ' ' && testNextChar == '-')
                    charaType = line.substring(0, i - 1);
                else if(testChar == '-' && testNextChar == ' ')
                    charaName = line.substring(i + 2, line.length() - 1);
                else if(testChar == ':')
                {
                    stat = line.substring(0, i - 1);
                    statLevel = Integer.parseInt(line.substring(i + 1, line.length() - 1));
                }
                else
                    continue;
            } //End of for-loop
            System.out.println(charaName + ", " + charaType + " : " + stat + " (" + statLevel + ")");
        } //End of while loop

    }
}