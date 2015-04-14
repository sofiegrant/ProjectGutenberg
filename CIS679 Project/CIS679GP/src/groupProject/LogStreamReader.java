/* Sources:
 * See RunFlesh.java notes
 * 
 * Program Purpose:
 * Works in conjunction with Flesh to write the results to file
 */

package groupProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class LogStreamReader implements Runnable {
	String fleshResults = "C:/Users/Senna/workspace/CIS679GP/Gutenberg/FleshResults/results475.txt";
	
    private BufferedReader reader;
    
    public LogStreamReader(InputStream is) {
        this.reader = new BufferedReader(new InputStreamReader(is));
    }

    public void run() {
		try {
			//Write results to file
			try {
			System.setOut(new PrintStream(new FileOutputStream(fleshResults)));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			//Begin writing to file
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			reader.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
    } 
}