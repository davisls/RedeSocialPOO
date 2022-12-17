package br.com.davi.socialnetwork.exceptions;

public class InvalidFormatPasswordException extends Exception {
    public InvalidFormatPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
