package theCryptograms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	/*
	public static void myMethod() {
		//goes through dictionary and if the length of the word is same as word1
		//and if there are 2 letters that are the same in the positions of indLetter1 and indLetter2
		//then it found a word that matches the pattern of word1
		for(int i = 0; i<dictionary.size(); i++) {
			if(dictionary.get(i).length() == word1.length && dictionary.get(i).charAt(indLetter1) == dictionary.get(i).charAt(indLetter2)) {
				System.out.println("word that matches pattern is: " + dictionary.get(i));
			}
		}
				
		ArrayList<String[]> results = new ArrayList<String[]>();
		char[] result1 = new char[patterns[0].length()];
		char[] result2 = new char[patterns[0].length()];
				
		for(int a = 0; a<alphabet.length; a++) {
			
		}
	}*/
	
	//prints the word1 and word 2
			/*
			for(int i = 0; i<patterns.length; i++) {
				System.out.println(patterns[i]);
			}*/
	
	static boolean checkDuplicates(String word) {
		boolean flag = false;
		for(int i = 0; i<word.length(); i++) {
			for(int j = 0; j<word.length(); j++) {
				if(i != j && word.charAt(i) == word.charAt(j)) {
					flag = true;
				}
			}
		}
		return flag;
	}
	

	public static void main(String[] args) {
		
		//dictionary is an ArrayList of the dictionary
		ArrayList<String> dictionary = new ArrayList<String>();
		
		//Takes in a Cryptogram as an input from the user via StdIn
		Scanner myScanObj = new Scanner(System.in);
		System.out.println("Enter the pattern: ");
		String pattern = myScanObj.nextLine();
		//System.out.println("The given Cryptogram: " + pattern);
		
		
		
		//patterns is an array that holds 2 strings - pattern1 and pattern2
		//Example: [asd, das]
		String[] patterns = pattern.split(" ");
		
		//pattern1 is a String that is the first given word
		//Example: asd
		String pattern1 = patterns[0];
		//System.out.println("This is pattern1 (String): " + pattern1);

		//pattern2 is a String that is the second given word
		//Example: das
		String pattern2 = patterns[1];
		//System.out.println("This is pattern2 (String): " + pattern2);
		
		//this might not be needed
		//p1chars is array that holds characters from pattern1
		//Example: [a, s, d]
		/*
		char[] p1chars = new char[pattern1.length()];
		for(int i = 0; i<p1chars.length; i++) {
			p1chars[i] = pattern1.charAt(i);
		}*/
		
		
		//this might not be needed
		//p2chars is array that holds characters from patterns2
		//Example: [d, a, s]
		/*
		char[] p2chars = new char[pattern2.length()];
		for(int i = 0; i<p2chars.length; i++) {
			p2chars[i] = pattern1.charAt(i);
		}*/
		
		
		//this might not be needed
		//p1_ind is array that holds integers of positions of pattern1
		//Example: [0, 1, 2]
		/*
		int[] p1_ind = new int[pattern1.length()];
		for(int i = 0; i<p1_ind.length; i++) {
			p1_ind[i] = i;
		}*/
		
			
		//p2_ind is ArrayList that holds integers of positions of pattern2
		//Example: [2, 0, 1]		
		ArrayList<Integer> p2_ind = new ArrayList<Integer>();
		for(int i = 0; i<pattern1.length(); i++) {
			for(int j = 0; j<pattern1.length(); j++) {
				if(pattern2.charAt(i) == pattern1.charAt(j) && !p2_ind.contains(j)) {
					p2_ind.add(i, j);
				}
			}
		}
		/*
		for(int i = 0; i<p2_ind.size(); i++) {
			System.out.println("p2_ind is: " + p2_ind.get(i));
		}*/
		
				
		try {
			
			//Reads the File
			//File myObj = new File(args[1]);
			//File myObj = new File("/Users/mycahdetorres/cryptograms/Cryptograms/src/theCryptograms/dictionary.txt");
			
			Scanner myScannerObj = new Scanner(System.in);
			System.out.println("Type the path of the dictionary.txt: ");
			String dictionaryPath = myScannerObj.nextLine();
			File myObj = new File(dictionaryPath);
			
			Scanner myReader = new Scanner (myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				dictionary.add(data); //this adds each word in the dictionary into the ArrayList called dictionary
				//System.out.println(data);
			}
			myReader.close();	
			myScannerObj.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		
		//results is an ArrayList that holds the words that match pattern1 and pattern2
		//Example: [are, ear, eat, tea, how, who, own, now]
		ArrayList<String> results = new ArrayList<String>();
		
		
	
		
		//wordA is the current word from the dictionary - is String
		//choosing wordA: go through dictionary 
		//if length of wordA == length of pattern1
		//if pattern1 has a pattern within it - meaning duplicate letter within pattern1
			//then check if word1 matches the pattern wihtin pattern 1 - example - ada check for a_a
			//if this is all true thennnn - we need to rearrange
		for(int i = 0; i<dictionary.size(); i++) {
			//wordA is a String that is the current word from the dictionary
			String wordA = dictionary.get(i);
			
			if(wordA.length() == pattern1.length()) {
				//System.out.println("this word is the correct length: " + dictionary.get(i));
				
				//ensures that pattern1 doesn't have duplicates and wordA doesn't have duplicates
				if(!checkDuplicates(pattern1) && !checkDuplicates(wordA)) {
				
					//rearrangedWord is String that is the rearranged word of wordA
					String rearrangedWord = "";
					
					//*****function to rearrange wordA to match pattern2
					for(int a = 0; a<p2_ind.size(); a++){
						rearrangedWord += wordA.charAt(p2_ind.get(a));
					}
					//System.out.println("Rearranged Word is: " + rearrangedWord);
					
					
					//wordB is string of correct rearranged word
					String wordB = "";
					
					//****function to check if rearranged word is in dictionary
					for(int w = 0; w<dictionary.size(); w++) {
						if(dictionary.get(w).length() == rearrangedWord.length() && dictionary.get(w).equals(rearrangedWord) && dictionary.get(w) != wordA) {
							wordB = dictionary.get(w);
							//System.out.println("the rearranged word matches the dicitonary word: " + dictionary.get(w));
							
							//ensures that no duplicate pair of words are added to the results
							if(!results.contains(wordA) && ! results.contains(wordB)) {
								results.add(wordA);
								results.add(wordB);
							}
							//System.out.println("*********Solution is: " + wordA + " " + wordB);
						}
					}
					//System.out.println("wordB: " + wordB);
					
				} else if(checkDuplicates(pattern1) && checkDuplicates(wordA)) {

					//rearrangedWord is String that is the rearranged word of wordA
					String rearrangedWord = "";
					
					//*****function to rearrange wordA to match pattern2
					for(int a = 0; a<p2_ind.size(); a++){
						rearrangedWord += wordA.charAt(p2_ind.get(a));
					}
					//System.out.println("Rearranged Word is: " + rearrangedWord);
					
					
					//wordB is string of correct rearranged word
					String wordB = "";
					
					//****function to check if rearranged word is in dictionary
					for(int w = 0; w<dictionary.size(); w++) {
						if(dictionary.get(w).length() == rearrangedWord.length() && dictionary.get(w).equals(rearrangedWord) && dictionary.get(w) != wordA) {
							wordB = dictionary.get(w);
							//System.out.println("the rearranged word matches the dicitonary word: " + dictionary.get(w));
							
							//ensures that no duplicate pair of words are added to the results
							if(!results.contains(wordA) && ! results.contains(wordB)) {
								results.add(wordA);
								results.add(wordB);
							}
							//System.out.println("*********Solution is: " + wordA + " " + wordB);
						}
					}
					//System.out.println("wordB: " + wordB);
				}
				
			}
		}
		
		
		
		//Final Output
		//should already be alphabetic order bc go through dictionary which is in alphabetic order
		System.out.println(results.size()/2);
		for(int i = 0; i<results.size(); i+=2) {
			System.out.println(results.get(i) + " " + results.get(i+1));
		}
		
		myScanObj.close();
		
				
		
	}
}



























