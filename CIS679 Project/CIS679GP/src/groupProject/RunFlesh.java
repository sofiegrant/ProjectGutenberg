/* Sources:
 * http://stackoverflow.com/questions/15700879/how-to-run-a-java-executable-jar-in-another-java-program
 * for instructions on how to use process builder to run a jar
 * 
 * http://stackoverflow.com/questions/2851234/system-out-to-a-file-in-java
 * for instructions on how to save the jar output to file
 * 
 * Program Purpose:
 * This runs Flesh, a readability test capable of reading text files. It outputs six
 * statistics related to the readability of each file.
 */

package groupProject;

import java.io.File;
import java.io.IOException;

public class RunFlesh {
	public static void main(String[] args) throws IOException {
		String originalTextBase = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/Text/";
		
		//For each file
	
		//Build its directory name & assign it to textSource
		for (int b = 471; b < 476; b++) {
			StringBuilder sb3 = new StringBuilder();
			sb3.append(originalTextBase);
			sb3.append(b);
			sb3.append(".txt");
			String originalTextSource = sb3.toString();
			
			//Run Flesh
			ProcessBuilder pb = new ProcessBuilder("java", "-jar", "C:/Program Files (x86)/Java/jre7/CmdFlesh.jar", originalTextSource);
	        pb.directory(new File("C:\\"));
	        try {
	            Process p = pb.start();
	            LogStreamReader lsr = new LogStreamReader(p.getInputStream());
	            Thread thread = new Thread(lsr, "LogStreamReader");
	            thread.start();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
}