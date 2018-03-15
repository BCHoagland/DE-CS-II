package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AlgorithmTester {
	
	public static final int MAX = 30;
	public static final int DIV = 4;
	public static ArrayList<String> hashesStrs = new ArrayList<String>();
	
	public static int hash(char ch) {
		switch(ch) {
		case ' ':
			return 0;
		case 'o':
			return 1;
		case 'x':
			return 2;
		default:
			return -1;
		}
	}
	
	public static int hash(String str) {
		int hash = 0;
		int n = str.length();
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			hash += hash(chars[i]) * Math.pow(3, n - 1 - i);
		}
		return hash;
	}
	
	private static void permutation(char[] perm, int pos, String str) {
	    if (pos == perm.length) {
	        hashesStrs.add(new String(perm));
	    } else {
	        for (int i = 0 ; i < str.length() ; i++) {
	            perm[pos] = str.charAt(i);
	            permutation(perm, pos+1, str);
	        }
	    }
	}
	
	public static void main(String[] args) {
		
		char[] perm = new char[9];
		permutation(perm, 0, " ox");
		
		ArrayList<Integer> hashes = new ArrayList<Integer>();
		
		for (String str : hashesStrs) {
			int hash = hash(str);
			hashes.add(hash);
		}
		
		int[] hashesInts = new int[hashes.size()];
		for (int i = 0; i < hashes.size(); i++) {
			hashesInts[i] = hashes.get(i);
		}
		Arrays.sort(hashesInts);
		
		for (int i = 1; i < hashesInts.length; i++) {
			if (hashesInts[i] - hashesInts[i - 1] != 1) {
				System.out.println("doesn't map to every spot");
				break;
			}
		}
		
		System.out.println(hash("xxxxxxxxx"));
	}
}