//testing for example aba baa --> result is oto too 

		//Example for testing output
		/*
		results.add("are");
		results.add("ear");
		results.add("eat");
		results.add("tea");
		results.add("how");
		results.add("who");
		results.add("own");
		results.add("now");*/
		
		
		//we need to tell it that if the given pattern has duplicate letters
		//then the result should have the same letters at those spots
		//example aba baa --> result is oto too
		
		//we need to tell it that if the given pattern doesn't have duplicate letters
		//then the result shouldn't have duplicate letters at certain spots
		//example gop pgo --> result is add dad - this CAN'T happen
		
		
		
		//question - if the given crytogram is gop pgo, each letter must be 
		//mapped to a different letter
		//meaning o and p can't map to the same letter
		//for example the cryptogram is gop pgo
		//and then the result is add and dad, so o and p would both map to the letter d
		//so this is an example of something that can't happen
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		//All of this will be combined:
		
		//******************************************
		
		//wordA is the current word from the dictionary - is String
		//choosing wordA: go through dictionary 
		//if length of wordA == length of pattern1
		//if pattern1 has a pattern within it - meaning duplicate letter within pattern1
			//then check if word1 matches the pattern wihtin pattern 1 - example - ada check for a_a
			//if this is all true thennnn - we need to rearrange
		for(int i = 0; i<dictionary.size(); i++) {
			if(dictionary.get(i).length() == pattern1.length()) {
				System.out.println("this word is the correct length: " + dictionary.get(i));
				break;
			}
		}
		
		//wordA is a String that is the current word from the dictionary
		String wordA = "dleh";
		
		//rearrangedWord is String that is the rearranged word of wordA
		String rearrangedWord = "";
		
		//wordB is string of correct rearranged word
		String wordB = "";
		
		//*****function to rearrange wordA to match pattern2
		for(int i = 0; i<p2_ind.size(); i++){
			rearrangedWord += wordA.charAt(p2_ind.get(i));
		}
		System.out.println("Rearranged Word is: " + rearrangedWord);
		
		
		
		//****function to check if rearranged word is in dictionary
		for(int i = 0; i<dictionary.size(); i++) {
			if(dictionary.get(i).length() == rearrangedWord.length() && dictionary.get(i).equals(rearrangedWord)) {
				wordB = dictionary.get(i);
				System.out.println("the rearranged word matches the dicitonary word: " + dictionary.get(i));
			}
		}
		System.out.println("wordB: " + wordB);
		//******************************************
		*/
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		//if letter is a and word in dictionary with letter a at position1 in word1, 
		//and letter a at position 2 for word2
		//and length is same 
		
		//function that changes the letter a to b to c from alphabet
		
		//goes through dictionary --> at dictionary.word(A)
		//if dictionary.word(A).length = word1length
			//inner for loop goes through dictionary --> at dictionary.word(B)
			//if position1 of letter a in dictionary.word(A) == letter at dictionary.word(B) at position2
				//this checks if word(A)'s has a letter a and word(B) also has letter a but at position2
		
		//conditions - wordA and wordB need ot be same length as word1
		//wordA needs to have same letters as wordB
		//wordB needs to match the pattern of word2
		
		
		//step 1 - check if current dictionary word is same as length of word1
		//step 2 - if yes then reaarange word to match pattern of word2
		//step 3 - go through dictionary and check if length of word = word1, and if the rearranged word matches pattern of word2
		
		
		//function to rearrange wordA to match pattern of word2
		//then later on will check if rearranged word exists in dictionary, to become wordB
		
		//wordA is array that holds the
		//pattern2 is array that holds the positions of rearranged word
		//wordB is the rearranged word stored as an array
		//given "asd das" --> from 012 into 201
		//for(int i = 0; i<pattern2.length; i++){
		//	 wordB[i] = pattern2[i]
		//}
		
		//String myWord = "word";
		//System.out.println("TESTINGGGGG: " + myWord.charAt(2));
		
		
		
		
		
		
		
		
		//defining terms
		//"asd das"
		
		//patterns is array that holds 2 strings - asd and das
		
		//pattern1 is first word - asd
		//pattern2 is second word - das
		
		//p1chars is array that holds characters from asd - a, s, d
		//p2chars is array that holds chars from das - d, a, s
		
		//p1_ind is array that holds ints of positions of asd - is just 0, 1, 2
		//p2_ind is array that holds ints of posiitons of das - 2, 0, 1
			//need to check if there are duplicate characters,  THIS IS IMPORTANTTTTTTTTTTTTTTTTTT!!!!!!!!!!!!!!!!!!!!!!!!!
			//if yes then make sure their index is different in p2_ind
		
		//wordA is the current word from the dictionary - is String
			//choosing wordA: go through dictionary 
			//if length of wordA == length of pattern1
			//if pattern1 has a pattern within it - meaning duplicate letter within pattern1
				//then check if word1 matches the pattern wihtin pattern 1 - example - ada check for a_a
				//if this is all true thennnn - we need to rearrange
		
		//we are trying to rearrange wordA to match pattern2 
			//(then later on will check if the rearranged word is found in the dictionary, and if yes, then that becomes wordB and that is a solution)
		
		//rearrangedWord is String that is the rearranged word of wordA
		//wordB is string of correct rearranged word
		
		
		//*****function to rearrange wordA to match pattern2
		//given wordA which is String
		//for(int i = 0; i<p2_ind.length; i++){
		//	rearrangedWord += wordA.charAt(p2_ind[i]);
		//}
		
		//****function to check if rearranged word is in dictionary
		/*
		for(int i = 0; i<dictionary.size(); i++) {
			if(dictionary.get(i).length() == rearrangedWord.length() && dictionary.get(i).equals(rearrangedWord)) {
				wordB = dictionary.get(i);
				System.out.println("the rearranged word matches the dicitonary word: " + dictionary.get(i));
			}
		}
		*/
		
		
		//there's going to be multiple wordA's and wordB's
		//because multiple pairs that match the given pattern 
		//need a way to save all of the pairs
		//also need a way to not have duplicates
		
		


