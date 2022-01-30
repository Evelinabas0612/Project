import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Analysis {
    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя .,-:;?!0123456789";

    public static void methodAnalysis() {
        HashMap<Character, Integer> mapLinesDecrypt = new HashMap<>();
        HashMap<Character, Integer> mapLinesEncrypt = new HashMap<>();
        char[] cypherCode = ALPHABET.toCharArray();
        int counterDecrypt = 0;
        int counterEncrypt = 0;
        String cypherText = "";
        String cypherTextAll = "";

        try {
            Scanner scannerAnalysis = new Scanner(System.in);

            System.out.println("Введите имя зашифрованного файла и путь:");
            List<String> linesDecrypt = Files.readAllLines(Paths.get(scannerAnalysis.nextLine()));

            for (String s : linesDecrypt) {
                char[] bufferDecrypt = s.toLowerCase().toCharArray();

                for (int i = 0; i < cypherCode.length; i++) {
                    int count = 0;
                    for (int j = 0; j < bufferDecrypt.length; j++) {

                        if (cypherCode[i] == bufferDecrypt[j]) {
                            count++;

                        }
                        counterDecrypt = counterDecrypt + count;
                        count = 0;
                    }

                    if (mapLinesDecrypt.containsKey(cypherCode[i])) {
                        int sum = mapLinesDecrypt.get(cypherCode[i]);
                        sum = sum + counterDecrypt;
                        mapLinesDecrypt.put(cypherCode[i], sum);

                    } else {
                        mapLinesDecrypt.put(cypherCode[i], counterDecrypt);
                    }
                    counterDecrypt = 0;
                }

            }
            System.out.println("Введите имя незашифрованного файла и путь:");
            List<String> linesEncrypt = Files.readAllLines(Paths.get(scannerAnalysis.nextLine()));

            for (String s : linesEncrypt) {

                char[] bufferEncrypt = s.toLowerCase().toCharArray();


                int count = 0;
                for (int i = 0; i < cypherCode.length; i++) {


                    for (int j = 0; j < bufferEncrypt.length; j++) {

                        if (cypherCode[i] == bufferEncrypt[j]) {
                            count++;

                        }
                        counterEncrypt = counterEncrypt + count;
                        count = 0;

                    }
                    if (mapLinesEncrypt.containsKey(cypherCode[i])) {
                        int sum = mapLinesEncrypt.get(cypherCode[i]);
                        sum = sum + counterEncrypt;
                        mapLinesEncrypt.put(cypherCode[i], sum);

                    } else {
                        mapLinesEncrypt.put(cypherCode[i], counterEncrypt);
                    }
                    counterEncrypt = 0;
                }


            }

            Set<Map.Entry<Character, Integer>> entriesDecrypt = mapLinesDecrypt.entrySet();
            Set<Map.Entry<Character, Integer>> entriesEncrypt = mapLinesEncrypt.entrySet();

            Map<Character, Integer> sortEntriesDecrypt = new LinkedHashMap<>();
            Map<Character, Integer> sortEntriesEncrypt = new LinkedHashMap<>();
            entriesDecrypt.stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                    .forEach(e -> sortEntriesDecrypt.put(e.getKey(), e.getValue()));
            entriesEncrypt.stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                    .forEach(e -> sortEntriesEncrypt.put(e.getKey(), e.getValue()));

            List<Character> keyListDecrypt = new ArrayList<>(sortEntriesDecrypt.keySet());
            List<Character> keyListEncrypt = new ArrayList<>(sortEntriesEncrypt.keySet());


            char[] newDecryptCode = new char[keyListDecrypt.size()];
            for (int i = 0; i < keyListDecrypt.size(); i++) {
                newDecryptCode[i] = keyListDecrypt.get(i);
            }
            char[] newEncryptCode = new char[keyListEncrypt.size()];
            for (int i = 0; i < keyListEncrypt.size(); i++) {
                newEncryptCode[i] = keyListEncrypt.get(i);
            }


            for (String s : linesDecrypt) {
                char[] buffer = s.toLowerCase().toCharArray();
                char[] newBuffer = new char[buffer.length];


                for (int j = 0; j < buffer.length; j++) {
                    for (int k = 0; k < newDecryptCode.length; k++) {
                        if (buffer[j] == newDecryptCode[k]) {
                            newBuffer[j] = newEncryptCode[k];
                        }


                    }


                }
                cypherText = String.valueOf(newBuffer);
                if (cypherTextAll.equalsIgnoreCase("")) {

                    cypherTextAll = cypherText + "\n";
                } else {
                    cypherTextAll = cypherTextAll + cypherText + "\n";
                }
            }


            System.out.println("Как назвать новый файл?");

            FileWriter fileWriterAnalysis = new FileWriter(scannerAnalysis.nextLine());
            fileWriterAnalysis.write(cypherTextAll);


            System.out.println("Готово!");
            fileWriterAnalysis.close();
            scannerAnalysis.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

