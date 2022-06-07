package utils.exceptions;

public class InvalidLoginException extends RuntimeException{

     InvalidLoginException() {
        super("Invalid Login credentials, try again");
    }

     InvalidLoginException(String msg) {
        super(msg);
    }
}
