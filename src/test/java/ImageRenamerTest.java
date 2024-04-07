import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageRenamerTest{
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    @Mock
    ImageMetadata mockImageMetaData;
    ImageRenamer imageRenamer;

    @Before
    public void setUp() {
        imageRenamer = new ImageRenamer();
        mockImageMetaData = Mockito.mock(ImageMetadata.class);
    }
    @Test
    public void testGenerateNewFileName() throws IOException {
        File imageFile = temporaryFolder.newFile("test.jpg");
        Mockito.when(mockImageMetaData.getCaptureDate()).thenReturn(new Date());
        String result = imageRenamer.generateNewFileName(mockImageMetaData, imageFile);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        String expected = sdf.format(new Date());
        assertTrue(result.startsWith(expected));
        assertTrue(result.endsWith(".jpg"));
    }
    @Test
    public void testRenameFile() throws IOException {
        File imageFile = temporaryFolder.newFile("old_name.jpg");
        String newImageFile = "new_name.jpg";
        File renamedFile = new File(imageFile.getParent(), newImageFile);
        File result = imageRenamer.renameFile(imageFile, newImageFile);
        assertEquals(renamedFile, result);
    }

    @Test
    public void testGetFileExtension() throws IOException {
        File imageFile = temporaryFolder.newFile("test.jpg");
        String result = imageRenamer.getFileExtension(imageFile);
        assertEquals(".jpg", result);
    }
    @Test
    public void testGetFileExtension_WithoutExtension() throws IOException {
        File imageFile = temporaryFolder.newFile("test");
        String result = imageRenamer.getFileExtension(imageFile);
        assertEquals("", result);
    }
}