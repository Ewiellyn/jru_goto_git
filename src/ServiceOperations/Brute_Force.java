package ServiceOperations;

import AlphabetOperations.AlphabetOperations;
import Main.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Brute_Force {
    public static final ArrayList<Double> letterFrequencies = new ArrayList<>(
            Arrays.asList(8.1, 1.5, 2.8, 4.3, 13.0, 2.2, 2.0, 6.1, 7.0, 0.15, 0.77, 7.0, 2.4,
                    6.8, 7.5, 1.9, 0.095, 6.0, 6.3, 9.1, 2.8, 0.98, 2.4, 0.15, 2.0, 0.074));
    private AlphabetOperations alphabetOperations = new AlphabetOperations();
    public ArrayList<Integer> inputFrequencies;

    public void brute_forced(BufferedReader file, String newFileName) {
        alphabetOperations.alphabetCreation();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFileName));
            char[] line = file.readLine().toCharArray();
            while (line != null) {
                inputLettersFrequencies(line);
                String s = file.readLine();
                if (s != null) {
                    line = s.toCharArray();
                } else {
                    line = null;
                }
            }
            int key = findTheKey();
            alphabetOperations.setKey(key);
            alphabetOperations.shifter();
            file.close();
            FileInputStream fi = new FileInputStream(String.valueOf(Main.sPath));
            BufferedReader br = new BufferedReader(new InputStreamReader(fi));
            String readLine = br.readLine();
            while (readLine != null) {
                String decryptedLine = getDecryptedLine(readLine);
                bw.write(decryptedLine);
                bw.newLine();
                readLine = br.readLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //count of frequency for each letter from input
    public void inputLettersFrequencies(char[] line) {
        HashMap<Character, Integer> lettersFrequencies = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            lettersFrequencies.put(c, 0);
        }
        for (char c : line) {
            if (!AlphabetOperations.unchangableSymbols.contains((int) c)) {
                lettersFrequencies.put(c, lettersFrequencies.get(c) + 1);
            }
        }
        ArrayList<Integer> initialFrequencies = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : lettersFrequencies.entrySet()) {
            initialFrequencies.add(entry.getValue());
        }
        inputFrequencies = initialFrequencies;
    }

    //find the key with a method of the least squares
    public int findTheKey() {
        double minimalPoints = Double.MAX_VALUE;
        int key = 0; //key equals 1 because it`s the minimal value to shift the numbers
        for (int i = 0; i < 26; i++) {
            double sum = 0;
            ArrayList<Double> differenceBetweenIdealAndInputFrequencies = new ArrayList<>();
            for (int a = 0; a < 26; a++) {
                differenceBetweenIdealAndInputFrequencies.add(Math.pow(inputFrequencies.get(a) - letterFrequencies.get(a), 2));
                sum = differenceBetweenIdealAndInputFrequencies.get(a) + sum;
            }
            if (sum / 26 < minimalPoints) {
                minimalPoints = sum / 26;
                key = i;
            }
            Integer variable = inputFrequencies.get(0);
            inputFrequencies.add(variable);
            inputFrequencies.remove(0);
        }
        return key;
    }

    public String getDecryptedLine(String line){
        char[] lineFromReader = line.toCharArray();
        char[] decryptedValue = new char[lineFromReader.length];
        for(int i = 0; i<decryptedValue.length;i++){
            if(AlphabetOperations.unchangableSymbols.contains((int)lineFromReader[i])){
                decryptedValue[i]=lineFromReader[i];
            } else{
                int index = alphabetOperations.shiftedAlphabet.indexOf(Character.toUpperCase(lineFromReader[i]));
                //saving to format of reader file (upper or lower case)
                if(Character.isUpperCase(lineFromReader[i])){
                    decryptedValue[i] = alphabetOperations.ALPHABET.get(index);
                } else{
                    decryptedValue[i] = Character.toLowerCase(alphabetOperations.ALPHABET.get(index));
                }
            }
        }
        String decryptedLine = String.valueOf(decryptedValue);
        return decryptedLine;
    }
}
