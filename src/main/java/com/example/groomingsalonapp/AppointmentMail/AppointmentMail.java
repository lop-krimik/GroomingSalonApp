package com.example.groomingsalonapp.AppointmentMail;


import com.example.groomingsalonapp.Domain.Appointment;
import com.example.groomingsalonapp.Repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentMail  {

    private final JavaMailSender mailSender;
    private final AppointmentRepository appointmentRepository;
//String toAddress, String subject, String message
 @Scheduled(cron = "0 0/10 9-18 * * ?")
public void sendReminderEmails() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime hourLater = now.plusHours(1);

    List<Appointment> appointments = appointmentRepository.findByDateTimeBetween(
            hourLater.minusMinutes(1),
            hourLater.plusMinutes(1)
    );
    for (Appointment appointment : appointments) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(appointment.getClient().getEmail());
            mailMessage.setSubject("Напоминание о записи");
            mailMessage.setText(
                    String.format(
                            "Привет %s! Напоминаем, что у вас запись через 1 час, в %s.",
                            appointment.getClient().getClientName(),
                            appointment.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                    ));
            mailSender.send(mailMessage);
            log.info("Отправлено напоминание для записи ID: {}", appointment.getAppointmentId());

        } catch (RuntimeException e) {
            log.error("Ошибка отправки письма для записи ID: {}", appointment.getAppointmentId(), e);
        }
    }
}

}
