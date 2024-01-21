package com.example.medicalclinic.service;

import com.example.medicalclinic.config.AdminConfig;
import com.example.medicalclinic.config.CompanyDetails;
import com.example.medicalclinic.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private CompanyDetails companyDetails;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public String buildAppointmentEmail(String message) {

        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("med_url","http://localhost:8081/doctors");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_name", companyDetails.getCompanyName());
        context.setVariable("company_goal", companyDetails.getCompanyGoal());
        context.setVariable("company_email", companyDetails.getCompanyEmail());
        context.setVariable("company_phone", companyDetails.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("goodbye_message", "Greetings from the Medical Clinic Team");
        return templateEngine.process("mail/create-appointment-mail", context);
    }

    public String buildScheduledMedAppMail(String message) {

        List<LocalDate> appointmentList = appointmentRepository.findAll().stream()
                .map(appointment -> appointment.getAppointmentDate())
                .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("med_url","http://localhost:8081/doctors");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_name", companyDetails.getCompanyName());
        context.setVariable("company_goal", companyDetails.getCompanyGoal());
        context.setVariable("company_email", companyDetails.getCompanyEmail());
        context.setVariable("company_phone", companyDetails.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("appointment_list", appointmentList);
        context.setVariable("goodbye_message", "Greetings from the Medical Clinic Team");
        return templateEngine.process("mail/created-scheduled-appointment-list-mail", context);
    }
}
