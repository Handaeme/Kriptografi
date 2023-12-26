public class PlayfairCipher {
    private char[][] matrix;

    public PlayfairCipher(String key) {
        prepareMatrix(key);
    }

    private void prepareMatrix(String key) {
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        key = key + alphabet;
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.chars().distinct()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        matrix = new char[5][5];
        int keyIndex = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                matrix[row][col] = key.charAt(keyIndex);
                keyIndex++;
            }
            
        }
    }

    public void printMatrix() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private String prepareText(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder preparedText = new StringBuilder(plaintext);
        for (int i = 0; i < preparedText.length() - 1; i += 2) {
            if (preparedText.charAt(i) == preparedText.charAt(i + 1)) {
                preparedText.insert(i + 1, 'X');
            }
        }
        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }
        return preparedText.toString();
    }

    public String encrypt(String plaintext) {
        plaintext = prepareText(plaintext);
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char char1 = plaintext.charAt(i);
            char char2 = plaintext.charAt(i + 1);

            int[] pos1 = findCharPosition(char1);
            int[] pos2 = findCharPosition(char2);

            int row1 = pos1[0];//row 1 berisi H yang dimana baris 2
            int col1 = pos1[1];
            int row2 = pos2[0];//row 2 berisi A yang diaman baris 0
            int col2 = pos2[1];
      

            // Cetak indeks baris dan kolom karakter pertama dan kedua dalam pasangan
            System.out.println("Pair: " + char1 + char2);
            System.out.println("Character 1 - Row: " + row1 + ", Column: " + col1);
            System.out.println("Character 2 - Row: " + row2 + ", Column: " + col2);
            char encryptedChar1, encryptedChar2;

            if (row1 == row2) {
                encryptedChar1 = matrix[row1][(col1 + 1) % 5];
                encryptedChar2 = matrix[row2][(col2 + 1) % 5];
                System.out.println("1");
            } else if (col1 == col2) {
                encryptedChar1 = matrix[(row1 + 1) % 5][col1];
                encryptedChar2 = matrix[(row2 + 1) % 5][col2];
                System.out.println("2");
            } else {
                encryptedChar1 = matrix[row1][col2];
                encryptedChar2 = matrix[row2][col1];
                System.out.println("3");
            }

            ciphertext.append(encryptedChar1).append(encryptedChar2);
        }

        return ciphertext.toString();
    }

    public void printTextPairs(String plaintext) {
        plaintext = prepareText(plaintext);
        for (int i = 0; i < plaintext.length(); i += 2) {
            if (i + 1 < plaintext.length()) {
                System.out.println(plaintext.charAt(i) + "" + plaintext.charAt(i + 1));
            }
        }
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char char1 = ciphertext.charAt(i);
            char char2 = ciphertext.charAt(i + 1);

            int[] pos1 = findCharPosition(char1);
            int[] pos2 = findCharPosition(char2);

            int row1 = pos1[0];
            int col1 = pos1[1];
            int row2 = pos2[0];
            int col2 = pos2[1];


            char decryptedChar1, decryptedChar2;

            if (row1 == row2) {
                decryptedChar1 = matrix[row1][(col1 + 4) % 5];
                decryptedChar2 = matrix[row2][(col2 + 4) % 5];
            } else if (col1 == col2) {
                decryptedChar1 = matrix[(row1 + 4) % 5][col1];
                decryptedChar2 = matrix[(row2 + 4) % 5][col2];
            } else {
                decryptedChar1 = matrix[row1][col2];
                decryptedChar2 = matrix[row2][col1];
            }

            plaintext.append(decryptedChar1).append(decryptedChar2);
        }

        return plaintext.toString().replace("X", "");
    }

    private int[] findCharPosition(char c) {
        int[] pos = new int[2];

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col] == c) {
                    pos[0] = row;
                    pos[1] = col;
                    return pos;
                }
            }
        }

        return pos;
    }

    public static void main(String[] args) {
        String key = "EXAMPLEKEY"; // Kunci yang sudah disediakan
        PlayfairCipher playfair = new PlayfairCipher(key);

        String plaintext = "HANAN"; // Teks yang akan dienkripsi
        String encryptedText = playfair.encrypt(plaintext);

        System.out.println("Matriks 5x5:");
        playfair.printMatrix();


        playfair.printTextPairs(plaintext);

        System.out.println("Teks terenkripsi: " + encryptedText);

        String decryptedText = playfair.decrypt(encryptedText);
        System.out.println("Teks terdekripsi: " + decryptedText);


    }
}
