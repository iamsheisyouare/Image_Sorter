import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Класс чтения метадаты из файла.
 */
public class MetaDataReader {
    /**
     * Чтение метадаты из файла.
     * @param imageFile файл изображения
     * @return объект ImageMetaData, который содержит дату создания файла, если её нет то null
     * @throws ImageProcessingException если произошла ошибка при обработке изображения
     * @throws IOException если произошла ошибка ввода-вывода при чтении файла
     */
    public ImageMetadata readMetaData (File imageFile) throws ImageProcessingException, IOException {
        Metadata metadata =ImageMetadataReader.readMetadata(imageFile);
        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        if (directory != null) {
            return new ImageMetadata(directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
        } else {
            return null;
        }
    }
}
