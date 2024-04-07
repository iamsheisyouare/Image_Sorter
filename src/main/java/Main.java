import java.io.File;
import java.util.Scanner;

// TODO
//  1.Удаление файлов: нужно ли оно?;
//  2. Тесты не для ImageProcessor yе все работают, не срабатывает mock;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileProcessor fileProcessor = new FileProcessor();
        String directoryName = fileProcessor.inputDirectoryName();
        fileProcessor.setDirectoryName(directoryName);
//        // Получение списка файлов
//        File[] files = fileProcessor.getFiles();
//
//        // Вывод содержимого списка файлов
//        System.out.println("Files in the directory:");
//        for (File file : files) {
//            System.out.println(file.getName()); // или другая информация о файле
//        }
        System.out.println("Delete processed files? (yes/no)");
        String choice = scanner.nextLine().toLowerCase();

        boolean deleteProcessedFiles;
        if (choice.equals("yes")) {
            deleteProcessedFiles = true;
        } else if (choice.equals("no")) {
            deleteProcessedFiles = false;
        } else {
            System.out.println("Invalid choice. Assuming 'no'.");
            deleteProcessedFiles = false;
        }

        ImageProcessor imageProcessor = new ImageProcessor(fileProcessor, deleteProcessedFiles);
        imageProcessor.processImage();
    }
}
