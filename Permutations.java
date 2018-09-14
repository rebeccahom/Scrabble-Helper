import java.util.ArrayList;
/**
 * Permutations class: Contains the sequence of letters from which words should be constructed; this class
 * constructs all the words and permutations possible from the given letter sequence. It uses a Dictionary
 * object to perform its tasks.
 * 
 * @author Rebecca Hom
 * @since March 2, 2017 
 */


class Permutations {
	private String letters;
	private ArrayList<String> permutations = new ArrayList<String>();
	private ArrayList<String> words = new ArrayList<String>();
	
	
	/**
	 * Creates a Permutations object with the data field letters
	 * 
	 * @param letters The sequence of letters that the Permutation object works with
	 * @throws IllegalArgumentException If the supplied argument contains characters that are not letters
	 */
	public Permutations(String letters) throws IllegalArgumentException{
		try{
		String cleanedString = "";
		//Check if every character is a letter; if not, throw an illegal argument exception
		for (int i = 0; i < letters.length(); i++){
				if (Character.isLetter(letters.charAt(i))== false){
					throw new IllegalArgumentException();
				}
				else if (Character.isUpperCase(letters.charAt(i))){
					cleanedString += Character.toLowerCase(letters.charAt(i));
				}
				else{
					cleanedString += letters.charAt(i);
				}
		}
		
		this.letters = cleanedString;
		}
		catch (IllegalArgumentException error){
			System.err.println("Sequence can only contain letters");
		}
	}
	
	/**
	 * Computes all possible permutations of the sequence of letters stored in the Permutations object
	 * It calls another method, permutationBuilder, to generate all permutations recursively 
	 * @return ArrayList<String> containing all permutations of the sequence of letters
	 */
	public ArrayList<String> getAllPermutations(){
		permutationBuilder(letters,"");
		return permutations;
	}
	
	/**
	 * Given a sequence of letters, this method creates all permutations of those letters. It builds strings
	 * recursively and adds each permutation to the permutation ArrayList stored in the Permutations object.
	 * 
	 * @param letters The sequence of letters from which the permutations are generated
	 * @param permutation The permutation of the sequence of letters
	 */
	private void permutationBuilder(String letters,String permutation){
		//Base case: If the permutation has all of the characters from the letter sequence, stop the recursive call
		if (permutation.length() == this.letters.length()){ 
			permutations.add(permutation);
		}
		else{
			//Cycle through all of the letters 
			for (int i = 0;i<letters.length();i++){
				//Add on the letter to the permutation
				String builder = permutation + letters.charAt(i);
				
				//Cycle through the letters - the letters.substring(0,i) iterates through the 
				//first half of the sequence of strings; letters.substring(i+1,letters.length()) iterates
				//through the second half of the sequence of strings
				String cycledLetters = letters.substring(0,i) + letters.substring(i+1, letters.length());
				
				//Make recursive calls to build permutations
				permutationBuilder(cycledLetters,builder);
			}
		}
	}

	/**
	 * Checks a permutation of the sequence of letters in the Permutations object is a word in a given 
	 * dictionary object. It uses another method, wordBuilder, to generate all possible words recursively
	 * @param dictionary A Dictionary object that contains the dictionary that the Permutations object checks
	 * to see if a given permutation is a word
	 * 
	 * @return An ArrayList containing all of the permutations that are words
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary){
		wordBuilder(letters,"",dictionary); //Call the recursive function to fill the ArrayList
		return words;
		
	}
	
	/**
	 * A recursive method called by another method, getAllWords, to generate the different possible
	 * words from the permutations of a sequence of letters
	 * @param letters The sequence of letters from which the permutations are generated
	 * @param word The permutation that is a word in the Dictionary object
	 * @param dictionary The Dictionary object that contains a set of words
	 */
	private void wordBuilder(String letters,String word,Dictionary dictionary){
		//Base case: If the word has all of the letters in the letter sequence, add it to the list
		//(if it isn't already in it)
		if (word.length() == this.letters.length()){ 
			if (!words.contains(word)){
				words.add(word);
			}
		}
		else{
			//Generate the word
			for (int i = 0;i<letters.length();i++){
				String builder = word + letters.charAt(i);
				//If the built string is not a prefix, discard it and move on to the next iteration
				if (dictionary.isPrefix(builder)==false){
					continue;
				}
				else{
					//Cycle through the letters and recursively add them to the string
					String cycledLetters = letters.substring(0,i) + letters.substring(i+1, letters.length());
					wordBuilder(cycledLetters,builder,dictionary);
				}
			}
		}
	}

}
