import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VigenereCipher {
    static char[] alphabet = new char[26];
    static char[][] vigenere = new char[26][26];
    static char[][] keyWord;
    static String options;
    static String key, word;
    static String answer = "";
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 26; i++) {
            alphabet[i] = (char) ('a' + i);
        }
        System.out.println("Введите 1, если надо зашифровать слово по ключу или любой другой символ, чтобы расшифровать слово по ключу: ");
        options = reader.readLine();
        if (options.equals("1")) {
            System.out.println("Введите слово: ");
            word = reader.readLine();
            System.out.println("Введите ключ: ");
            key = reader.readLine();
            keyWord = new char[2][word.length()];
            table();
            encrypt();
        } else if (options.equals("2")) {
            System.out.println("Введите слово: ");
            word = reader.readLine();
            System.out.println("Введите ключ: ");
            key = reader.readLine();
            keyWord = new char[2][word.length()];
            table();
            decrypt();
        }
    }

    static void table() {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (j + i >= 26) {
                    vigenere[i][j] = alphabet[j + i - 26];
                } else vigenere[i][j] = alphabet[j + i];
            }
        }
    }

    static void encrypt() {
        createArray();
        findCoordinatesEncryption();
        System.out.println(answer);
    }

    static void decrypt() {
        createArray();
        findCoordinatesDecryption();
        System.out.println(answer);
    }

    static void createArray() {
        int k = 0;
        int q = 0;
        while (word.length() != q) {
            keyWord[0][q] = word.charAt(q);
            keyWord[1][q] = key.charAt(k);
            k++;
            q++;
            if (k >= key.length()) k = 0;
        }
    }

    static void findCoordinatesEncryption() {
        int number = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (keyWord[0][number] == alphabet[i]) {
                for (int k = 0; k < alphabet.length; k++) {
                    if (keyWord[1][number] == alphabet[k]) {
                        findSymbolEncryption(i, k);
                        number++;
                        i = -1;
                        if (number >= word.length()) return;
                        break;
                    }
                }
            }
        }
    }

    static void findCoordinatesDecryption() {
        int number = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (keyWord[1][number] == alphabet[i]) {
                findSymbolDecryption(i, number);
                number++;
                i = 1;
                if (number >= word.length()) return;
            }
        }
    }

    static void findSymbolEncryption(int first, int second) {
        answer += vigenere[first][second];
    }

    static void findSymbolDecryption(int first, int number) {
        for (int i = 0; i < 26; i++) {
            if(vigenere[first][i] == keyWord[0][number]) {
                answer += vigenere[0][i];
                return;
            }
        }
    }
}
