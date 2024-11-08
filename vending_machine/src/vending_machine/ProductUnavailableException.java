package vending_machine;

public class ProductUnavailableException extends Exception {
    public ProductUnavailableException(String message) {
        super(message);
    }
}