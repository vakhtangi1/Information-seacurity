package org.example;

import java.util.Scanner;

public class VigenereCipher {
    private static final char[] ALPHABET = generateAlphabet();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    public static String encrypt(String text, String key) {
        return processText(text, key, true);
    }

    public static String decrypt(String text, String key) {
        return processText(text, key, false);
    }

    private static String processText(String text, String key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        char[] textArray = text.toCharArray();
        char[] keyArray = key.toCharArray();
        int keyLength = key.length();

        for (int i = 0; i < textArray.length; i++) {
            char textChar = textArray[i];
            char keyChar = keyArray[i % keyLength];
            char processedChar = vigenereCipher(textChar, keyChar, encrypt);
            result.append(processedChar);
        }
        return result.toString();
    }

    private static char vigenereCipher(char textChar, char keyChar, boolean encrypt) {
        int alphabetSize = ALPHABET.length;
        int textIndex = findIndex(textChar);
        int keyIndex = findIndex(keyChar);

        if (textIndex == -1) return textChar;

        int newIndex = encrypt
                ? (textIndex + keyIndex) % alphabetSize
                : (textIndex - keyIndex + alphabetSize) % alphabetSize;

        return ALPHABET[newIndex];
    }

    private static char[] generateAlphabet() {
        char[] alphabet = new char[95];
        for (int i = 0; i < 95; i++) {
            alphabet[i] = (char) (i + 32);
        }
        return alphabet;
    }

    private static int findIndex(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == c) return i;
        }
        return -1;
    }
}