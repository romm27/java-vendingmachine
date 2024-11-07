package vending_machine.Exceptions;

public class DontSuchElementException extends Exception {
    public DontSuchElementException() {
        super();
    }

    @Override
    public String toString() {
        return "O input não foi encontrado, o programa será encerrado.";
    } 
}