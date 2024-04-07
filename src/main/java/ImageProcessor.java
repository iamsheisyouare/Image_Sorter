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
                String newFileName = imageRenamer.generateNewFileName(imageMetadata, imageFile);
                File renamedFile = imageRenamer.renameFile(imageFile, newFileName);
                imageSorter.sortImage(renamedFile);

            } catch (ImageProcessingException | IOException ex) {
                System.err.println("Error processing image file: " + imageFile.getName());
                ex.printStackTrace();
                moveToNotSortedFolder(imageFile, notSortedFolder);
            }
        }
        // удаление исходных файлов
        if (deleteProcessedFiles) {
            deleteProcessedFiles(imageFiles, notSortedFolder);
        }
    }

    private void moveToNotSortedFolder(File imageFile, File notSortedFile) {
        File destinationFile = new File(notSortedFile.getAbsolutePath() + File.separator
                + imageFile.getName());
        System.out.println("File " + imageFile.getName() + "moved to 'notsorted' folder");
        if(!imageFile.renameTo(destinationFile)) {
            System.out.println("Failed to move file to 'notsorted' folder.");
        }
    }

    private void deleteProcessedFiles(File[] imageFiles, File notSortedFolder) {
        for (File file : imageFiles) {
            if(file.exists() && file.getParentFile().equals(notSortedFolder)) {
                file.delete();
            }
        }
        System.out.println("Deleted processed files");
    }

}
