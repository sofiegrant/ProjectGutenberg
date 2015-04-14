/* Sources:
 * http://stackoverflow.com/questions/4105331/how-to-convert-from-int-to-string
 * for instructions on implementing StringBuilder
 * 
 * http://examples.javacodegeeks.com/core-java/io/file/4-ways-to-copy-file-in-java/
 * for the method copyFileUsingFileStreams (retitled copyFile)
 * 
 * Program Purpose:
 * In the PG metadata tar file, each metadata record for PG works is stored in
 * its own folder. This program copies the contents of each folder into a new
 * union folder.
 */
package groupProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFiles {
	public static void main(String[] args) throws IOException {
		String copyFromBase = "C:/Users/Senna/Desktop/epub/";
		String copyToBase = "C:/Users/Senna/Desktop/GutenbergRecords/";
		
		//For each file
		
		//Build its directory name & assign it to copyFromBase
		for (int a = 0; a < 47072; a++) {
			StringBuilder sb1 = new StringBuilder();
			sb1.append(copyFromBase);
			sb1.append(a);
			sb1.append("/pg");
			sb1.append(a);
			sb1.append(".rdf/");
			String copyFromFull = sb1.toString();
			File copySource = new File(copyFromFull);

			//Build a new directory location & assign it to copyToBase
			StringBuilder sb2 = new StringBuilder();
			sb2.append(copyToBase);
			sb2.append("/pg");
			sb2.append(a);
			sb2.append(".rdf/");
			String copyToFull = sb2.toString();
			File copyDestination = new File(copyToFull);
			
			//Copy the contents of copyFromBase to copyToBase
			copyFile(copySource, copyDestination);
			}
	}
		
	private static void copyFile(File incomingSource, File outgoingDestination) 
			throws IOException {
		InputStream copyInput = null;
		OutputStream copyOutput = null;
		try {
			copyInput = new FileInputStream(incomingSource);
			copyOutput = new FileOutputStream(outgoingDestination);
		    byte[] copyBuff = new byte[1024];
		    int copyBytesRead;
		    while ((copyBytesRead = copyInput.read(copyBuff)) > 0) {
		    	copyOutput.write(copyBuff, 0, copyBytesRead);
		    }
		} finally {
			copyInput.close();
			copyOutput.close();
		    System.out.println(outgoingDestination);
		    }
		}
}