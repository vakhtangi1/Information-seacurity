package org.example;

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

    // Encrypt only alphabetic characters (A-Z, a-z)
    public static String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            // Encrypt only alphabetic characters (A-Z, a-z)
            if (Character.isLetter(plainChar)) {
                char base = Character.isUpperCase(plainChar) ? 'A' : 'a';
                int shifted = (findIndex(plainChar) + findIndex(keyChar) - findIndex(base)) % 26;
                encryptedText.append(alphabet[shifted + (Character.isUpperCase(plainChar) ? 0 : 26)]);
            } else {
                encryptedText.append(plainChar);  // Keep non-alphabetic characters unchanged
            }
        }
        return encryptedText.toString();
    }

    // Decrypt only alphabetic characters (A-Z, a-z)
    public static String decrypt(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char encryptedChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            // Decrypt only alphabetic characters (A-Z, a-z)
            if (Character.isLetter(encryptedChar)) {
                char base = Character.isUpperCase(encryptedChar) ? 'A' : 'a';
                int shifted = (findIndex(encryptedChar) - findIndex(keyChar) + 26) % 26;
                decryptedText.append(alphabet[shifted + (Character.isUpperCase(encryptedChar) ? 0 : 26)]);
            } else {
                decryptedText.append(encryptedChar);  // Keep non-alphabetic characters unchanged
            }
        }
        return decryptedText.toString();
    }

    // Find the index of the character in the alphabet
    private static int findIndex(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1; // In case of non-alphabet characters
    }
}