//asd
		//das
		
		//012
		//201
		
		//if dictionary has word that has 012 and 201 then 
		
		
		//if dictionary
		//creating result1
		//start with a
		//if dictionary has a word that has a in that position 1
		//and dictionary has word that has a in position2
		//and if length of word the same, then 
		
						
				
				
						
				
		
		
		
		//ArrayList<String[]> results = new ArrayList<String[]>();
		//char[] result1 = new char[patterns[0].length()];
		//char[] result2 = new char[patterns[0].length()];
		





//prints out the dictionary stored in ArrayList dictionary 
/*
for(int i = 0; i<dictionary.size(); i++) {
	System.out.println("hi: " + dictionary.get(i));
}*/




//goes through dictionary and if the length of the word is same as word1
//and if there are 2 letters that are the same in the positions of indLetter1 and indLetter2
//then it found a word that matches the pattern of word1

/*
for(int i = 0; i<dictionary.size(); i++) {
	if(dictionary.get(i).length() == word1.length && dictionary.get(i).charAt(indLetter1) == dictionary.get(i).charAt(indLetter2)) {
		System.out.println("word that matches pattern is: " + dictionary.get(i));
	}
}*/


/*
//String result1 = "";
//String result2 = "";
char[] result1 = new char[patterns[0].length()];
char[] result2 = new char[patterns[0].length()];

//go through and change letter at position 0 form a to z until it matches the conditions
//then go through and change letter at position 1 from a to z until it matches the conditions
for(int i = 0; i<patterns[0].length(); i++) {
	for(int a = 0; a<alphabet.length; a++) {
		result1[i] = alphabet[a];
	}
}

*/



