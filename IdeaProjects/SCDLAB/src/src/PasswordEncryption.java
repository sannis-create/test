//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Scanner;
//public class PasswordEncryption
//{
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Please Enter a Sentence: ");
//        String inputSentence = scanner.nextLine();
//
//        // Encrypt the input sentence
//        String encryptedSentence = encrypt(inputSentence);
//
//        // Write the encrypted sentence to a file
//        try {
//            FileWriter fileWriter = new FileWriter("encrypt.txt");
//            fileWriter.write(encryptedSentence);
//            fileWriter.close();
//            System.out.println("The encrypted sentence has been saved to 'encrypt.txt'.");
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing to the file.");
//            return;
//        }
//
//        // Decrypt and display the encrypted sentence
//        String decryptedSentence = decrypt(encryptedSentence);
//        System.out.println("Encrypted Sentence: " + encryptedSentence);
//        System.out.println("Decrypted Sentence: " + decryptedSentence);
//    }
//
//    // Simple XOR encryption for a string
//    public static String encrypt(String input) {
//        char[] chars = input.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            chars[i] = (char) (chars[i] ^ 3);
//        }
//        return new String(chars);
//    }
//
//    // Simple XOR decryption for a string
//    public static String decrypt(String input) {
//        return encrypt(input); // XOR decryption is the same as encryption for this case
//    }
//}
