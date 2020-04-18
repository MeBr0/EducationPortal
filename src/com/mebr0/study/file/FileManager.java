package com.mebr0.study.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.mebr0.intranet.util.Printer.print;

/**
 * Manager class for creating, editing and removing files
 * Used by {@link com.mebr0.study.Course}
 *
 * @author A.Yergali
 * @version 1.2
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
                writer.write(text + '\n');
            }
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public static List<String> getFiles(String name) {
        File directory = new File(PREFIX + name);

        if (directory.isFile())
            return Collections.emptyList();

        //noinspection ConstantConditions
        return Arrays.stream(directory.listFiles()).
                map(File::getName).
                collect(Collectors.toList());
    }

    public static String readFromFile(String directory, String name) {
        File file = new File(PREFIX + directory + "/" + name);

        if (file.isDirectory()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        try (FileReader reader = new FileReader(file)) {

            int i;
            while ((i = reader.read()) != -1) {
                builder.append((char) i);
            }
        }
        catch (IOException e) {
            return builder.toString();
        }

        return builder.toString();
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
