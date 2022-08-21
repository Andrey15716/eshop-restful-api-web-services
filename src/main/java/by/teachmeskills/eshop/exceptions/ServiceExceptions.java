package by.teachmeskills.eshop.exceptions;

public class ServiceExceptions extends Exception{
    public ServiceExceptions(String message) {
        super(message);
    }

    public ServiceExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
