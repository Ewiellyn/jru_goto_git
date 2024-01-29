package ServiceOperations;

import AlphabetOperations.AlphabetOperations;

import java.io.*;

public class Encryptor {
    private AlphabetOperations alphabetOperations = new AlphabetOperations();

    public void encrypt(BufferedReader file, int key,String newFileName){
        alphabetOperations.alphabetCreation();
        alphabetOperations.setKey(key);
        alphabetOperations.shifter();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFileName));
            String line = file.readLine();
            while(line!=null){
                String decryptedLine = getEncryptedLine(line);
                bw.write(decryptedLine);
                bw.newLine();
                line = file.readLine();
            }
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getEncryptedLine(String line){
        char[] lineFromReader = line.toCharArray();
        char[] encryptedValue = new char[lineFromReader.length];
        for(int i = 0; i<encryptedValue.length;i++){
            if(AlphabetOperations.unchangableSymbols.contains((int)lineFromReader[i])){
                encryptedValue[i]=lineFromReader[i];
            } else{
                int index = alphabetOperations.ALPHABET.indexOf(Character.toUpperCase(lineFromReader[i]));
                //saving to format of reader file (upper or lower case)
                if(Character.isUpperCase(lineFromReader[i])){
                    encryptedValue[i] = alphabetOperations.shiftedAlphabet.get(index);
                } else{
                    encryptedValue[i] = Character.toLowerCase(alphabetOperations.shiftedAlphabet.get(index));
                }
            }
        }
        return String.valueOf(encryptedValue);
    }
}
