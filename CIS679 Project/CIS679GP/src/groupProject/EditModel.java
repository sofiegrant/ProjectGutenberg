/*Source:
 * http://stackoverflow.com/questions/16766408/jena-library-is-not-writing-output-to-an-external-rdf-xml-file
 * For instructions on setting up an outputstream
 * 
 * Program Purpose:
 * This edits each metadata record, adding its Flesch scores.
*/

package groupProject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.*;

public class EditModel extends Object {
	
	public static void main(String[] args) throws IOException {
		String evaluatedFile = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/FleshResults/evaluated.txt";
		String gutenbergString = null;
		String [] gutenbergArray = null;
		String fileBase = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/Records";
		String urlBase = "http://www.gutenberg.org/ebooks";
		
		//Open trimmed.txt into a String, then convert the String to a String Array for easier access
	    try
	    { 
	    	BufferedReader buff = new BufferedReader(new FileReader(evaluatedFile));
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
	    	gutenbergArray = StringUtils.split(gutenbergString, ",", 0);
	    }

		//For each metadata file
		
		//Construct its pathway
        int r = 0;
        int s = 5;
		for (int q = 1; q < 501; q++) {
			StringBuilder sb4 = new StringBuilder();
			sb4.append(fileBase);
			sb4.append("/pg");
			sb4.append(q);
			sb4.append(".rdf");
			String inputFileName = sb4.toString();

			//Open the file
			InputStream in = FileManager.get().open(inputFileName);
			if (in == null) {
				throw new IllegalArgumentException(
						"File: " + inputFileName + " not found");
			}

			//Create an empty model
			Model model = ModelFactory.createDefaultModel();
			
			//Read the file into the model
			model.read(in, "");
			
			//Construct the URL for the current book metadata record
			StringBuilder sb5 = new StringBuilder();
			sb5.append(urlBase);
			sb5.append("/");
			sb5.append(q);
			String gutenbergURI = sb5.toString();
			
			//Create the resource and add the properties cascading style
			model.createResource(gutenbergURI).addProperty(DCTerms.audience, gutenbergArray[r]);
			model.createResource(gutenbergURI).addProperty(DCTerms.audience, gutenbergArray[s]);

			//Create the save file directory
			StringBuilder sb6 = new StringBuilder();
			sb6.append(fileBase);
			sb6.append("/pg");
			sb6.append(q);
			sb6.append("Edited");
			sb6.append(".rdf");
			String outputFileName = sb6.toString();
			
			//Output the results to the save file
			OutputStream output = null;
			try{
			  output = new FileOutputStream(outputFileName);
			} catch (IOException e5) {
				e5.printStackTrace();
				}
			try {
				model.write(output, "RDF/XML");
			} finally {
				try {
					output.close();
				} catch (IOException e6) {
					e6.printStackTrace();
				}
			}
			
			r = r + 1;
			s = s + 1;
			}
		}
}