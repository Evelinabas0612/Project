import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Brutforce {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя .,-:;?!0123456789";

    public static void methodBrutforce() {
        try (Scanner scannerBrutforce = new Scanner(System.in))  {
            int key = 1;

            System.out.println("Введите имя файла и путь:");

            List<String> lines = Files.readAllLines(Paths.get(scannerBrutforce.nextLine()));
            System.out.println("Как назвать новый файл?");


            FileWriter fileWriterBrutforce = new FileWriter(scannerBrutforce.nextLine());
            while (key <= ALPHABET.length()) {
                String cypherTextAll = "";
                String cypherText = "";

                for (String line : lines) {

                    char[] buffer = line.toCharArray();

                    char[] cypherCode = ALPHABET.toCharArray();

                    for (int i = 0; i < buffer.length; i++) {

                        for (int j = cypherCode.length - 1; j >= 0; j--) {
                            if (buffer[i] == cypherCode[j]) {

                                j = j - key;
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

                int first = cypherTextAll.indexOf(" ");
                int last = cypherTextAll.lastIndexOf(" ");


                int firstMidl = cypherTextAll.indexOf(" ", cypherTextAll.length() / 2);
                int lastMidl = cypherTextAll.lastIndexOf(" ", cypherTextAll.length() / 2);
                int count = 0;

                if (first < 20 && first > 0 && last > cypherTextAll.length() - 20 && last > 0) {
                    count++;
                }

                if (firstMidl < cypherTextAll.length() / 2 + 20 && lastMidl > cypherTextAll.length() / 2 - 20) {
                    count++;
                }
                if (cypherTextAll.charAt(0) != '.' && cypherTextAll.charAt(0) != ':'
                        && cypherTextAll.charAt(0) != ',' && cypherTextAll.charAt(0) != ';') {
                    count++;
                }

                if ( !cypherTextAll.contains(",:") && !cypherTextAll.contains(":,") && !cypherTextAll.contains(":;") && !cypherTextAll.contains(";:")) {
                    count++;
                }
                if (!cypherTextAll.contains(",!") && !cypherTextAll.contains("!,") && !cypherTextAll.contains("?;") && !cypherTextAll.contains(";?")) {
                    count++;
                }
                if (!cypherTextAll.contains(".?") && !cypherTextAll.contains(";.") && !cypherTextAll.contains(":.") && !cypherTextAll.contains(".!")) {
                    count++;
                }
                if (!cypherTextAll.contains("-:") && !cypherTextAll.contains("-.") && !cypherTextAll.contains("::") && !cypherTextAll.contains(";;")) {
                    count++;
                }
                if (count == 7) {
                    System.out.println(cypherTextAll);
                    System.out.println("Если ок, нажмите 1");
                    String answer = scannerBrutforce.nextLine();
                    if (answer.equalsIgnoreCase("1")) {
                        fileWriterBrutforce.write(cypherTextAll);
                        System.out.println("Ключ расшифровки: " + key);
                        System.out.println("Готово!");
                        fileWriterBrutforce.close();
                        break;
                    } else {
                        System.out.println("Попробуем с другим ключом!");
                        System.out.println("Если нет больше подходящего, программа завершится");
                    }
                }
                key++;
            }

        } catch (Exception e) {
            System.out.println("что-то не то" + e);
        }
    }
}


