import com.drew.imaging.ImageProcessingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ImageProcessorTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private FileProcessor mockFileProcessor;
    private ImageProcessor imageProcessor;

    @Before
    public void setUp() {
        mockFileProcessor = mock(FileProcessor.class);
        imageProcessor = new ImageProcessor(mockFileProcessor, false);
     }

    @Test
    public void testProcessImageWithNoFiles() throws IOException {
        // Создаем заглушку для метода getFiles, который возвращает пустой массив файлов
        when(mockFileProcessor.getFiles()).thenReturn(new File[0]);
        imageProcessor.processImage();
        //Проверяем, что метод getFiles был вызван ровно один раз на объекте mockFileProcessor.
        //Это гарантирует, что метод getFiles был использован для получения списка файлов в директории.
        verify(mockFileProcessor, times(1)).getFiles();
    }

    @Test
    public void testProcessImageWithFiles() throws IOException {
        File file1 = temporaryFolder.newFile("test1.jpg");
        File file2 = temporaryFolder.newFile("test2.jpg");
        createImageFile(file1, 100, 100);
        createImageFile(file2,100, 200);
        File[] imageFiles = new File[] { file1, file2 };
        when(mockFileProcessor.getFiles()).thenReturn(imageFiles);
        imageProcessor.processImage();
    }
    private void createImageFile(File file, int width, int height) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ImageIO.write(image, "JPEG", file);
    }

}