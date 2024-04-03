import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private String directoryName;
    private List<File> files;
    public FileProcessor() {
        this.files = new ArrayList<>();
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String inputDirectoryName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String directoryName = null;
        while (directoryName == null) {
            try {
                System.out.println("Enter directory name: ");
                directoryName = reader.readLine();
                File directory = new File(directoryName);
                if (!directory.exists()) {
                    System.out.println("Directory not exists. Repeat input");
                    directoryName = null;
                }
            } catch (IOException ex) {
                System.out.println("Error input. Repeat input.");
            }
        }
        return directoryName;
    }

    public File[] getFiles() {
        File folder =  new File(directoryName);
        if (folder.isDirectory()) {
            return folder.listFiles();
        } else {
            return new File[0];
        }
    }

    public void addFiles(File[] files) {
        if (files != null) {
            for (File file : files) {
                if (files != null) {
                    this.files.add(file);
                }
            }
        }
    }

    public String getFileName(List<File> files) {
        for (File file : files) {
            return file.getAbsolutePath();
        }
        return "Files list is empty.";
    }



    @Override
    public String toString() {
        return "FileReader{" +
                "files=" + files +
                '}';
    }

}
