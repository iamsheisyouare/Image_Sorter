import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// TODO
//  1.Удаление файлов: нужно ли оно?;
//  2.Написать тесты;
//  3.Указать JavaDoc;

/**
 * Класс для сортировки файлов.
 */
public class ImageSorter {

    /**
     * Сортировка изображения по году и месяцу.
     *
     * @param imageFile исходный файл
     */
    public void sortImage(File imageFile) {
        if (imageFile == null || !imageFile.isFile()) {
            System.out.println("Invalid image file");
            return;
        }

        String fileName = imageFile.getName();
        String[] parts = fileName.split("_");
        if(parts.length < 3) {
            System.out.println("Invalid fileName format " + fileName);
            return;
        }
        String yearString = parts[0];
        String monthString = parts[1];

        int year;
        int month;

        try {
            year = Integer.parseInt(yearString);
            month = Integer.parseInt(monthString);
        } catch (NumberFormatException ex) {
            System.out.println("Failed to parse year or month from " + fileName);
            return;
        }
        // Создание имени директории для сортировки файлов
        Path destinationDirectory = Paths.get(imageFile.getParent(), String.valueOf(year), String.valueOf(month));

        try {
            // Проверяем существует ли директория
            if (!Files.exists(destinationDirectory)) {
                Files.createDirectories(destinationDirectory);
            }
            // Присваиваем путь для сохранения файла с учетом пути директории
            Path destinationFile = destinationDirectory.resolve(imageFile.getName());
            // Перемещаем файл
            Files.move(imageFile.toPath(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File sorted succesfully " + imageFile.getName());
        } catch (Exception ex) {
            System.out.println("Error sorting imageFile " + imageFile.getName());
            ex.printStackTrace();
        }
    }
}
