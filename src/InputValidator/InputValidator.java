package InputValidator;

import Exceptions.InvalidArgumanentException;
import Modes.Modes;

import java.nio.file.*;

public class InputValidator {
    public static void validateInput(String[] args) {
        if (args.length > 3 || args.length < 2) {
            throw new InvalidArgumanentException("Quantity of arguments shouldn`t be more than 3 or less than 2 ");
        }
    }

    public static void validateCommand(Modes command) {
        for (Modes mode : Modes.values()) {
            if (!(mode.toString().equals(command))) {
                throw new InvalidArgumanentException("Command is not exist.");
            }
        }
    }

    public static void validateFilePath(String path) {
        if (!Files.exists(Path.of(path))) {
            throw new InvalidArgumanentException("File not found");
        }
    }

}
