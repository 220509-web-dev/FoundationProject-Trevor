package utils.exceptions;

public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException() {
        super("Invalid Login credentials, try again");
    }

     public InvalidLoginException(String msg) {
        super(msg);
    }
}
