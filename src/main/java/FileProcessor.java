import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки файлов
 */
public class FileProcessor {
    private String directoryName;
    private List<File> files;
    public FileProcessor() {
        this.files = new ArrayList<>();
    }

    /**
     * Получает имя директории.
     *
     * @return возвращает имя директории с файлами
     */
    public String getDirectoryName() {
        return directoryName;
    }

    /**
     * Устанавливает имя директории для обработки файлов
     *
     * @param directoryName заданное имя директории
     */
    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    /**
     * Устанавливает список файлов для обработки.
     *
     * @param files список файлов для обработки
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    /**
     * Запрашивает ввод директории для обработки.
     * Ввод директории до тех пор, пока не будет введен существующее имя директории.
     *
     * @return имя директории для обработки
     */
    public String inputDirectoryName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String directoryName = null;
        while (directoryName == null) {
            try {
                System.out.println("Enter directory name: ");
                directoryName = reader.readLine();
                File directory = new File(directoryName);
                if (!directory.exists()) {
                    System.out.println("Directory not exists. Repeat input");
                    directoryName = null;
                }
            } catch (IOException ex) {
                System.out.println("Error input. Repeat input.");
            }
        }
        return directoryName;
    }

    /**
     * Возвращает массив файлов в целевой директории.
     *
     * @return возвращает массив файлов в целевой директории
     */
    public File[] getFiles() {
        File folder =  new File(directoryName);
        if (folder.isDirectory()) {
            return folder.listFiles();
        } else {
            return new File[0];
        }
    }

    @Override
    public String toString() {
        return "FileReader{" +
                "files=" + files +
                '}';
    }

}
