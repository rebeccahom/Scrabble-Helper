import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Dictionary class: Creates a Dictionary object that performs queries in the dictionary
 * 
 * @author Rebecca Hom
 * @since March 2, 2017
 *
 */

public class Dictionary {
	private ArrayList<String> dictionary = new ArrayList<String>();
	
	/** 
	 * Creates a Dictionary object with the data field dictionary
	 * If the file name is invalid, the method throws an exception
	 * @param f The name of the File object that contains the dictionary text
	 * @throws IllegalArgumentException if the file cannot be read
	 */
	public Dictionary(File f) throws IllegalArgumentException{
		//Catch any exceptions
		try{
			Scanner fileReader = new Scanner(f);
			//Check if the file exists and can be read
			if (f.canRead()){
				//Read the file by line and store the words into the array list
				while (fileReader.hasNextLine()){
					dictionary.add(fileReader.next());
				}
			}
			else{
				fileReader.close();
				throw new IllegalArgumentException();
			}
			fileReader.close();
			
		}
		catch (FileNotFoundException error){
			System.err.print("File cannot be read");
		}
	}

	/**
	 * Determines if the argument string str is one of the words stored in the dictionary
	 * Uses binary search to look for the string
	 * @param str The word that is given to the method to see if it's in the dictionary
	 * @return True if the given string can be found in the dictionary, False if it can't be found
	 */
	public boolean isWord(String str){
		//Set the bounds of the binary search
		int upper = dictionary.size() - 1;
		int lower = 0;
		int middle = (upper + lower)/2;
		//Call the recursive method
		if (wordRecursiveBinarySearch(str,upper,lower,middle).equals("")){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Determines if the argument str is a prefix for at least one of the words in the dictionary
	 * Uses binary search to look for the string
	 * @param str The word that is given to the method to see if it's a prefix of a word in the dictionary
	 * @return True if the given word is a prefix of a word in the dictionary; false otherwise
	 */
	public boolean isPrefix(String str){
		//Set the bounds of the binary search
		int upper = dictionary.size() - 1;
		int lower = 0;
		int middle = (upper+lower)/2;

		//Call the recursive method
		if (prefixRecursiveBinarySearch(str,upper,lower,middle).equals(str)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * A method that looks for a word in the dictionary with binary search 
	 * @param str The word that the method is searching for
	 * @param upper The upper bound (index) of the half that the method is searching in
	 * @param lower The lower bound (index) of the half that the method is searching in
	 * @param middle THe middle bound (index) of the half that the method is searching in
	 * @return The word, str, if it's in the dictionary; otherwise, return an empty string
	 */
	private String wordRecursiveBinarySearch(String str, int upper, int lower, int middle){
		//If the word is in the middle of the dictionary, return it
		if (str.equals(dictionary.get(middle))){
			return str;
		}
		else{
			//If the middle and lower index are the same, and the word is not in either the middle/lower index
			//or the upper index, then the word is not there. Return an empty string.
			if (middle == lower && !str.equals(dictionary.get(upper))){
				return "";
			}
			//If the middle and upper index are the same, and the word is not in either the middle/upper index
			//or the lower index, then the word is not there. Return an empty string.
			else if (middle == upper && !str.equals(dictionary.get(lower))){
				return "";
			}

			//If the word is greater than the middle term of the dictionary (i.e. further down the alphabet),
			//then split the dictionary in half and look in the upper half by restricting the bounds
			else if (str.compareToIgnoreCase(dictionary.get(middle))> 0){
				lower = middle + 1;
				middle = (upper+lower)/2;
			}
			else{
				//If the word is less than the middle term of the dictionary (i.e. higher up in the alphabet),
				//split the dictionary in half and look in the lower half by restricting the bounds
				upper = middle - 1;
				middle = (upper+lower)/2;
			}
			//Keep making recursive calls until the entire dictionary is searched through
			return wordRecursiveBinarySearch(str,upper,lower,middle);
		}
	}
	
	/**
	 * A method that checks if a word is a prefix in the dictionary utilizing binary search
	 * @param str The string that the method checks to see if it is a prefix of a word in the dictionary
	 * @param upper The upper bound/index of the half that the binary search looks in
	 * @param lower The lower bound/index of the half that the binary search looks in
	 * @param middle The middle bound/index of the half that the binary search looks in
	 * @return The string if it is a prefix of a word in the dictionary; an empty string otherwise
	 */
	private String prefixRecursiveBinarySearch(String str, int upper, int lower, int middle){
		//Start looking at the middle term; if the given string is a prefix of the middle term, 
		//return the prefix
		if (dictionary.get(middle).startsWith(str)){
			return str;
		}
		else{
			//If the middle and lower index are the same, and the word is not a prefix of either the middle/lower index
			//or the upper index, then the word is not a prefix. Return an empty string.
			if (middle == lower && !dictionary.get(upper).startsWith(str)){
				return "";
			}
			
			//If the middle and upper index are the same, and the word is not a prefix of either the middle/upper index
			//or the lower index, then the word is not a prefix. Return an empty string.
			else if (middle == upper && !dictionary.get(lower).startsWith(str)){
				return "";
			}
			
			//If the prefix is greater than the middle term, cut the dictionary in half and search in the upper half
			//by changing the bounds
			else if (str.compareToIgnoreCase(dictionary.get(middle))> 0){
				lower = middle + 1;
				middle = (upper+lower)/2;
			}
			//If the prefix is less than the middle term, cut the dictionary in half and search in the lower half
			//by changing the bounds
			else{
				upper = middle - 1;
				middle = (upper+lower)/2;
			}
			
			//Make recursive calls to search the whole list using binary search
			return prefixRecursiveBinarySearch(str,upper,lower,middle);
		}
}
	
}