package bank.system.manager.users.application.exception;

public class UserInvalidPasswordException extends RuntimeException {
    public UserInvalidPasswordException(String message) {
        super(message);
    }
}
