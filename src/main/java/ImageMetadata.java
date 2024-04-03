import com.drew.imaging.ImageMetadataReader;

import java.util.Date;

public class ImageMetadata {
    private Date captureDate;
    public ImageMetadata (Date captureDate) {
        this.captureDate = captureDate;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    @Override
    public String toString() {
        return "ImageMetadata{" +
                "captureDate=" + captureDate +
                '}';
    }
}
