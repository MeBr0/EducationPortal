package com.mebr0.study.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Manager class for creating, editing and removing files
 * Used by {@link com.mebr0.study.Course}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class FileManager {

    private static final String PREFIX = "files/";

    public static boolean createDirectory(String name) {
        File file = new File(PREFIX + name);

        return file.mkdir();
    }

    public static boolean createFileAndWrite(String directory, String name, String... content) {
        File file = new File(PREFIX + directory + "/" + name);

        try {
            return file.createNewFile() && writeToFile(file, content);
        }
        catch (IOException e) {
            return false;
        }
    }

    public static boolean writeToFile(String directory, String name, String... content) {
        File file = new File(PREFIX + directory + "\\" + name);

        return writeToFile(file, content);
    }

    private static boolean writeToFile(File file, String... content) {
        if (file.isDirectory())
            return false;

        try (FileWriter writer = new FileWriter(file)) {

            for (String text: content) {
                writer.write(text);
            }
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public static boolean removeDirectory(String name) {
        File file = new File(PREFIX + name);

        return file.delete();
    }

    public static boolean removeFile(String directory, String name) {
        File file = new File(PREFIX + directory + "\\" + name);

        return file.delete();
    }
}
