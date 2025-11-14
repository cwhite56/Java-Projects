import java.util.ArrayList;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

// Custom class that represents the brain of my password cracker
// 3 Threads: I was working on this the day it was due. 4 threads took 4 hours. 
// Unfortuneately I did not have time to run the 3 thread version before the deadline
// 4 Threads: 15013840 milliseconds
public class PasswordCracker {
	public static ArrayList<String> passwordList = new ArrayList<>();
	private volatile static boolean passwordFound = false;

	public static void main(String[] args) throws Exception {
		// Part A
		long currTime = System.currentTimeMillis();
		String temp = "";
		passwordGenerator(3, temp);
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
		passwordGenerator(5, temp);
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
 * Method for generator every possible password with n lowercase characters
 * @param length of the password
 * @param temp temporary string
 */
    public static void passwordGenerator(int length, String temp) {
        // base case
        if( length == 0) {
            passwordList.add(temp);
            return;
        }
        // general case
        for (char i = 'a'; i <= 'z'; i++) {
            passwordGenerator(length - 1, temp + i);
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
