package indexMaker;

import java.io.File;
import java.io.FileNotFoundException;
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
		
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(new File(inputName));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}

		//make output file
		if (args.length > 1) {
			outputName = args[1];
		} else {
			System.out.println("Output file name: ");
			outputName = keyboard.nextLine().trim();
			if (outputName.equals("")) {
				if (inputName.contains(".")) {
					outputName = inputName.substring(0, inputName.lastIndexOf(".")) + "Index.txt";
				} else {
					outputName = inputName + "Index.txt";
				}
			}
		}

		PrintWriter outputFile = new PrintWriter(new FileWriter(outputName));

		//create DocumentIndex for the input file
		DocumentIndex index = new DocumentIndex();

		String line;
		int lineNum = 0;
		while (inputFile.hasNextLine()) {
			line = inputFile.nextLine();
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