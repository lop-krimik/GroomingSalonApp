package com.example.groomingsalonapp.Service;

import com.example.groomingsalonapp.DTO.AppointmentDto;
import com.example.groomingsalonapp.Domain.*;
import com.example.groomingsalonapp.ExceptiionHandler.AppointmentException.AppointmentNotFoundException;
import com.example.groomingsalonapp.ExceptiionHandler.ClientException.ClientNotFoundException;
import com.example.groomingsalonapp.ExceptiionHandler.EmployeeException.EmployeeNotFoundException;
import com.example.groomingsalonapp.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
                .orElseThrow(() -> new EmployeeNotFoundException(appointmentDto.getEmployeeId()));

        Client client = clientRepository.findById(appointmentDto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(appointmentDto.getClientId()));

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

    public AppointmentDto updateAppointment(Long appoinmentId, LocalDateTime date) {


        Appointment appointment = appointmentRepository.findById(appoinmentId).orElseThrow(() -> new RuntimeException("not found"));
        appointment.setDateTime(date);
        appointment.setEndDateTime(date.plus(appointment.getHandling().getDuration()));
        appointmentRepository.save(appointment);
        return  AppointmentDto.builder()
                .clientId(appointment.getClient().getClientId())
                .handlingId(appointment.getHandling().getHandlingId())
                .employeeId(appointment.getEmployee().getEmployeeId())
                .petId(appointment.getPet().getPetId())
                .dateTime(date)
                .endDateTime(date.plus(appointment.getHandling().getDuration()))
                .build();
    }

    public Integer countAppointment(Long clientId){
       int count = (int) appointmentRepository.findByClient_ClientId(clientId).stream().count();
       return count;
    }

    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }

    public AppointmentDto getAppointment(Long appoinmentId){
        Appointment appointment = appointmentRepository.findById(appoinmentId)
                .orElseThrow(()-> new AppointmentNotFoundException(appoinmentId));
        return AppointmentDto.builder()
                .endDateTime(appointment.getEndDateTime())
                .handlingId(appointment.getHandling().getHandlingId())
                .dateTime(appointment.getDateTime())
                .petId(appointment.getPet().getPetId())
                .employeeId(appointment.getEmployee().getEmployeeId())
                .clientId(appointment.getClient().getClientId())
                .build();
    }

}
