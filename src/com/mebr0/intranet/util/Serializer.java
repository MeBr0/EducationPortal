package com.mebr0.intranet.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for general java serialization
 * Files for saving stored in {@link #PREFIX} directory
 *
 * @author A.Yergali
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class Serializer {

    private static final String PREFIX = "db/";

    public static boolean serialize(String file, Object object) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(PREFIX + file))) {
            stream.writeObject(object);

            stream.flush();

            return true;
        }
        catch (FileNotFoundException e) {
            System.err.println(PREFIX + file + ": FileNotFoundException");
        }
        catch (IOException e) {
            System.err.println(PREFIX + file + ": IOException");
        }

        return false;
    }

    public static <T> T deserialize(String file, Class<T> clazz) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(PREFIX + file))) {
            return (T) stream.readObject();
        }
        catch (ClassNotFoundException e) {
            System.err.println(PREFIX + file + ": ClassNotFoundException");
        }
        catch (FileNotFoundException e) {
            System.err.println(PREFIX + file + ": FileNotFoundException");
        }
        catch (IOException e) {
            System.err.println(PREFIX + file + ": IOException");
        }

        return null;
    }

    public static <T> List<T> deserializeList(String file, Class<T> clazz) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(PREFIX + file))) {
            return (ArrayList<T>) stream.readObject();
        }
        catch (ClassNotFoundException e) {
            System.err.println(PREFIX + file + ": ClassNotFoundException");
        }
        catch (FileNotFoundException e) {
            System.err.println(PREFIX + file + ": FileNotFoundException");
        }
        catch (IOException e) {
            System.err.println(PREFIX + file + ": IOException");
        }

        return null;
    }
}
