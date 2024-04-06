import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Класс для переименования изображения на основе метаданных.
 */
public class ImageRenamer {
    /**
     * Генерирует новое имя файла на основе метаданных.
     *
     * @param metadata метаданные изображения
     * @param imageFile файл изображения
     * @return новое имя файла, сформированное на оснве даты из метаданных и расширения исходного файла
     */
    public String generateNewFileName(ImageMetadata metadata, File imageFile) {
        String newFileName = null;
        if(metadata != null && metadata.getCaptureDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
            String formattedDate = simpleDateFormat.format(metadata.getCaptureDate());
            String extension = getFileExtension(imageFile);
            newFileName = formattedDate + extension;
        }
        return newFileName;
    }

    /**
     * Переименовывает файл в указанное имя.
     *
     * @param imageFile файл который треубеится переименовать
     * @param newFileName новое имя файла
     * @return объект типа File, представляющий переименованный файл, или исходный файл, если переименование не удалось
     */
    public File renameFile(File imageFile, String newFileName) {
        File renamedFile = new File(imageFile.getParent(), newFileName);
        if (imageFile.renameTo(renamedFile)) {
            return renamedFile;
        } else {
            return imageFile;
        }
    }

    /**
     * Получает расширение файла.
     *
     * @param file исходный файл у которого необходимо получить расширение
     * @return расширение файла, включая точку
     */
    public String getFileExtension (File file){
        String extension = "";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < (fileName.length() - 1)) {
            extension = fileName.substring(dotIndex);
        }
        return extension;

    }
}
