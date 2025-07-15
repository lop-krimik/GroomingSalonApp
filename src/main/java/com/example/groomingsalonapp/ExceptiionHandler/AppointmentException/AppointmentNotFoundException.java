package com.example.groomingsalonapp.ExceptiionHandler.AppointmentException;

public class AppointmentNotFoundException extends AppointmentException{
    public AppointmentNotFoundException(Long appointmentId) {
        super("Appointment with id " + appointmentId + " was not found");
    }
}
