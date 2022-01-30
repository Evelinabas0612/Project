import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Encrypt {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя .,-:;?!0123456789";

    public static void methodEncrypt() {
        try {
            String cypherText = "";
            String cypherTextAll = "";
            Scanner scannerEncrypt = new Scanner(System.in);
            System.out.println("Введите ключ расшифрования:");
            String str = scannerEncrypt.nextLine();
            int step = Integer.parseInt(str);
            System.out.println("Введите имя файла и путь:");

            List<String> lines = Files.readAllLines(Paths.get(scannerEncrypt.nextLine()));
            System.out.println("Как назвать новый файл?");
            FileWriter fileWriterEncrypt = new FileWriter(scannerEncrypt.nextLine());
            for (String line : lines) {

                char[] buffer = line.toCharArray();
                char[] cypherCode = ALPHABET.toCharArray();

                for (int i = 0; i < buffer.length; i++) {
                    for (int j = cypherCode.length - 1; j >= 0; j--) {
                        if (buffer[i] == cypherCode[j]) {
                            j = j - step;
                            if (j == -1) {
                                j = cypherCode.length - 1;
                            }
                            if (j < -1) {
                                j = j + cypherCode.length;

                            }


                            buffer[i] = cypherCode[j];
                        }

                    }

                }

                cypherText = String.valueOf(buffer);
                if (cypherTextAll.equalsIgnoreCase("")) {

                    cypherTextAll = cypherText + "\n";
                } else {
                    cypherTextAll = cypherTextAll + cypherText + "\n";
                }

            }
            fileWriterEncrypt.write(cypherTextAll);
            System.out.println("Готово!");
            fileWriterEncrypt.close();
            scannerEncrypt.close();


        } catch (Exception e) {
            System.out.println("что-то не то" + e);
        }


    }

}





