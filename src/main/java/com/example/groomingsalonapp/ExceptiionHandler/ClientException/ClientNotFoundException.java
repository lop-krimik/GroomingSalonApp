package com.example.groomingsalonapp.ExceptiionHandler.ClientException;

public class ClientNotFoundException extends ClientException{
    public ClientNotFoundException(Long clientId) {
        super("Client with id " + clientId + " was not found");
    }
    public ClientNotFoundException(String phone) {
        super("Client with id " + phone + " was not found");
    }
}
