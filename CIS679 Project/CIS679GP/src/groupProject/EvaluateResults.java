/* Program Purpose:
 * This evaluates the Flesh results for each file, converting each to something a little more
 * human readable.
 */

package groupProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

public class EvaluateResults {
	public static void main(String[] args) throws IOException {
		String trimmedFile = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/FleshResults/trimmed.txt";
		String gutenbergString = null;
		String [] gbStringArray = null;
		double [] gbDoubleArray = null;
		String ES = "Flesh Reading Ease Score: ";
		String GL = "Flesh-Kincaid Grade Level: ";
	    List<String> evaluatedResults = new ArrayList<String>();
	    String evaluatedResultsFile = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/FleshResults/evaluated.txt";
		
		//Open trimmed.txt
	    try
	    { 
	    	BufferedReader buff = new BufferedReader(new FileReader(trimmedFile));
	        try {
	            StringBuilder sb4 = new StringBuilder();
	            String line = buff.readLine();

	            while (line != null) {
	                sb4.append(line);
	                line = buff.readLine();
	            }
	            gutenbergString = sb4.toString();
	        } finally {
	        	buff.close();
	        }
	    } finally {
	    	gbStringArray = StringUtils.split(gutenbergString, ",", 0);
	    	gbDoubleArray = (double[])ConvertUtils.convert(gbStringArray, Double.TYPE);
	    }
		
		//Find the grade level for the Flesh results
		int q = 0;
		for (int i = 1; i < 501; i++) {
			if (gbDoubleArray[q] >= 17.00) {
				evaluatedResults.add(GL + "Advanced College");
				q = q +2;
			} else if (gbDoubleArray[q] >= 13.00 && gbDoubleArray[q] <= 16.00) {
				evaluatedResults.add(GL + "College");
				q = q +2;
			} else if (gbDoubleArray[q] >= 12.00) {
				evaluatedResults.add(GL + "12th Grade");
				q = q +2;
			} else if (gbDoubleArray[q] >= 11.00) {
				evaluatedResults.add(GL + "11th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 10.00) {
				evaluatedResults.add(GL + "10th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 9.00) {
				evaluatedResults.add(GL + "9th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 8.00) {
				evaluatedResults.add(GL + "8th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 7.00) {
				evaluatedResults.add(GL + "7th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 6.00) {
				evaluatedResults.add(GL + "6th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 5.00) {
				evaluatedResults.add(GL + "5th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 4.00) {
				evaluatedResults.add(GL + "4th Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 3.00) {
				evaluatedResults.add(GL + "3rd Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 2.00) {
				evaluatedResults.add(GL + "2nd Grader");
				q = q +2;
			} else if (gbDoubleArray[q] >= 1.00) {
				evaluatedResults.add(GL + "1st Grader");
				q = q +2;
			} else {
				evaluatedResults.add(GL + "Kindergarten");
				q = q +2;
				}
		}
	    
	    //Find the reading ease score for the Flesh results
		int p = 1;
		for (int r = 1; r < 501; r++) {
			if (gbDoubleArray[p] >= 90) {
				evaluatedResults.add(ES + "Very Easy");
				p = p + 2;
			} else if (gbDoubleArray[p] >= 80) {
				evaluatedResults.add(ES + "Easy");
				p = p + 2;
			} else if (gbDoubleArray[p] >= 70) {
				evaluatedResults.add(ES + "Fairly Easy");
				p = p + 2;
			} else if (gbDoubleArray[p] >= 60) {
				evaluatedResults.add(ES + "Standard");
				p = p + 2;
			} else if (gbDoubleArray[p] >= 50){
				evaluatedResults.add(ES + "Fairly Difficult");
				p = p + 2;
			} else if (gbDoubleArray[p] >= 30 && gbDoubleArray[p] < 50) {
				evaluatedResults.add(ES +"Difficult");
				p = p + 2;
			} else {
				evaluatedResults.add(ES + "Very Difficult");
				p = p + 2;
				}
			}
		
		/* Important: The ordering scheme of evaluated.txt is different than results.txt or
		 * trimmed.txt. The new scheme is: GL Result for Record 1, GL Result for Record 2, etc.
		 * followed by ES Result for Record 1, ES Result for Record 2, etc.
		 */
		
		String saveEvaluatedResults = StringUtils.join(evaluatedResults, ',');
	    
	    try {
			//Write results to file
			try {
			System.setOut(new PrintStream(new FileOutputStream(evaluatedResultsFile)));
			} catch (FileNotFoundException e4) {
				e4.printStackTrace();
				}
			}
		finally {
			System.out.println(saveEvaluatedResults);
		}
	}
}
