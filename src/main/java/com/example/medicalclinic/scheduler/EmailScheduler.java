package com.example.medicalclinic.scheduler;

import com.example.medicalclinic.config.AdminConfig;
import com.example.medicalclinic.domain.Mail;
import com.example.medicalclinic.repository.AppointmentRepository;
import com.example.medicalclinic.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Appointments: One reminder email per day";
    private final SimpleEmailService serviceMail;
    private final AppointmentRepository appointmentRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 8 * * *")
    //@Scheduled(fixedRate = 10000)
    public void sendEmailInformation() {
        long size = appointmentRepository.count();
        serviceMail.sendScheduler(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        size == 0
                                ? "Currently in the database you have no appointments"
                                : size == 1
                                ? "Currently in the database you have 1 appointment"
                                : "Currently in the database you have " + size + " appointments"
                )
        );
    }
}
