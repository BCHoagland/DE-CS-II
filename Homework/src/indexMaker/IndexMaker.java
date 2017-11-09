package indexMaker;

/**
 * This program takes a text file, creates an index (by line numbers)
 *  for all the words in the file and writes the index
 *  into the output file.  The program takes input and output file names
 *  from the command-line args or prompts the user for the file names.
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class IndexMaker {
	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		String inputName, outputName;

		//make input file
		if (args.length > 0) {
			inputName = args[0];
		} else {
			System.out.println("Input file name: ");
			inputName = keyboard.nextLine().trim();
		}

		BufferedReader inputFile = new BufferedReader(new FileReader(inputName), 1024);

		//make output file
		if (args.length > 1) {
			outputName = args[1];
		} else {
			System.out.println("Output file name: ");
			outputName = keyboard.nextLine().trim();
			if (outputName.equals("")) {
				//TODO fix this so it doesn't make the output file name "input.txtIndex.txt"
				outputName = inputName + "Index.txt";
			}
		}

		PrintWriter outputFile = new PrintWriter(new FileWriter(outputName));

		//create DocumentIndex for the input file
		DocumentIndex index = new DocumentIndex();

		String line;
		int lineNum = 0;
		while ((line = inputFile.readLine()) != null) {
			lineNum++;
			index.addAllWords(line, lineNum);
		}

		//transfer contents of the DocumentIndex to the output file
		for (Map.Entry<String, IndexEntry> entry : index.entrySet())
			outputFile.println(entry.getValue());

		//close files and scanners
		inputFile.close();
		outputFile.close();
		keyboard.close();
	}
}