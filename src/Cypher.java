import java.util.Scanner;

public class Cypher {

    public static void main(String[] args) {

        System.out.println("Вас приветствует MrCypher!");
        System.out.println("Выберите операцию:");
        System.out.println("1 - Зашифровать");
        System.out.println("2 - Расшифровать");
        System.out.println("3 - Метод брутфорс");
        System.out.println("4 - Метода статистического анализа");
        System.out.println("0 - Выход из программы\n");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("0")) {
                    System.out.println("До свидания!");
                    break;
                } else if (answer.equalsIgnoreCase("1")) {
                    Decrypt decrypt = new Decrypt();
                    decrypt.methodDecrypt();
                    break;
                } else if (answer.equalsIgnoreCase("2")) {
                    Encrypt encrypt = new Encrypt();
                    encrypt.methodEncrypt();
                    break;
                } else if (answer.equalsIgnoreCase("3")) {
                    Brutforce brutforce = new Brutforce();
                    brutforce.methodBrutforce();
                    break;
                } else if (answer.equalsIgnoreCase("4")) {
                    Analysis analysis = new Analysis();
                    analysis.methodAnalysis();
                    break;
                } else {
                    System.out.println("Проверьте правильно ли вы ввели команду. Начните сначала");
                }

            }
            scanner.close();

        } catch (Exception e) {
            System.out.println();
            System.out.println("Что-то не то! Проверьте условия ещё раз и запустите повторно! " + e);
        }

    }
}


