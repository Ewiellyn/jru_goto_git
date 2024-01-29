package AlphabetOperations;

import java.util.ArrayList;
import java.util.Arrays;

//AlphabetOperations class is for the shifting the alphabet into the steps amounted as variable "key"
//"key" can be both positive or negative. If key is negative then alphabet moved to the left and vise versa.
public class AlphabetOperations {
    public ArrayList<Character> ALPHABET;
    public ArrayList<Character> shiftedAlphabet;
    public static ArrayList<Integer> unchangableSymbols = new ArrayList<>(
            Arrays.asList(32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52,
                    53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64));
    private int key;

    public void alphabetCreation() {
        ArrayList<Character> alphabet = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
        ALPHABET = alphabet;
        shiftedAlphabet = new ArrayList<>(ALPHABET);
    }

    public void setKey(int key) {
        if (key > ALPHABET.size()) {
            this.key = key % ALPHABET.size();
        } else {
            this.key = key;
        }
    }

    // method shifter is for cases when we have negative or positive value of key
    public void shifter() {
        if (this.key > 0) {
            moveRightAlphabet(this.key);
        } else {
            moveLeftAlphabet(this.key);
        }
    }

    public void moveRightAlphabet(int key) {
        for (int index = 0; index < key; index++) {
            char variable = shiftedAlphabet.get(0);
            shiftedAlphabet.add(shiftedAlphabet.size(), variable);
            shiftedAlphabet.remove(0);
        }
    }

    public void moveLeftAlphabet(int key) {
        for (int index = 0; index < -key; index++) {
            char variable = shiftedAlphabet.get(shiftedAlphabet.size()-1);
            shiftedAlphabet.add(0, variable);
            shiftedAlphabet.remove(shiftedAlphabet.size()-1);
        }
    }


}
