package vault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public Paths paths;

    public static File[] getDirectoryFiles(String path) {
        File directory = new File(path);

        return directory.listFiles();
    }

    public static void createDirectory(String path) {
        Path dirPath = Paths.get(path);

        try {
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            } else {
                System.out.println("Directory already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readAllBytes(String path) throws IOException{
        Path filePath = Paths.get(path);
        return new String(Files.readAllBytes(filePath));
    }

    public static void write(String path, byte[] bytes) {
        try{
            Files.write(Paths.get(path), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean delete(String path) throws IOException{
        Path filePath = Paths.get(path);

        return Files.deleteIfExists(filePath);
    }
}
