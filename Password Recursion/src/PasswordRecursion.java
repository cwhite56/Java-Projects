import java.util.ArrayList;

public class PasswordRecursion {
    public static ArrayList<String> passwords = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        String s = "";
        generator(3, s);
        print(passwords);
        System.out.println(passwords.size());
    }
/**
 * Method for generator every possible password with n lowercase characters
 * @param length of the password
 * @param temp temporary string
 */
    public static void generator(int length, String temp) {
        // base case
        if( length == 0) {
            passwords.add(temp);
            return;
        }
        // general case
        for (char i = 'a'; i <= 'z'; i++) {
            generator(length - 1, temp + i);
        }
    }
/**
 * Method to print out the list of passwords generated
 * @param list of passwords
 */
    public static void print(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
