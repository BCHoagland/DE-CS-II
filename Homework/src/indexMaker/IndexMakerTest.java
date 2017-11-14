package indexMaker;

import java.io.IOException;

public class IndexMakerTest {

	public static void main(String[] args) throws IOException {
		normalTests();
//		ohYeah(50);
	}

	public static void normalTests() throws IOException {
		String[][] fileNames = {{"fish.txt", "output.txt"},
				{"lmao", "output2.txt"}};

		for (String[] files : fileNames) {
			try {
				IndexMaker.main(files);
			} finally {}
		}
	}

	public static void ohYeah(int numOutputFiles) throws IOException {
		for (int i = 0; i < numOutputFiles; i++) {
			String inputFile = "output" + i + ".txt";
			if (i == 0) {
				inputFile = "fish.txt";
			}
			String outputFile = "output" + (i + 1) + ".txt";
			String[] files = {inputFile, outputFile};
			try {
				IndexMaker.main(files);
			} finally {}
		}
	}
}