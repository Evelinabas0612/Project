import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Decrypt {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя .,-:;?!0123456789";

    public static void methodDecrypt() {

        try (Scanner scannerDecrypt = new Scanner(System.in)) {
            String cypherText = "";
            StringBuilder cypherTextAll = new StringBuilder();

            System.out.println("Введите ключ шифрования:");
            int key = Integer.parseInt(scannerDecrypt.nextLine());

            System.out.println("Введите имя файла и путь:");
            List<String> lines = Files.readAllLines(Paths.get(scannerDecrypt.nextLine()));

            System.out.println("Как назвать новый файл?");
            FileWriter fileWriterDecrypt = new FileWriter(scannerDecrypt.nextLine());
            for (String line : lines) {

                char[] buffer = line.toLowerCase().toCharArray();
                char[] cypherCode = ALPHABET.toCharArray();

                for (int i = 0; i < buffer.length; i++) {
                    for (int j = 0; j < cypherCode.length; j++) {
                        if (buffer[i] == cypherCode[j]) {
                            j = j + key;
                            if (j == cypherCode.length) {
                                j = 0;
                            }
                            if (j > cypherCode.length) {
                                j = j - cypherCode.length;
                            }
                            buffer[i] = cypherCode[j];
                        }
                    }
                }
                cypherText = String.valueOf(buffer);
                if (cypherTextAll.toString().equalsIgnoreCase("")) {
                    cypherTextAll = new StringBuilder(cypherText + System.lineSeparator());
                } else {
                    cypherTextAll.append(cypherText).append(System.lineSeparator());
                }
            }
            fileWriterDecrypt.write(cypherTextAll.toString());
            System.out.println("Готово!");
            fileWriterDecrypt.close();
        } catch (IOException e) {
            System.out.println("Что-то не то! Попробуйте ещё раз" + e);
        }
    }
}

