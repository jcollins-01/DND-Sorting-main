/**
 * DNDSort reads input from a file 'characters.txt' with twelve D&D character profiles.
 * It sorts the profiles into a triple-nested Hashmap storing their names, types, stats, and stat levels.
 * The user then inputs which stat they'd like to sort the D&D characters by.
 * Using a PriorityQueue, the program sorts the characters by the chosen stat in ascending order.
 * It outputs them and continues to ask the user for more stats to sort by, until the user ends the loop.
 * 
 * @author Jazmin Collins
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DNDSort
{
    //Triple-nested HashMap that sorts profiles by chara name, first inner HashMap sorts by their type,
    //second inner HashMap sorts by the stat and the stat has an associated num value called stat level.
    //      *Chara name      *Chara type      *Stat   *Stat level      
    public static HashMap <String, HashMap <String, HashMap <String, Integer>>> characters = new HashMap<String, HashMap <String, HashMap <String, Integer>>>();

    public static void main(String[] args) throws FileNotFoundException
    {   
        readValues();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the D&D Character Sorting Program!");
        System.out.println("\nPlease enter the skill you would like to sort the D&D Characters by:\n");
        System.out.println("(Enter 'recursion!' to stop the program)");
        String response = scan.nextLine();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        while(!response.equals("recursion!"))
        {
            sortValues(response, pq);
            pq.clear();
            System.out.println("\nPlease enter the skill you would like to sort the D&D Characters by:\n");
            response = scan.nextLine();
        }
        System.out.println("\nThank you for using the sorting program! We hope you found a character that suits you!");
    }
    /**
     * Takes the file 'characters.txt' and reads in the data from its twelve character profiles.
     * Stores stat and stat level in a HashMap which will be the innermost HashMap overall.
     * Passes character name, type, and the innerHashMap to insertValues().
     * 
     * @throws FileNotFoundException
     */
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
    }//End of readValues()
    /**
     * Takes values passed by readValues() method and inserts them into nested HashMaps accordingly.
     * The value type is used in a new HashMap as a key for tempHashInner holding stats and stat levels.
     * The value name is used as a key in the triple-nested HashMap characters where the other HMs are put.
     * 
     * @param name
     * @param type
     * @param tempHashInner
     */
    public static void insertValues(String name, String type, HashMap <String, Integer> tempHashInner)
    {
        HashMap <String, HashMap<String, Integer>> tempHashOuter = new HashMap <String, HashMap<String, Integer>>();
        //Switch statement that puts the innerHM into different outerHM based on the type
        switch(type)
        {
            case "Barbarian":
            {
                HashMap <String, Integer> barbarian = new HashMap <String, Integer>();
                barbarian.putAll(tempHashInner);
                tempHashOuter.put(type, barbarian);
                break;
            }
            case "Bard":
            {
                HashMap <String, Integer> bard = new HashMap <String, Integer>();
                bard.putAll(tempHashInner);
                tempHashOuter.put(type, bard);
                break;
            }
            case "Cleric":
            {
                HashMap <String, Integer> cleric = new HashMap <String, Integer>();
                cleric.putAll(tempHashInner);
                tempHashOuter.put(type, cleric);
                break;
            }
            case "Druid":
            {
                HashMap <String, Integer> druid = new HashMap <String, Integer>();
                druid.putAll(tempHashInner);
                tempHashOuter.put(type, druid);
                break;
            }
            case "Fighter":
            {
                HashMap <String, Integer> fighter = new HashMap <String, Integer>();
                fighter.putAll(tempHashInner);
                tempHashOuter.put(type, fighter);
                break;
            }
            case "Wizard":
            {
                HashMap <String, Integer> wizard = new HashMap <String, Integer>();
                wizard.putAll(tempHashInner);
                tempHashOuter.put(type, wizard);
                break;
            }
            case "Monk":
            {
                HashMap <String, Integer> monk = new HashMap <String, Integer>();
                monk.putAll(tempHashInner);
                tempHashOuter.put(type, monk);
                break;
            }
            case "Paladin":
            {
                HashMap <String, Integer> paladin = new HashMap <String, Integer>();
                paladin.putAll(tempHashInner);
                tempHashOuter.put(type, paladin);
                break;
            }
            case "Ranger":
            {
                HashMap <String, Integer> ranger = new HashMap <String, Integer>();
                ranger.putAll(tempHashInner);
                tempHashOuter.put(type, ranger);
                break;
            }
            case "Sorcerer":
            {
                HashMap <String, Integer> sorcerer = new HashMap <String, Integer>();
                sorcerer.putAll(tempHashInner);
                tempHashOuter.put(type, sorcerer);
                break;
            }
            case "Thief":
            {
                HashMap <String, Integer> thief = new HashMap <String, Integer>();
                thief.putAll(tempHashInner);
                tempHashOuter.put(type, thief);
                break;
            }
            case "Warlock":
            {
                HashMap <String, Integer> warlock = new HashMap <String, Integer>();
                warlock.putAll(tempHashInner);
                tempHashOuter.put(type, warlock);
                break;
            }
        }//End of switch statement
        characters.put(name, tempHashOuter);
    }//End of insertValues
    /**
     * Takes the user's input for the skill to sort by and finds the corresponding skill in characters.
     * Adds the stat level matching with the skill to the PriorityQueue to sort them in ascending order.
     * Dives back into each level of the characters HM to retrieve character data that corresponds with the
     * now-ordered skills.
     * If (and only if) it finds a match, it will pop the value from the PQ and print it with the other data.
     * In doing so, it prints out the characters and their data in ascending order.
     */
    public static void sortValues(String response, PriorityQueue <Integer> pq)
    {
        switch(response.toLowerCase())
        {
            case "strength":
            {
                pq.add(characters.get("Alf A. Romeo").get("Barbarian").get("Strength"));
                pq.add(characters.get("Chip Munk").get("Bard").get("Strength"));
                pq.add(characters.get("Barry Cade").get("Cleric").get("Strength"));
                pq.add(characters.get("Brock Lee").get("Druid").get("Strength"));
                pq.add(characters.get("Anna Sasin").get("Fighter").get("Strength"));
                pq.add(characters.get("Cam Payne").get("Wizard").get("Strength"));
                pq.add(characters.get("Cara Van").get("Monk").get("Strength"));
                pq.add(characters.get("Casey Macy").get("Paladin").get("Strength"));
                pq.add(characters.get("Claire Annette Reed").get("Ranger").get("Strength"));
                pq.add(characters.get("Anne Teak").get("Sorcerer").get("Strength"));
                pq.add(characters.get("Anna Prentice").get("Thief").get("Strength"));
                pq.add(characters.get("Billy Rubin").get("Warlock").get("Strength"));
                System.out.println("\nThe characters are sorted in ascending order by Strength:\n");
                //Put everything in a while loop based on while pq is not empty.
                //Peek to check the lowest value and if it matches, then we poll and remove elements.
                while(!pq.isEmpty())
                {
                    int comparePQ = pq.peek();
                    //For every Chara name
                    for(String name : characters.keySet())
                    {
                        //For every Chara type under that name (there's only one for each HM)
                        for(String type : characters.get(name).keySet())
                        {   
                            //Get the value at our chosen stat and compare it to the value in pq
                            if(comparePQ == characters.get(name).get(type).get("Strength"))
                                System.out.println(type + " - " + name + " (" + pq.poll() + ")");
                        }
                    }
                }//End of while loop for running through pq
                break;
            }
            case "dexterity":
            {
                pq.add(characters.get("Alf A. Romeo").get("Barbarian").get("Dexterity"));
                pq.add(characters.get("Chip Munk").get("Bard").get("Dexterity"));
                pq.add(characters.get("Barry Cade").get("Cleric").get("Dexterity"));
                pq.add(characters.get("Brock Lee").get("Druid").get("Dexterity"));
                pq.add(characters.get("Anna Sasin").get("Fighter").get("Dexterity"));
                pq.add(characters.get("Cam Payne").get("Wizard").get("Dexterity"));
                pq.add(characters.get("Cara Van").get("Monk").get("Dexterity"));
                pq.add(characters.get("Casey Macy").get("Paladin").get("Dexterity"));
                pq.add(characters.get("Claire Annette Reed").get("Ranger").get("Dexterity"));
                pq.add(characters.get("Anne Teak").get("Sorcerer").get("Dexterity"));
                pq.add(characters.get("Anna Prentice").get("Thief").get("Dexterity"));
                pq.add(characters.get("Billy Rubin").get("Warlock").get("Dexterity"));
                System.out.println("\nThe characters are sorted in ascending order by Dexterity:\n");

                while(!pq.isEmpty())
                {
                    int comparePQ = pq.peek();
                    for(String name : characters.keySet())
                    {
                        for(String type : characters.get(name).keySet())
                        {   
                            if(comparePQ == characters.get(name).get(type).get("Dexterity"))
                                System.out.println(type + " - " + name + " (" + pq.poll() + ")");
                        }
                    }
                }//End of while loop
                break;
            }
            case "constitution":
            {
                pq.add(characters.get("Alf A. Romeo").get("Barbarian").get("Constitution"));
                pq.add(characters.get("Chip Munk").get("Bard").get("Constitution"));
                pq.add(characters.get("Barry Cade").get("Cleric").get("Constitution"));
                pq.add(characters.get("Brock Lee").get("Druid").get("Constitution"));
                pq.add(characters.get("Anna Sasin").get("Fighter").get("Constitution"));
                pq.add(characters.get("Cam Payne").get("Wizard").get("Constitution"));
                pq.add(characters.get("Cara Van").get("Monk").get("Constitution"));
                pq.add(characters.get("Casey Macy").get("Paladin").get("Constitution"));
                pq.add(characters.get("Claire Annette Reed").get("Ranger").get("Constitution"));
                pq.add(characters.get("Anne Teak").get("Sorcerer").get("Constitution"));
                pq.add(characters.get("Anna Prentice").get("Thief").get("Constitution"));
                pq.add(characters.get("Billy Rubin").get("Warlock").get("Constitution"));
                System.out.println("\nThe characters are sorted in ascending order by Constitution:\n");

                while(!pq.isEmpty())
                {
                    int comparePQ = pq.peek();
                    for(String name : characters.keySet())
                    {
                        for(String type : characters.get(name).keySet())
                        {   
                            if(comparePQ == characters.get(name).get(type).get("Constitution"))
                                System.out.println(type + " - " + name + " (" + pq.poll() + ")");
                        }
                    }
                }//End of while loop
                break;
            }
            case "intelligence":
            {
                pq.add(characters.get("Alf A. Romeo").get("Barbarian").get("Intelligence"));
                pq.add(characters.get("Chip Munk").get("Bard").get("Intelligence"));
                pq.add(characters.get("Barry Cade").get("Cleric").get("Intelligence"));
                pq.add(characters.get("Brock Lee").get("Druid").get("Intelligence"));
                pq.add(characters.get("Anna Sasin").get("Fighter").get("Intelligence"));
                pq.add(characters.get("Cam Payne").get("Wizard").get("Intelligence"));
                pq.add(characters.get("Cara Van").get("Monk").get("Intelligence"));
                pq.add(characters.get("Casey Macy").get("Paladin").get("Intelligence"));
                pq.add(characters.get("Claire Annette Reed").get("Ranger").get("Intelligence"));
                pq.add(characters.get("Anne Teak").get("Sorcerer").get("Intelligence"));
                pq.add(characters.get("Anna Prentice").get("Thief").get("Intelligence"));
                pq.add(characters.get("Billy Rubin").get("Warlock").get("Intelligence"));
                System.out.println("\nThe characters are sorted in ascending order by Intelligence:\n");

                while(!pq.isEmpty())
                {
                    int comparePQ = pq.peek();
                    for(String name : characters.keySet())
                    {
                        for(String type : characters.get(name).keySet())
                        {   
                            if(comparePQ == characters.get(name).get(type).get("Intelligence"))
                                System.out.println(type + " - " + name + " (" + pq.poll() + ")");
                        }
                    }
                }//End of while loop
                break;
            }
            case "wisdom":
            {
                pq.add(characters.get("Alf A. Romeo").get("Barbarian").get("Wisdom"));
                pq.add(characters.get("Chip Munk").get("Bard").get("Wisdom"));
                pq.add(characters.get("Barry Cade").get("Cleric").get("Wisdom"));
                pq.add(characters.get("Brock Lee").get("Druid").get("Wisdom"));
                pq.add(characters.get("Anna Sasin").get("Fighter").get("Wisdom"));
                pq.add(characters.get("Cam Payne").get("Wizard").get("Wisdom"));
                pq.add(characters.get("Cara Van").get("Monk").get("Wisdom"));
                pq.add(characters.get("Casey Macy").get("Paladin").get("Wisdom"));
                pq.add(characters.get("Claire Annette Reed").get("Ranger").get("Wisdom"));
                pq.add(characters.get("Anne Teak").get("Sorcerer").get("Wisdom"));
                pq.add(characters.get("Anna Prentice").get("Thief").get("Wisdom"));
                pq.add(characters.get("Billy Rubin").get("Warlock").get("Wisdom"));
                System.out.println("\nThe characters are sorted in ascending order by Wisdom:\n");

                while(!pq.isEmpty())
                {
                    int comparePQ = pq.peek();
                    for(String name : characters.keySet())
                    {
                        for(String type : characters.get(name).keySet())
                        {   
                            if(comparePQ == characters.get(name).get(type).get("Wisdom"))
                                System.out.println(type + " - " + name + " (" + pq.poll() + ")");
                        }
                    }
                }//End of while loop
                break;
            }
            case "charisma":
            {
                pq.add(characters.get("Alf A. Romeo").get("Barbarian").get("Charisma"));
                pq.add(characters.get("Chip Munk").get("Bard").get("Charisma"));
                pq.add(characters.get("Barry Cade").get("Cleric").get("Charisma"));
                pq.add(characters.get("Brock Lee").get("Druid").get("Charisma"));
                pq.add(characters.get("Anna Sasin").get("Fighter").get("Charisma"));
                pq.add(characters.get("Cam Payne").get("Wizard").get("Charisma"));
                pq.add(characters.get("Cara Van").get("Monk").get("Charisma"));
                pq.add(characters.get("Casey Macy").get("Paladin").get("Charisma"));
                pq.add(characters.get("Claire Annette Reed").get("Ranger").get("Charisma"));
                pq.add(characters.get("Anne Teak").get("Sorcerer").get("Charisma"));
                pq.add(characters.get("Anna Prentice").get("Thief").get("Charisma"));
                pq.add(characters.get("Billy Rubin").get("Warlock").get("Charisma"));
                System.out.println("\nThe characters are sorted in ascending order by Charisma:\n");

                while(!pq.isEmpty())
                {
                    int comparePQ = pq.peek();
                    for(String name : characters.keySet())
                    {
                        for(String type : characters.get(name).keySet())
                        {   
                            if(comparePQ == characters.get(name).get(type).get("Charisma"))
                                System.out.println(type + " - " + name + " (" + pq.poll() + ")");
                        }
                    }
                }//End of while loop
                break;
            }
            default:
            {
                System.out.println("I'm sorry - that's not a valid statistic for these characters.");
                System.out.println("Your options are: Strength, Dexterity, Consitution, Intelligence, Wisdom, and Charisma.");
                System.out.println("And remember, enter 'recursion!' to stop the program.");
            }
        }//End switch statement
    }//End sortValues
}