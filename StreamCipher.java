public class StreamCipher {
    public static void main(String[] args) {

        // Contoh plaintext "AKU"
        String plaintext = "AKU";
        printOut("Plaintext: " + plaintext);

        // Kunci stream cipher diambil dari nilai ASCII karakter 'K'
        String key = String.valueOf((int) 'K');
        printOut("Key: " + key);

        // Proses enkripsi
        String ciphertext = encrypt(plaintext, key);
        printOut("Ciphertext: " + ciphertext);

        // Proses dekripsi
        String decryptedText = decrypt(ciphertext, key);
        printOut("Decrypted Text: " + decryptedText);
    }

    // Fungsi untuk melakukan enkripsi dengan stream cipher menggunakan XOR
    private static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % key.length()); // Mengulang kunci jika panjang plaintext > panjang kunci
            char encryptedChar = (char) (plainChar ^ keyChar); // Operasi XOR
            int asciiValuePlain = (int) plainChar; // Nilai ASCII karakter plaintext
            int asciiValueKey = (int) keyChar; // Nilai ASCII karakter kunci
            int asciiValueEncrypted = (int) encryptedChar; // Nilai ASCII karakter hasil enkripsi
            String binaryAsciiPlain = Integer.toBinaryString(asciiValuePlain); // Nilai ASCII dalam bentuk biner
            String binaryAsciiKey = Integer.toBinaryString(asciiValueKey); // Nilai ASCII kunci dalam bentuk biner
            String binaryAsciiEncrypted = Integer.toBinaryString(asciiValueEncrypted); // Nilai ASCII hasil enkripsi dalam bentuk biner

            // printOut("Character: " + plainChar + ", ASCII: " + asciiValuePlain +
            //         ", Binary: " + binaryAsciiPlain +
            //         " XOR " + keyChar + " (" + asciiValueKey + "), Binary: " + binaryAsciiKey +
            //         " = " +
            //         encryptedChar + " (" + asciiValueEncrypted + "), Binary: " + binaryAsciiEncrypted);
            System.out.println("Character: " + plainChar + "  ,Ciphertext : " + encryptedChar);

            ciphertext.append(encryptedChar);
        }
        return ciphertext.toString();
    }

    // Fungsi untuk melakukan dekripsi dengan stream cipher menggunakan XOR
    private static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char encryptedChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i % key.length()); // Mengulang kunci jika panjang ciphertext > panjang kunci
            char decryptedChar = (char) (encryptedChar ^ keyChar); // Operasi XOR
            int asciiValueEncrypted = (int) encryptedChar; // Nilai ASCII karakter hasil enkripsi
            int asciiValueKey = (int) keyChar; // Nilai ASCII karakter kunci
            int asciiValueDecrypted = (int) decryptedChar; // Nilai ASCII karakter hasil dekripsi
            String binaryAsciiEncrypted = Integer.toBinaryString(asciiValueEncrypted); // Nilai ASCII hasil enkripsi dalam bentuk biner
            String binaryAsciiKey = Integer.toBinaryString(asciiValueKey); // Nilai ASCII kunci dalam bentuk biner
            String binaryAsciiDecrypted = Integer.toBinaryString(asciiValueDecrypted); // Nilai ASCII hasil dekripsi dalam bentuk biner

            // printOut("Character: " + encryptedChar + ", ASCII: " + asciiValueEncrypted +
            //         ", Binary: " + binaryAsciiEncrypted +
            //         " XOR " + keyChar + " (" + asciiValueKey + "), Binary: " + binaryAsciiKey +
            //         " = " +
            //         decryptedChar + " (" + asciiValueDecrypted + "), Binary: " + binaryAsciiDecrypted);
            System.out.println("Character: " + encryptedChar + "  ,Dekrip: " + decryptedChar);
            decryptedText.append(decryptedChar);
        }
        return decryptedText.toString();
    }

    // Fungsi khusus untuk mencetak output
    private static void printOut(String output) {
        System.out.println("=== Custom Output ===");
        System.out.println(output);
        System.out.println("=====================");
        System.out.println(" ");
        
    }
}
