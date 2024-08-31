package jzxy.mcdd.backend.exception;

public class UserNameAlreadyExistException extends RuntimeException {
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
