package com.example.groomingsalonapp.ExceptiionHandler.HandlingException;

public class HandlingNotFoundException extends HandlingException{

    public HandlingNotFoundException(Long handlingId) {
        super("Handling with id " + handlingId + " was not found");
    }
}
