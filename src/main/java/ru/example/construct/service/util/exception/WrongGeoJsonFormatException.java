package ru.example.construct.service.util.exception;

public class WrongGeoJsonFormatException extends Exception {

    public WrongGeoJsonFormatException(String message) {
        super(message);
    }
}
