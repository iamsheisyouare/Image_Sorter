import com.drew.imaging.ImageProcessingException;

import java.io.File;
import java.io.IOException;

/**
 * Класс для обработки изображения.
 */
public class ImageProcessor {
    private FileProcessor fileProcessor;
    private boolean deleteProcessedFiles;
    public ImageProcessor(FileProcessor fileProcessor, boolean deleteProcessedFiles) {
        this.fileProcessor = fileProcessor;
        this.deleteProcessedFiles = deleteProcessedFiles;
    }

    /**
     * Метод для обработки изображения.
     */
    public void processImage() {
        // создаем массив изображений для обработки
        File[] imageFiles = fileProcessor.getFiles();
        if (imageFiles.length == 0) {
            System.out.println("No images in directory");
        }
        MetaDataReader metaDataReader = new MetaDataReader();
        ImageRenamer imageRenamer = new ImageRenamer();
        ImageSorter imageSorter = new ImageSorter();

        // создаем папку для изображений которые не смогут быть отсортированы
        File notSortedFolder = new File(fileProcessor.getDirectoryName() + File.separator + "notsorted");
        if(!notSortedFolder.exists()) {
            notSortedFolder.mkdirs();
        }

        // обрабатываем весь массив файлов изображений
        for (File imageFile : imageFiles) {
            try {
                // читаем метадату из изображения
                ImageMetadata imageMetadata = metaDataReader.readMetaData(imageFile);
                if (imageMetadata != null) {
                    // сохраняем имя файла и переименовываем файл
                    String newFileName = imageRenamer.generateNewFileName(imageMetadata, imageFile);
                    File renamedFile = imageRenamer.renameFile(imageFile, newFileName);
                    // сортируем файл по папкам
                    imageSorter.sortImage(renamedFile);
                } else {
                    // файлы без метадаты переносим в папку с неотсортированными изображениями
                    File destinationFile = new File(notSortedFolder.getAbsolutePath()
                            + File.separator + imageFile.getName());
                    System.out.println("File " + imageFile.getName() + " moves to 'notsorted' folder");
                        if(!imageFile.renameTo(destinationFile)) {
                        System.out.println("Failed to move file to 'notsorted' folder.");
                    }
                }

            } catch (ImageProcessingException | IOException ex) {
                System.err.println("Error processing image file: " + imageFile.getName());
                ex.printStackTrace();

            }
        }
        // удаление исходных файлов
        if (deleteProcessedFiles) {
            for (File file : imageFiles) {
                // Перед удалением проверяем, был ли файл перемещен в папку "notsorted"
                if (file.exists() && file.getParentFile().equals(notSortedFolder)) {
                    file.delete();
                }
            }
            System.out.println("Deleted processed files");
        }
    }
}
