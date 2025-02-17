package org.example;

import java.util.Scanner;

public class VigenereCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        System.out.print("Enter key: ");
        String key = scanner.nextLine();

        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }

    // Encrypt only letters (A-Z, a-z)
    public static String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(plainChar)) {
                char base = Character.isUpperCase(plainChar) ? 'A' : 'a';
                int shifted = ((plainChar - base) + (keyChar - base)) % 26;
                encryptedText.append((char) (base + shifted));
            } else {
                encryptedText.append(plainChar);
            }
        }
        return encryptedText.toString();
    }

    // Decrypt only letters (A-Z, a-z)
    public static String decrypt(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char encryptedChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(encryptedChar)) {
                char base = Character.isUpperCase(encryptedChar) ? 'A' : 'a';
                int shifted = ((encryptedChar - base) - (keyChar - base) + 26) % 26;
                decryptedText.append((char) (base + shifted));
            } else {
                decryptedText.append(encryptedChar);
            }
        }
        return decryptedText.toString();
    }
}
