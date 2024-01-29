package InputValidator;

import Exceptions.InvalidArgumanentException;
import Modes.Modes;

import java.nio.file.*;
//Class InputValidator is created for validation of input information of user.
//It checks the number of arguments entered, check whether the command and path are correct.
public class InputValidator {
    public static void validateInput(String[] args) {
        if (args.length > 3 || args.length < 2) {
            throw new InvalidArgumanentException("Quantity of arguments shouldn`t be more than 3 or less than 2 ");
        }
    }

    public static void validateCommand(Modes command) {
        boolean isCommandExist = false;
        for (Modes mode : Modes.values()) {
            if (mode.equals(command)) {
                isCommandExist = true;
                break;
            }
        }
        if (!isCommandExist) {
            throw new InvalidArgumanentException("Command is not exist.");
        }
    }

    public static void validateFilePath(String path) {
        if (!Files.exists(Path.of(path))) {
            throw new InvalidArgumanentException("File not found");
        }
    }

}