/*
//creates arrray of each letter in word1
char[] word1 = new char[patterns[0].length()];
for(int i = 0; i<word1.length; i++) {
	word1[i] = patterns[0].charAt(i);
}

//prints each letter from word1
for(int i=0; i<word1.length; i++) {
	System.out.println(word1[i]);
}

//initialize ints for the position of 2 same letters in word1
int indLetter1 = 10;
int indLetter2 = 10;

//checks if 2 same letters in a word and their positions
for(int i=0; i<word1.length; i++) {
	for(int j=0; j<word1.length; j++) {
		if(i != j && word1[i] == word1[j]) {
			indLetter1 = i;
			indLetter2 = j;
		}
	}
}

//prints out the index of a letter that is the same
System.out.println("IndLetter1: " + indLetter1);
System.out.println("IndLetter2: " + indLetter2);
*/




//creates array of all letters in alphabet
//char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};



//things to check for in dictionary
//look at pattern 
//aba
//look at a_a
// if there something in dictionary that 
// 1) is there 'a' at these positions in the word
// and 2) length of word is in dictionary 
//if yes then rearrange the word in the dictionary based on pattern
//then check if that reaaranged word is in the dictionary 

//if no then move on to next letter - is there 'b' at these positions in the word
//

//asd








//ArrayList<Integer> numPatterns1 = new ArrayList<Integer>();
//ArrayList<Integer> numPatterns2 = new ArrayList<Integer>();






