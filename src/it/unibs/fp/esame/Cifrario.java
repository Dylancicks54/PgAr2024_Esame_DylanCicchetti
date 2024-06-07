package it.unibs.fp.esame;

import java.util.Scanner;

/**
 * La classe Cifrario permette di decifrare parole cifrate utilizzando una chiave di cifratura.
 * È possibile inserire più parole cifrate e le rispettive chiavi, e il programma decifrerà ogni parola
 * utilizzando la chiave corrispondente.
 */
public class Cifrario {

    /**
     * Metodo principale per decifrare le parole cifrate utilizzando le chiavi fornite.
     * Legge il numero di casi di test, le parole cifrate e le relative chiavi da input, quindi decifra ogni parola
     * e stampa il risultato decifrato.
     */
    public void cifra() {
        Scanner scanner = new Scanner(System.in);

        // Legge il numero di casi di test
        int N = scanner.nextInt();
        scanner.nextLine();  // Consuma il carattere newline

        // Legge le parole cifrate
        String[] encryptedWords = new String[N];
        for (int i = 0; i < N; i++) {
            encryptedWords[i] = scanner.next();
        }

        // Legge le chiavi
        String[] keys = new String[N];
        for (int i = 0; i < N; i++) {
            keys[i] = scanner.next();
        }

        // Decifra ogni parola e stampa il risultato
        for (int i = 0; i < N; i++) {
            String decryptedWord = decrypt(encryptedWords[i], keys[i]);
            System.out.println(decryptedWord);
        }
        System.out.flush(); // Assicura che tutti i dati vengano stampati immediatamente
    }

    /**
     * Metodo per decifrare una parola cifrata utilizzando la chiave fornita.
     *
     * @param encryptedWord La parola cifrata da decifrare.
     * @param key           La chiave di cifratura utilizzata per decifrare la parola.
     * @return              La parola decifrata.
     */
    private static String decrypt(String encryptedWord, String key) {
        StringBuilder decryptedWord = new StringBuilder();

        for (int i = 0; i < encryptedWord.length(); i++) {
            char encryptedChar = encryptedWord.charAt(i);
            char keyChar = key.charAt(i % key.length());

            // Determina lo spostamento (keyChar - 'a')
            int shift = keyChar - 'a';

            // Decifra il carattere
            char decryptedChar;
            if (Character.isUpperCase(encryptedChar)) {
                decryptedChar = (char) ('A' + (encryptedChar - 'A' - shift + 26) % 26);
            } else if (Character.isLowerCase(encryptedChar)) {
                decryptedChar = (char) ('a' + (encryptedChar - 'a' - shift + 26) % 26);
            } else {
                decryptedChar = encryptedChar; // I caratteri non alfabetici non vengono decifrati
            }

            decryptedWord.append(decryptedChar);
        }

        return decryptedWord.toString();
    }

}
