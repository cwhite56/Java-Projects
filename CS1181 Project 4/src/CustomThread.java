import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

/**
 * Custom thread class used to test various sections of a password list
 */
public class CustomThread extends Thread {
    private ArrayList<String> passwordChunk;
    private volatile static int i = 0;

    public CustomThread(ArrayList<String> passwordChunk) {
        this.passwordChunk = passwordChunk;

    }

    /**
     * run method that checks each password from a threads assigned chunk of the
     * overall password list
     */
    @Override
    public void run() {
        // Create file cache
        String destinationFilename = this.cacheFile();

        // Check through all passwords
        for (int j = 0; j < passwordChunk.size(); j++) {

            boolean check = correctPasswordCheck(passwordChunk.get(j), destinationFilename, i);
            if (check) {
                Example.setCheck();
                System.out.println("Password: " + passwordChunk.get(j));
            }
            // Password found
            if (Example.getCheck()) {
                break;
            }
        }

        this.cacheCleanup(destinationFilename);
    }

    /**
     * Method that creates a copy of the file to be cracked for each thread to work
     * on
     */
    public String cacheFile() {
        // I used generative AI to assist in the implementation of the provided copy
        // code
        String srcFilename = "protected5.zip";
        String destinationFilename = "protected5temp" + i + ".zip";
        i++;

        try {
            Files.copy(Path.of(srcFilename), Path.of(destinationFilename));
            System.out.println("File copied successfully.");
        } catch (Exception e) {
            System.out.println("File copy failed.");
        }
        return destinationFilename;
    }

    /**
     * Method that deletes cached copies of files once used
     * 
     * @param destinationFilename file to delete
     */
    public void cacheCleanup(String destinationFilename) {
        // Delete file when done
        try {
            Files.delete(Path.of(destinationFilename));
            System.out.println("File deleted successfully");
        } catch (IOException e) {
            System.out.println("File delete failed.");
        }
    }

    public boolean correctPasswordCheck(String s, String destinationFilename, int i) {
        try {
            ZipFile zipFile = new ZipFile(destinationFilename);
            zipFile.setPassword(s);
            zipFile.extractAll("contents" + i);
            System.out.println("Successfully cracked!");
        } catch (ZipException ze) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
