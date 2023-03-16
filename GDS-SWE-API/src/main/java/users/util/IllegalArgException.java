package users.util;

public class IllegalArgException extends IllegalArgumentException {

    private static final long serialVersionUID = -7806029002430564887L;

    private String message;

    public IllegalArgException() {
    }

    public IllegalArgException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
