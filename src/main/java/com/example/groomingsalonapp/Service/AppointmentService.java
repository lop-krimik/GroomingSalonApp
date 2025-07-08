package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.AppointmentDto;
import com.example.groomingsalonapp.Domain.*;
import com.example.groomingsalonapp.ExceptiionHandler.ClientException.ClientNotFoundException;
import com.example.groomingsalonapp.ExceptiionHandler.EmployeeException.EmployeeNotFoundException;
import com.example.groomingsalonapp.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final PetRepository petRepository;
    private final HandlingRepository handlingRepository;
    private final ClientRepository clientRepository;

//    public List<Appointment> findAppointmentsByEmployeeId (Long employeeId){
//        return appointmentRepository.findByEmployee_EmployeeId(employeeId);
//    }

    public Appointment createAppointment(AppointmentDto appointmentDto) {

        Employee employee = employeeRepository.findById(appointmentDto.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        Client client = clientRepository.findById(appointmentDto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Pet pet = petRepository.findById(appointmentDto.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found or doesn't belong to client"));

        Handling handling = handlingRepository.findById(appointmentDto.getHandlingId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        HandlingName handlingName = handling.getHandlingName();

        if (appointmentRepository.existsByEmployeeAndDateTimeLessThanAndEndDateTimeGreaterThan(employee,
                appointmentDto.getDateTime().plus(Handling.getDurationHandling().get(handlingName)),
                appointmentDto.getDateTime()
                )) {
            throw new RuntimeException("This time slot is already booked");
        }

        Appointment appointment = Appointment.builder()
                .employee(employee)
                .client(client)
                .pet(pet)
                .handling(handling)
                .dateTime(appointmentDto.getDateTime())
                .endDateTime(appointmentDto.getDateTime().plus(Handling.getDurationHandling().get(handlingName)))
                .build();
        return appointmentRepository.save(appointment);
    }

    public AppointmentDto updateAppointment(Long appoinmentId, LocalDateTime date, HandlingName handlingName) {


        Appointment appointment = appointmentRepository.findById(appoinmentId).orElseThrow(() -> new RuntimeException("not found"));
        appointment.setDateTime(date);
        appointmentRepository.save(appointment);
        return  AppointmentDto.builder()
                .clientId(appointment.getClient().getClientId())
                .handlingId(appointment.getHandling().getHandlingId())
                .employeeId(appointment.getEmployee().getEmployeeId())
                .petId(appointment.getPet().getPetId())
                .dateTime(date)
                .endDateTime(date.plus(Handling.getDurationHandling().get(handlingName)))
                .build();
    }

    public Integer countAppointment(Long clientId){
       int count = (int) appointmentRepository.findByClient_ClientId(clientId).stream().count();
       return count;
    }

}
