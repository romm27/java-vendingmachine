package vending_machine.Exceptions;

public class InputTypeIsNotIntException extends Exception {
    public InputTypeIsNotIntException() {
        super();
    }

    @Override
    public String toString() {
        return "A entrada não é um número inteiro!";
    }
}