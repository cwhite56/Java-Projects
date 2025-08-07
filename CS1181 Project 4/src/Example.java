import java.util.ArrayList;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

// Custom class that represents the brain of my password cracker
// 3 Threads: I was working on this the day it was due. 4 threads took 4 hours. 
// Unfortuneately I did not have time to run the 3 thread version before the deadline
// 4 Threads: 15013840 milliseconds
public class Example {
	public static ArrayList<String> passwordList = new ArrayList<>();
	private volatile static boolean passwordFound = false;

	public static void main(String[] args) throws Exception {
		// Part A
		long currTime = System.currentTimeMillis();

		passwordGeneratorLengthThree();
		// Password brute force loop
		for (int i = 0; i < passwordList.size(); i++) {
			boolean check = correctPasswordCheck(passwordList.get(i));
			if (check) {
				System.out.println("Password: " + passwordList.get(i) + " cracked in "
						+ (System.currentTimeMillis() - currTime) + " milliseconds");
			}
		}
		passwordList.clear();

		// Part B
		currTime = System.currentTimeMillis();
		int numThreads = 4;
		ArrayList<Thread> threadList = new ArrayList<>();
		passwordGeneratorLengthFive();
		int chunkSize = passwordList.size() / numThreads;
		int startIndex = 0;
		int endIndex = chunkSize;

		// Thread initiation
		for (int i = 0; i < numThreads; i++) {

			CustomThread t = new CustomThread(new ArrayList<String>(passwordList.subList(startIndex, endIndex)));
			t.start();
			threadList.add(t);
			startIndex += chunkSize;
			endIndex += chunkSize;
		}
		// Wait for threads to finish
		for (Thread t : threadList) {
			t.join();
		}

		System.out.println("Password found in: " + (System.currentTimeMillis() - currTime) + " milliseconds.");

	}

	/**
	 * Very sad and ugly non-recursive password generator that creates all potential
	 * 3 length passwords
	 */
	public static void passwordGeneratorLengthThree() {
		for (char a = 'a'; a <= 'z'; a++) {

			for (char b = 'a'; b <= 'z'; b++) {

				for (char c = 'a'; c <= 'z'; c++) {
					String temp = Character.toString(a);
					temp += Character.toString(b);
					temp += Character.toString(c);
					passwordList.add(temp);
				}
			}
		}

	}

	/**
	 * Much sadder and uglier non-recursive password generator for passwords of
	 * length 5
	 */
	public static void passwordGeneratorLengthFive() {
		for (char a = 'a'; a <= 'z'; a++) {

			for (char b = 'a'; b <= 'z'; b++) {

				for (char c = 'a'; c <= 'z'; c++) {

					for (char d = 'a'; d <= 'z'; d++) {

						for (char e = 'a'; e <= 'z'; e++) {
							String temp = Character.toString(a);
							temp += Character.toString(b);
							temp += Character.toString(c);
							temp += Character.toString(d);
							temp += Character.toString(e);
							passwordList.add(temp);
						}
					}
				}
			}
		}

	}

	/**
	 * Helper method that tests each password entered
	 * 
	 * @param s password to check
	 * @return whether password was correct
	 */
	public static boolean correctPasswordCheck(String s) {
		try {
			ZipFile zipFile = new ZipFile("protected3.zip");
			zipFile.setPassword(s);
			zipFile.extractAll("contents");
			System.out.println("Successfully cracked!");
		} catch (ZipException ze) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void setCheck() {
		passwordFound = true;
	}

	public static boolean getCheck() {
		return passwordFound;
	}
}
