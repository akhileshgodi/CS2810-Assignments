
/**
 * @author Akhilesh Godi
 * @date Mar 5, 2012
 * CS2810- File Reading Assignment
 */
package readandreverse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class StringReader {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		File input = new File("Input.txt");
		File output = new File ("Output.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(output) );
		BufferedReader reader = new BufferedReader(new FileReader(input));
        
		//... Loop as long as there are input lines.
	    String line = null;
	    while ((line=reader.readLine()) != null) 
	    {
            String revStr = reverse(line);

            writer.write(revStr);
            writer.newLine();   // Write system dependent end of line.
        }
	    reader.close();
		writer.close();
	}

	private static String reverse(String line) 
	{
		if(line == null || line.isEmpty() || !line.contains(" "))
		    return line;

		  String reversed = "";
		  for(String word : line.split(" "))
		    reversed = word + " " + reversed;

		  return reversed;

	}

	

}
