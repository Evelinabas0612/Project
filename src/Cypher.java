import java.util.Scanner;

public class Cypher {

    public static void main(String[] args) {
        System.out.println("Вас приветствует MrCypher! \n" +
                "Выберите операцию: \n" +
                "1 - Зашифровать \n" +
                "2 - Расшифровать \n" +
                "3 - Метод брутфорс \n" +
                "4 - Метода статистического анализа \n" +
                "0 - Выход из программы\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String answer = scanner.nextLine();

            switch (answer) {
                case ("1") -> Decrypt.methodDecrypt();
                case ("2") -> Encrypt.methodEncrypt();
                case ("3") -> Brutforce.methodBrutforce();
                case ("4") -> Analysis.methodAnalysis();
                default -> System.out.println("Проверьте правильно ли вы ввели команду. Начните сначала");
            }
            if (answer.equals("0")) {
                System.out.println("До свидания!");
                break;
            }
        }
    }
}


