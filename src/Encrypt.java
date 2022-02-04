import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Encrypt {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя .,-:;?!0123456789";

    public static void methodEncrypt() {
        try (Scanner scannerEncrypt = new Scanner(System.in);
             FileWriter fileWriterEncrypt = new FileWriter(scannerEncrypt.nextLine())) {
            String cypherText = "";
            StringBuilder cypherTextAll = new StringBuilder();

            System.out.println("Введите ключ расшифрования:");
            int key = Integer.parseInt(scannerEncrypt.nextLine());

            System.out.println("Введите имя файла и путь:");
            List<String> lines = Files.readAllLines(Paths.get(scannerEncrypt.nextLine()));

            System.out.println("Как назвать новый файл?");

            for (String line : lines) {
                char[] arrayChars = line.toCharArray();
                char[] newArray = new char[arrayChars.length];

                for (int i = 0; i < arrayChars.length; i++) {
                    int indexALPHABET = ALPHABET.indexOf(arrayChars[i]);
                    int newIndex = indexALPHABET + key % ALPHABET.length();
                    char newChar = ALPHABET.charAt(newIndex);
                    newArray[i] = newChar;
                }

                cypherText = String.valueOf(newArray);
                if (cypherTextAll.toString().equalsIgnoreCase("")) {
                    cypherTextAll = new StringBuilder(cypherText + System.lineSeparator());
                } else {
                    cypherTextAll.append(cypherText).append(System.lineSeparator());
                }
            }
            fileWriterEncrypt.write(cypherTextAll.toString());
            System.out.println("Готово!");

        } catch (Exception e) {
            System.out.println("что-то не то" + e);
        }
    }
}





