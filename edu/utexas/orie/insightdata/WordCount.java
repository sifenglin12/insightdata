package edu.utexas.orie.insightdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordCount {
	
	/**
	 * this method counts all the words in file under the directory inputFolder 
	 * @param inputDirectory the input directory that contains all the text files 
	 * @return a map that maps the word to number of counts in all the files under the directory
	 */
	public static Map<String, Integer> countWordInDirectory(String inputDirectory){
		
		Map<String, Integer> wordCountMap = new HashMap<>();  //the map that stores the word count information
		
		//get the list of input files, return if there is no file object
		File[] files = InsightData.getTextFile(inputDirectory);
		if(files==null){
			return wordCountMap;
		}
		
		try {
			
			for(File file : files){
				//for each file, read it line by line
				BufferedReader reader = new BufferedReader( new FileReader(file));
				String line;
				line = reader.readLine();
				
				while(line != null){
					
					//only keep the letter, number, and the space; then split the object into words 
					String entries[] = line.replaceAll("[^a-zA-Z\\s]", "").split("[ ]+");
					for(String entry : entries){
						
						//ignore the situation with 0 length
						if(entry.length()==0){
							continue;
						}
						
						//convert the string to lowercase
						String lowerCaseEntry = entry.toLowerCase();  
						Integer curCount = wordCountMap.get(lowerCaseEntry);
						
						//if the word is not in the map (i.e., it is new) count once; otherwise, add 1 to current count
						int newCount = (curCount==null) ? 1 : curCount+1;
						wordCountMap.put(lowerCaseEntry, newCount);
					}
					
					line = reader.readLine();
				}
				reader.close();
			}
			
		} catch (IOException e) {
			//if there is input or output error, return an empty map
			e.printStackTrace();
			return new HashMap<>();
		}
		
		
		
		return wordCountMap;
		
	}
	
	/**
	 * write the map of word count to file
	 * @param wordCountMap the map that contains the count of each word: key is the word; value is the count
	 * @param outputDirectory the directory of the outputfile
	 * @throws IOException
	 */
	public static void writeWordCountToFile(Map<String, Integer> wordCountMap, String outputDirectory){
		(new File(outputDirectory)).mkdirs();
		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter( new FileWriter(outputDirectory+"/wc_result.txt"));
			List<String> keys = getKeyAlphabetSroted(wordCountMap);
			
			for(String key : keys){
				writer.write(key +"\t" + wordCountMap.get(key));
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Given a map, get its keys, which is sorted in alphabet order increasingly (a to z)
	 * @param map the map that we would get the key and sort them
	 * @return the corresponding key that is sorted in the alphabet order
	 */
	private static List<String> getKeyAlphabetSroted(Map<String, Integer> map){
		//create a deep copy of keys in the map, and sort them
		LinkedList<String> keys = new LinkedList<String>(map.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		return keys;
		
	}

}
