import java.util.Date;

/**
 * Класс для получения даты создания из метадаты изображения.
 */
public class ImageMetadata {
    private Date captureDate;

    public ImageMetadata(Date captureDate) {
        this.captureDate = captureDate;
    }

    /**
     * Возвращает дату создания изображения из метадаты.
     *
     * @return дату создания изображения из метадаты
     */
    public Date getCaptureDate() {
        return captureDate;
    }

    /**
     * Устанавливает дату создания изображения.
     *
     * @param captureDate
     */
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
