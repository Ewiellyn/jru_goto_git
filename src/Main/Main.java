package Main;

import Modes.Modes;
import ServiceOperations.Brute_Force;
import ServiceOperations.Decryptor;
import ServiceOperations.Encryptor;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static int key;
    public static StringBuilder sPath = new StringBuilder(); //path read from console
    public static BufferedReader path;

    public static void main(String[] args) {
        System.out.println("Please enter the arguments : command, filePath, key");
        Scanner console = new Scanner(System.in);
        Modes command = Modes.valueOf(console.next());
        String nextLine = console.nextLine();
        processingOfInputData(nextLine);
        try {
            FileInputStream fi = new FileInputStream(String.valueOf(sPath));
            BufferedReader br = new BufferedReader(new InputStreamReader(fi));
            path = br;
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (command) {
            case DECRYPT:
                new Decryptor().decrypt(path, key, fileWithoutExtension(sPath.toString()) + "_DECRYPTED.txt");
                break;
            case ENCRYPT:
                new Encryptor().encrypt(path, key, fileWithoutExtension(sPath.toString()) + "_ENCRYPTED.txt");
                break;
            case BRUTE_FORCE:
                new Brute_Force().brute_forced(path, sPath + "_BRUTE_FORCED.txt");
        }
    }

    public static void processingOfInputData(String lineFromConsole) {
        // processing of cases of input path (with & w/o special symbols)
        if (lineFromConsole.charAt(1) == '\"') {
            int index = 2;
            while ((index < lineFromConsole.length()) && lineFromConsole.charAt(index) != '\"') {
                sPath.append(lineFromConsole.charAt(index));
                index++;
            }
            if (index + 2 < lineFromConsole.length()) {
                key = Integer.valueOf(lineFromConsole.substring(index + 2));
            }
        } else {
            int index = 1;
            while ((index < lineFromConsole.length()) && (lineFromConsole.charAt(index) != ' ')) {
                sPath.append(lineFromConsole.charAt(index));
                index++;
            }
            if(index + 1<lineFromConsole.length()){
                key = Integer.valueOf(lineFromConsole.substring(index + 1));
            }
        }
    }

    public static String fileWithoutExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
