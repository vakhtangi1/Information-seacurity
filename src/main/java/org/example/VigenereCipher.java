package org.example;

import java.util.Base64;
import java.util.Scanner;

public class VigenereCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input text and key
        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        System.out.print("Enter key: ");
        String key = scanner.nextLine();

        // Encrypt the text
        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted: " + encryptedText);

        // Decrypt the text
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }

    // Array to store alphabetic characters (A-Z, a-z)
    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    // Encrypt the text using Vigenère cipher
    public static String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            // Encrypt letters using the alphabet array
            if (Character.isLetter(plainChar)) {
                char base = Character.isUpperCase(plainChar) ? 'A' : 'a';
                int shifted = (plainChar - base + keyChar - base) % 26;
                if (shifted < 0) shifted += 26; // Handle negative values
                encryptedText.append(alphabet[shifted + (Character.isUpperCase(plainChar) ? 0 : 26)]);
            } else {
                // Encrypt non-alphabetic characters using ASCII
                int shifted = (plainChar + keyChar) % 256;
                encryptedText.append((char) shifted);
            }
        }

        // Convert the encrypted text to Base64 to make it more readable
        return Base64.getEncoder().encodeToString(encryptedText.toString().getBytes());
    }

    // Decrypt the text using Vigenère cipher
    public static String decrypt(String text, String key) {
        // Decode the Base64 encrypted text
        String decodedText = new String(Base64.getDecoder().decode(text));

        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < decodedText.length(); i++) {
            char encryptedChar = decodedText.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            // Decrypt letters using the alphabet array
            if (Character.isLetter(encryptedChar)) {
                char base = Character.isUpperCase(encryptedChar) ? 'A' : 'a';
                int shifted = (encryptedChar - base - (keyChar - base) + 26) % 26;
                decryptedText.append(alphabet[shifted + (Character.isUpperCase(encryptedChar) ? 0 : 26)]);
            } else {
                // Decrypt non-alphabetic characters using ASCII
                int shifted = (encryptedChar - keyChar + 256) % 256;
                decryptedText.append((char) shifted);
            }
        }

        return decryptedText.toString();
    }
}