import java.io.File;
import java.text.SimpleDateFormat;

public class ImageRenamer {
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

    public File renameFile(File imageFile, String newFileName) {
        File renamedFile = new File(imageFile.getParent(), newFileName);
        if (imageFile.renameTo(renamedFile)) {
            return renamedFile;
        } else {
            return imageFile;
        }
    }

    private String getFileExtension (File file){
        String extension = "";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < (fileName.length() - 1)) {
            extension = fileName.substring(dotIndex);
        }
        return extension;

    }


}