/*
for(int i = 0; i<patterns[0].length(); i++) {
	numPatterns1.set(i, i);
	System.out.println("First pattern: " + numPatterns1.get(i));
}

for(int i = 0; i<patterns[0].length(); i++) {
	for(int j = 0; j<patterns[0].length(); j++) {
		if(patterns[1].charAt(i) == (patterns[0].charAt(j))) {
			if(numPatterns2.contains(j)) {
				
			}
			if(i != 0 && patterns[0].charAt(i) == patterns[0].charAt(i-1) ) {
				numPatterns2[i] = j;
				System.out.println("here!" + i);
				break;
			} else {
				numPatterns2[i] = j;
			}
		}
	}
	System.out.println("Second pattern: " + numPatterns2[i]);
}
*/

//i != 0 && patterns[0].charAt(i) == patterns[0].charAt(i-1) 
/*
if count 

for(int i = 0; i<patterns[0].length(); i++) {
	
}

go through second word
first get letter of seocnd word is d
find the letter at position d in first word then add it to the new string
then go to second letter of 2nd word - is a
find the letter at position a which is 0 which is a 
patterns[0].charAt[]*/

/*
 
 if word1 = word from dictionary 
 && word2 = word from dictionary 
 then totalCount+=1
 also arrayList arr - add string "word1 word2"
 
 output -
 System.out.println(totalCount);
 for(int i = 0; i<totalCount; i++){
 	System.out.println(arr[i]);
 }
 
 for(int i = 0; i<26; i++){
 	
 }*/
 /*
 boolean finished = false; //found all solutions yet?
 public void backtrack(int a[], int k, data input){
 	int c[MAXCANDIDATES]; //candidates for next position
 	int ncandidates; //next position candidate count
 	int j; //counter
 	
 	if(is_a_solution(a,k,input)) {
 		process_solution(a,k,input);
 	} else {
 		k+=1;
 		construct_candidates(a,k,input,c,ncandidates);
 		for(j=0; j<ncandidates; j++){
 			a[k]=c[j];
 			backtrack(a,k,input);
 			if(finished){
 				break; //terminate early
 			}
 		}
 	}
 	
 		
 }*/






/*
//splits the line by spaces
String[] arrOfStr = data.split(" ");
	
//4 pieces of information (id, letter, info, timestamp)
int myId = Integer.parseInt(arrOfStr[0]);
String myLetter = arrOfStr[1];
int myInfo = Integer.parseInt(arrOfStr[2]);
int myTimestamp = Integer.parseInt(arrOfStr[3]);
						

//Checks to see if the given id is already present in the Hashmap
if(cows.containsKey(myId)){  //this is O(1)
	
	//Changes an existing Cow's fields
	if(myLetter.equals("W")) {
		cows.get(myId).setLatestWeight(myInfo);
		cows.get(myId).setLowestWeight(cows.get(myId).getLowestWeight(), cows.get(myId).getLatestWeight());
	} else if (myLetter.equals("M")) {
		cows.get(myId).setMilk(myInfo);
		cows.get(myId).setMilkNum(cows.get(myId).getMilkNum());
		cows.get(myId).setAvgMilkProduction(cows.get(myId).getMilk(), cows.get(myId).getMilkNum());
	} else if (myLetter.equals("T")) {
		cows.get(myId).setTemp(myInfo);
	}	
} else {
	//Creates a new Cow and puts it into the Hashmap
	//and adds it to the ArrayList
	Cow myCow = new Cow(myId, myLetter, myInfo, myTimestamp);
	cows.put(myId, myCow);
	cowList.add(myCow);
}*/


/*
for(int i = 0; i<arrOfStr.length; i++) {
	System.out.println(arrOfStr[i]);
}*/