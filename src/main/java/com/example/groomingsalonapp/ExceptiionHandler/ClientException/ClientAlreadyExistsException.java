package com.example.groomingsalonapp.ExceptiionHandler.ClientException;

public class ClientAlreadyExistsException extends ClientException{

   public ClientAlreadyExistsException(String clientName, String phone) {
        super("Client" + clientName + "with phone number" + phone + "already exists");
    }
}
