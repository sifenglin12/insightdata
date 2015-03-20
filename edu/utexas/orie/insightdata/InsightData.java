package edu.utexas.orie.insightdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * this class implements the main function that calls WordCount and RunningMedian to produce the results
 * @author SifengLin
 *
 */
public class InsightData {
	
	public static void main(String[] args){
		String inputDirectory = "wc_input";
		String outputDirectory = "wc_output";
		wordCount(inputDirectory, outputDirectory);
		runningMedian(inputDirectory, outputDirectory);
	}
	
	/**
	 * count number of occurrences of all words that is (in the input file) under a directory
	 * @param inputDirectory the directory that contains all the file
	 * @param outputDirectory the output directory for the file that shows the word count
	 */
	public static void wordCount(String inputDirectory, String outputDirectory){
		
		Map<String, Integer> wordCoutnMap;
		wordCoutnMap = WordCount.countWordInDirectory(inputDirectory);
		WordCount.writeWordCountToFile(wordCoutnMap, outputDirectory);
	}
	
	/**
	 * this method calculates the running median for number of words in a given set of files
	 * Note: when a line is empty, we count number of words as 0
	 * @param inputDirectory the directory that contains all the file
	 * @param outputDirectory the output directory for the file that shows the word count
	 */
	public static void runningMedian(String inputDirectory, String outputDirectory){
		
		try {
		
			BufferedWriter writer = new BufferedWriter( new FileWriter(outputDirectory+"/med_result.txt"));
			
			File[] files = getTextFile(inputDirectory);
			if(files==null){
				writer.close();
				return;
			}
			
			//sort the file in alpha order
			Arrays.sort(files);
			
			RunningMedian rm = new RunningMedian(); 
			for(File file : files){
				//for each file, read it line by line
				BufferedReader reader = new BufferedReader( new FileReader(file));
				String line = reader.readLine();
				
				while(line != null){

					//only keep the letter, number, and the space, trim the space before and after the word, then get number of words
					String trimedLine = line.replaceAll("[^a-zA-Z\\s]", "").trim();
					int numWords = trimedLine.length()==0 ? 0 : trimedLine.split("[ ]+").length;
					rm.addNumber(numWords);
					//get the current median value
					
					double median = rm.getCurMedian();
					//write the value
					writer.write(median+"\n");
					line = reader.readLine();
				}
				reader.close();
			}
			
			writer.close();
		} catch (IOException e) {
			//if there is input or output error, return an empty map
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method aims at finding all the file object under an input folder
	 * @param inputFolder the input folder that contains all the text files
	 * @return a File array that contains all the files under the directory 
	 */
	public static File[] getTextFile(String inputFolder){
		File folder = new File(inputFolder);
		return folder.listFiles();
	}

}
