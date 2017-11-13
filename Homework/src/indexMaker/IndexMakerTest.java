package indexMaker;

import java.io.IOException;

public class IndexMakerTest {
	
	public static void main(String[] args) throws IOException {
		String[][] fileNames = {{"fish.txt", "output.txt"},
								{"lmao", "output2.txt"}};
		
		for (String[] files : fileNames) {
			try {
				IndexMaker.main(files);
			} finally {}
		}
	}
}