import java.io.File;
import java.util.ArrayList;

/**
 * 
 * ScrabbleHelper class: The main program that parses the command line arguments, creates the Dictionary and
 * Permutation objects, and uses those objects to run queries.
 * 
 * @author Rebecca Hom
 * @since March 2, 2017
 * 
 *
 */
public class ScrabbleHelper {
	
	/**
	 * A method that reports the number of words and prints all of the words to the console.
	 * 
	 */
	public static void printAllWords(ArrayList<String> words){
		if (words.size() > 0){
			System.out.println("Found " + words.size() + " words");
			for (int i = 0; i<words.size();i++){
				System.out.println(words.get(i));
			}
		}
		else{
			System.out.println("No words found");
		}
	}
	public static void main(String[] args){
		//Validate the command line arguments
		if (args.length < 2){
			System.err.println("Not enough arguments");
		}
		
		else{
			try{	
				//Create the File object, Dictionary object, and Permutations object
				File newFile = new File(args[0]);
				Dictionary myDictionary = new Dictionary(newFile);
				Permutations obj1 = new Permutations(args[1]);
				
				//See if the file exists
				if (newFile.exists() == false){
					throw new IllegalArgumentException(args[0] +" does not exist");
				}
				
				//See if the Permutations has a valid letter sequence; no letters, special characters, or
				//white spaces
				for (int i = 0; i <args[1].length();i++){
					if (Character.isLetter(args[1].charAt(i)) == false){
						throw new IllegalArgumentException("Sequence can only contain letters");
					}
				}
						
				printAllWords(obj1.getAllWords(myDictionary));
				
			}
			
			catch (IllegalArgumentException error){
			}	
		}
	}
}
