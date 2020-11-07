import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DNDSort
{
    //One big HashMap that sorts them by chara name, first inner HashMap sorts by their type,
    //second inner HashMap sorts by the stat and the stat has an associated num value
    //      *Chara name      *Chara type      *Stat   *Stat level      
    public static HashMap <String, HashMap <String, HashMap <String, Integer>>> characters = new HashMap<String, HashMap <String, HashMap <String, Integer>>>();
    
    public static void main(String[] args) throws FileNotFoundException
    {   
        readValues();
        //System.out.println(characters.toString());
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the skill you would like to sort the D&D Characters by:");
        String response = scan.nextLine();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        //while(!response.equals("recursion!"))
        //{
            addToPQ(response, pq);
           // pq.clear();
        //}
        System.out.println("Thanks for testing it out!");
    }

    public static void readValues() throws FileNotFoundException
    {
        File file = new File("DND-Sorting-main\\characters.txt"); 
        Scanner scan = new Scanner(file);
        
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
                //Fixes a recurring error of i not resetting to 0 at lines with stats and statLevels
                else
                {
                    testNextChar = '%';
                    i = line.indexOf(':');
                    testChar = line.charAt(i);
                }

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
            //Call a method to place the found values for each character in our HashMap
            insertValues(charaName, charaType, tempHashInner);
        } //End of while loop
    }

    public static void insertValues(String name, String type, HashMap <String, Integer> tempHashInner)
    {
        HashMap <String, HashMap<String, Integer>> tempHashOuter = new HashMap <String, HashMap<String, Integer>>();
        HashMap <String, Integer> barbarian = new HashMap <String, Integer>();
        HashMap <String, Integer> bard = new HashMap <String, Integer>();
        HashMap <String, Integer> cleric = new HashMap <String, Integer>();
        HashMap <String, Integer> druid = new HashMap <String, Integer>();
        HashMap <String, Integer> fighter = new HashMap <String, Integer>();
        HashMap <String, Integer> wizard = new HashMap <String, Integer>();
        HashMap <String, Integer> monk = new HashMap <String, Integer>();
        HashMap <String, Integer> paladin = new HashMap <String, Integer>();
        HashMap <String, Integer> ranger = new HashMap <String, Integer>();
        HashMap <String, Integer> sorcerer = new HashMap <String, Integer>();
        HashMap <String, Integer> thief = new HashMap <String, Integer>();
        HashMap <String, Integer> warlock = new HashMap <String, Integer>();
        //Switch statement that puts the innerHM into different outerHM based on the type
        switch(type)
        {
            case "Barbarian":
            {
                barbarian.putAll(tempHashInner);
                tempHashOuter.put(type, barbarian);
                break;
            }
            case "Bard":
            {
                bard.putAll(tempHashInner);
                tempHashOuter.put(type, bard);
                break;
            }
            case "Cleric":
            {
                cleric.putAll(tempHashInner);
                tempHashOuter.put(type, cleric);
                break;
            }
            case "Druid":
            {
                druid.putAll(tempHashInner);
                tempHashOuter.put(type, druid);
                break;
            }
            case "Fighter":
            {
                fighter.putAll(tempHashInner);
                tempHashOuter.put(type, fighter);
                break;
            }
            case "Wizard":
            {
                wizard.putAll(tempHashInner);
                tempHashOuter.put(type, wizard);
                break;
            }
            case "Monk":
            {
                monk.putAll(tempHashInner);
                tempHashOuter.put(type, monk);
                break;
            }
            case "Paladin":
            {
                paladin.putAll(tempHashInner);
                tempHashOuter.put(type, paladin);
                break;
            }
            case "Ranger":
            {
                ranger.putAll(tempHashInner);
                tempHashOuter.put(type, ranger);
                break;
            }
            case "Sorcerer":
            {
                sorcerer.putAll(tempHashInner);
                tempHashOuter.put(type, sorcerer);
                break;
            }
            case "Thief":
            {
                thief.putAll(tempHashInner);
                tempHashOuter.put(type, thief);
                break;
            }
            case "Warlock":
            {
                warlock.putAll(tempHashInner);
                tempHashOuter.put(type, warlock);
                break;
            }
        }//End of switch statement
        characters.put(name, tempHashOuter);
    }//End of insertValues

    public static void addToPQ(String response, PriorityQueue <Integer> pq)
    {
        switch(response.toLowerCase())
        {
            case "strength":
            {
                pq.add(characters.get("Alf A. Romeo").get("Barbarian").get("Strength"));
                //pq.add(barbarian.get("Strength"));
                pq.add(characters.get("Chip Munk").get("Bard").get("Strength"));
                //pq.add(bard.get("Strength"));
                pq.add(characters.get("Barry Cade").get("Cleric").get("Strength"));
                //pq.add(cleric.get("Strength"));
                pq.add(characters.get("Brock Lee").get("Druid").get("Strength"));
                //pq.add(druid.get("Strength"));
                pq.add(characters.get("Anna Sasin").get("Fighter").get("Strength"));
                //pq.add(fighter.get("Strength"));
                pq.add(characters.get("Cam Payne").get("Wizard").get("Strength"));
                //pq.add(wizard.get("Strength"));
                pq.add(characters.get("Cara Van").get("Monk").get("Strength"));
                //pq.add(monk.get("Strength"));
                pq.add(characters.get("Casey Macy").get("Paladin").get("Strength"));
                //pq.add(paladin.get("Strength"));
                pq.add(characters.get("Claire Annette Reed").get("Ranger").get("Strength"));
                //pq.add(ranger.get("Strength"));
                pq.add(characters.get("Anne Teak").get("Sorcerer").get("Strength"));
                //pq.add(sorcerer.get("Strength"));
                pq.add(characters.get("Anna Prentice").get("Thief").get("Strength"));
                //pq.add(thief.get("Strength"));
                pq.add(characters.get("Billy Rubin").get("Warlock").get("Strength"));
                //pq.add(warlock.get("Strength"));

                //put everything here in a while loop for while pq is not empty because we peek
                //to check the strongest value and if it matches, then we poll
                while(!pq.isEmpty())
                {
                    int comparePQ = pq.peek();
                    //For every Chara name
                    for(String name : characters.keySet())
                    {
                        //For every Chara type under that name (there's only one for each)
                        for(String type : characters.get(name).keySet())
                        {   
                            //Get the value at our Strength stat and compare it to the value in pq
                            if(comparePQ == characters.get(name).get(type).get("Strength"))
                                System.out.println(type + " - " + name + "(" + pq.poll() + ")");
                        }
                    }
                }//End of while loop for running through pq
            }
            /*case "dexterity":
            {
                pq.add(barbarian.get("Dexterity"));
                pq.add(bard.get("Dexterity"));
                pq.add(cleric.get("Dexterity"));
                pq.add(druid.get("Dexterity"));
                pq.add(fighter.get("Dexterity"));
                pq.add(wizard.get("Dexterity"));
                pq.add(monk.get("Dexterity"));
                pq.add(paladin.get("Dexterity"));
                pq.add(ranger.get("Dexterity"));
                pq.add(sorcerer.get("Dexterity"));
                pq.add(thief.get("Dexterity"));
                pq.add(warlock.get("Dexterity"));
            }
            case "constitution":
            {
                pq.add(barbarian.get("Consitution"));
                pq.add(bard.get("Consitution"));
                pq.add(cleric.get("Consitution"));
                pq.add(druid.get("Consitution"));
                pq.add(fighter.get("Consitution"));
                pq.add(wizard.get("Consitution"));
                pq.add(monk.get("Consitution"));
                pq.add(paladin.get("Consitution"));
                pq.add(ranger.get("Consitution"));
                pq.add(sorcerer.get("Consitution"));
                pq.add(thief.get("Consitution"));
                pq.add(warlock.get("Consitution"));
            }
            case "intelligence":
            {
                pq.add(barbarian.get("Intelligence"));
                pq.add(bard.get("Intelligence"));
                pq.add(cleric.get("Intelligence"));
                pq.add(druid.get("Intelligence"));
                pq.add(fighter.get("Intelligence"));
                pq.add(wizard.get("Intelligence"));
                pq.add(monk.get("Intelligence"));
                pq.add(paladin.get("Intelligence"));
                pq.add(ranger.get("Intelligence"));
                pq.add(sorcerer.get("Intelligence"));
                pq.add(thief.get("Intelligence"));
                pq.add(warlock.get("Intelligence"));
            }
            case "wisdom":
            {
                pq.add(barbarian.get("Wisdom"));
                pq.add(bard.get("Wisdom"));
                pq.add(cleric.get("Wisdom"));
                pq.add(druid.get("Wisdom"));
                pq.add(fighter.get("Wisdom"));
                pq.add(wizard.get("Wisdom"));
                pq.add(monk.get("Wisdom"));
                pq.add(paladin.get("Wisdom"));
                pq.add(ranger.get("Wisdom"));
                pq.add(sorcerer.get("Wisdom"));
                pq.add(thief.get("Wisdom"));
                pq.add(warlock.get("Wisdom"));
            }
            case "charisma":
            {
                pq.add(barbarian.get("Charisma"));
                pq.add(bard.get("Charisma"));
                pq.add(cleric.get("Charisma"));
                pq.add(druid.get("Charisma"));
                pq.add(fighter.get("Charisma"));
                pq.add(wizard.get("Charisma"));
                pq.add(monk.get("Charisma"));
                pq.add(paladin.get("Charisma"));
                pq.add(ranger.get("Charisma"));
                pq.add(sorcerer.get("Charisma"));
                pq.add(thief.get("Charisma"));
                pq.add(warlock.get("Charisma"));
            }*/
        }//End switch statement
    }//End addToPQ
}