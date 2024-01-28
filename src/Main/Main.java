package Main;

import Modes.Modes;
import ServiceOperations.*;
import InputValidator.InputValidator;

import java.io.*;

public class Main {
    public static int key;
    public static String filePath;
    public static BufferedReader reader;

    public static void main(String[] args) {
        InputValidator.validateInput(args);
        Modes command = Modes.valueOf(args[0]);
        InputValidator.validateCommand(command);
        filePath = args[1];
        InputValidator.validateFilePath(filePath);
        key = Integer.valueOf(args[2]);
        try {
            FileInputStream fi = new FileInputStream(String.valueOf(filePath));
            reader = new BufferedReader(new InputStreamReader(fi));
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (command) {
            case DECRYPT:
                new Decryptor().decrypt(reader, key, fileWithoutExtension(filePath) + "_DECRYPTED.txt");
                break;
            case ENCRYPT:
                new Encryptor().encrypt(reader, key, fileWithoutExtension(filePath) + "_ENCRYPTED.txt");
                break;
            case BRUTE_FORCE:
                new Brute_Force().brute_forced(reader, fileWithoutExtension(filePath) + "_BRUTE_FORCED.txt");
        }
    }
    public static String fileWithoutExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
