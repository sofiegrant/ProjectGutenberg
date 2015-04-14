/* Sources:
 * http://stackoverflow.com/questions/12857242/java-create-string-array-from-text-file
 * for instructions on opening the results file
 * 
 * Program Purpose:
 * Flesh provides more information than we want to use per file. This program
 * edits the results file to just the statistics we want.
 */
package groupProject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.*;

public class ParseResults {
	public static void main(String[] args) throws IOException {		
		String textResultsFile = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/FleshResults/results.txt";
	    String[] rawArray= null;
	    List<String> rawResults = new ArrayList<String>();
	    List<String> indexResults = new ArrayList<String>();
		String subGLString = "Flesh-Kincaid Grade Level:";
		List<String> tempTrimmedResults = new ArrayList<String>();
    	String subESString = "Flesh Reading Ease Score:";
	    List<String> trimmedResults = new ArrayList<String>();
		String trimmedResultsFile = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/FleshResults/trimmed.txt";
	    
    	//Read the file
	    try
	    { 
	        FileInputStream fileIS = new FileInputStream(textResultsFile); 
	        DataInputStream dataIS = new DataInputStream(fileIS); 
	        BufferedReader bufferR = new BufferedReader(new InputStreamReader(dataIS)); 
	        String resultLine;

	        while ((resultLine = bufferR.readLine()) != null) 
	        { 
	        	resultLine = resultLine.trim();
	            if ((resultLine.length()!=0))  
	            {
	            	rawResults.add(resultLine);
	            } 
	        }
	        bufferR.close();

	        rawArray = (String[])rawResults.toArray(new String[rawResults.size()]);
	      
	    } finally {
	        int l = 0;
	        int m = 1;
	        //Loop through the Results.txt file, grabbing first two statistics for each record
			for (int n = 1; n < 501; n++) {
		    	indexResults.add(rawArray[l]);
		    	l = l + 6;
				
		    	indexResults.add(rawArray[m]);
				m = m + 6;
			}
	    }
	    
	    //Loop through the results, removing the first unwanted substring
	    String[] tempTrimArray1 = indexResults.toArray(new String[indexResults.size()]);
	    
	    int o = indexResults.size();
	    for (int p = 0; p < o ; p++) {
	    	 String tempString = StringUtils.remove(tempTrimArray1[p], subGLString);
	    	 String tempString2 = StringUtils.trim(tempString);
	    	 tempTrimmedResults.add(tempString2);
	    	 }
	    
	    //And repeat for the second unwanted substring
	    String[] tempTrimArray2 = tempTrimmedResults.toArray(new String[tempTrimmedResults.size()]);
	    
	    int s = tempTrimmedResults.size();
	    for (int q = 0; q < s ; q++) {
	    	 String tempString3 = StringUtils.remove(tempTrimArray2[q], subESString);
	    	 String tempString4 = StringUtils.trim(tempString3);
	    	 trimmedResults.add(tempString4);
	    	 }
	    
	    String saveTrimmedResults = StringUtils.join(trimmedResults, ',');
	    
	    try {
			//Write results to file
			try {
			System.setOut(new PrintStream(new FileOutputStream(trimmedResultsFile)));
			} catch (FileNotFoundException e3) {
				e3.printStackTrace();
				}
			}
		finally {
			System.out.println(saveTrimmedResults);
		}
	}
}