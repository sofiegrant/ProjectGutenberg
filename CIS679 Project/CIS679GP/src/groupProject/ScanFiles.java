/* Sources:
 * http://stackoverflow.com/questions/15577688/search-a-file-for-a-string-and-return-that-string-if-found
 * for instructions on searching a file for a specific phrase
 * 
 * http://codereview.stackexchange.com/questions/48756/find-all-the-missing-numbers
 * for code related to checking whether an array contains a range of numbers
 * 
 * Program Purpose:
 * Not all PG titles are written in English. Flesh cannot assess the readability of these titles.
 * This program identifies which files are in non-English languages.
 */

package groupProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

public class ScanFiles {
	public static void main(String[] args) throws FileNotFoundException {
	    String saveForeignBase = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/foreign.txt";
		String recordBase = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/Records";
		String englishString = "<rdf:value rdf:datatype=\"http://purl.org/dc/terms/RFC4646\">en</rdf:value>";
	    List<String> englishList = new ArrayList<String>();
	    String[] englishStringArray = null;
	    int [] englishIntArray = null;
	    List<String> foreignList = new ArrayList<String>();
		
	    try {
			//Set up file output
			try {
				System.setOut(new PrintStream(new FileOutputStream(saveForeignBase)));
			} catch (FileNotFoundException e8) {
				e8.printStackTrace();
				}
			} finally {
				//For each file
		
				//Build the correct directory location and assign it to our source variable
				for (int u = 1; u < 47072 ; u++) {
					StringBuilder sb3 = new StringBuilder();
					sb3.append(recordBase);
					sb3.append("/pg");
					sb3.append(u);
					sb3.append(".rdf");
					String recordFull = sb3.toString();
					File sourceRecord = new File(recordFull);
			
					final Scanner scanner = new Scanner(sourceRecord);
			
					while (scanner.hasNextLine()) {
						final String lineFromFile = scanner.nextLine();
						if(lineFromFile.contains(englishString)) {
							englishList.add(Integer.toString(u));
							break;
						} else {
						}
					}
					scanner.close();
				}
				
				englishStringArray = englishList.toArray(new String[englishList.size()]);
		    	englishIntArray = (int[])ConvertUtils.convert(englishStringArray, Integer.TYPE);
				
				//Before the array: numbers between first and a[0]-1
			    for (int i = 0; i < englishIntArray[0]; i++) {
			    	foreignList.add(Integer.toString(i));
			    }
			    
			    //Inside the array: at index i, a number is missing if it is between a[i-1]+1 and a[i]-1
			    for (int i = 1; i < englishIntArray.length; i++) {
			        for (int j = 1 + englishIntArray[i-1]; j < englishIntArray[i]; j++) {
			        	foreignList.add(Integer.toString(i));
			        }
			    }
			    
			    //After the array: numbers between a[a.length-1] and last
			    for (int i = 1 + englishIntArray[englishIntArray.length-1]; i <= 47072; i++) {
			    	foreignList.add(Integer.toString(i));
			    }
			    
			    //Save the list of non-English titles
				String outputForeignResults = StringUtils.join(foreignList, ',');
				System.out.println(outputForeignResults);
			}
		}
}
