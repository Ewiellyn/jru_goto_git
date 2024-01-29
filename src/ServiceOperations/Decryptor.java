package ServiceOperations;

import AlphabetOperations.AlphabetOperations;

import java.io.*;


public class Decryptor {
    private AlphabetOperations alphabetOperations = new AlphabetOperations();

    public void decrypt(BufferedReader file, int key,String newFileName){
        alphabetOperations.alphabetCreation();
        alphabetOperations.setKey(key);
        alphabetOperations.shifter();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFileName));
            String line = file.readLine();
            while(line!=null){
                String decryptedLine = getDecryptedLine(line);
                bw.write(decryptedLine);
                bw.newLine();
                line = file.readLine();
            }
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
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
        return String.valueOf(decryptedValue);
    }

}
