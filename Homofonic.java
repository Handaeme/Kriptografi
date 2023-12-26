
public class Homofonic {
    public static void main(String[] args) {
        // Pasangan karakter dan substitusi
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String substitutions = "12345678910!@#$%^&*()-+<>?;{}|";

        String message = "Hanan Nadhif"; // Pesan yang akan dienkripsi
        StringBuilder encryptedMessage = new StringBuilder();

        // Konversi karakter dan substitusi ke huruf kecil
        characters = characters.toLowerCase();
        message = message.toLowerCase();

        // Proses enkripsi
        for (char c : message.toCharArray()) {//message dibuat per huruf
            int index = characters.indexOf(c);//h dicek apakah ada di index characters atau tidak
            if (index != -1) {
                encryptedMessage.append(substitutions.charAt(index));
            } else {
                encryptedMessage.append(c);
            }
        }
         

        System.out.println("Pesan Asli: " + message);
        System.out.println("Pesan Terenkripsi: " + encryptedMessage.toString());

        // Proses dekripsi
        StringBuilder decryptedMessage = new StringBuilder();
        for (char c : encryptedMessage.toString().toCharArray()) {
            int index = substitutions.indexOf(c);
            if (index != -1) {
                decryptedMessage.append(characters.charAt(index));
            } else {
                decryptedMessage.append(c);
            }
        }

        System.out.println("Pesan Terdekripsi: " + decryptedMessage.toString());
    }
}
