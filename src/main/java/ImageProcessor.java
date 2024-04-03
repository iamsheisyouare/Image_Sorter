import com.drew.imaging.ImageProcessingException;

import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    private FileProcessor fileProcessor;
    private boolean deleteProcessedFiles;
    public ImageProcessor(FileProcessor fileProcessor, boolean deleteProcessedFiles) {
        this.fileProcessor = fileProcessor;
        this.deleteProcessedFiles = deleteProcessedFiles;
    }

    public void processImage() {
        File[] imageFiles = fileProcessor.getFiles();
        if (imageFiles.length == 0) {
            System.out.println("No images in directory");
        }
        MetaDataReader metaDataReader = new MetaDataReader();
        ImageRenamer imageRenamer = new ImageRenamer();
        ImageSorter imageSorter = new ImageSorter();

        File notSortedFolder = new File(fileProcessor.getDirectoryName() + File.separator + "notsorted");
        if(!notSortedFolder.exists()) {
            notSortedFolder.mkdirs();
        }

        for (File imageFile : imageFiles) {
            try {
                ImageMetadata imageMetadata = metaDataReader.readMetaData(imageFile);
                if (imageMetadata != null) {
                    String newFileName = imageRenamer.  generateNewFileName(imageMetadata, imageFile);
                    File renamedFile = imageRenamer.renameFile(imageFile, newFileName);
                    imageSorter.sortImage(renamedFile);
                } else {
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
