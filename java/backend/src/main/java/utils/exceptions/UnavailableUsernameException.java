package utils.exceptions;

public class UnavailableUsernameException extends RuntimeException {
    UnavailableUsernameException() {
        super("Invalid Username, enter a new username");
    }

    public UnavailableUsernameException(String msg) {
        super(msg);
    }
}
