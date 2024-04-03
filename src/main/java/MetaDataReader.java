import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;

public class MetaDataReader {
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
