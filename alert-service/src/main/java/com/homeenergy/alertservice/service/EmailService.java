package com.homeenergy.alertservice.service;

import com.homeenergy.alertservice.entity.Alert;
import com.homeenergy.alertservice.repository.AlertRepository;
import com.homeenergy.kafka.event.AlertingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final AlertRepository alertRepository;

    public void sendEmail(String to, String subject, String body, Long userId) {
        log.info("Sending email to {}, subject {}, body {}", to, subject, body);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setFrom("trongduc2301@gmail.com");
        mailMessage.setText(body);

        final Alert alert = Alert.builder()
                .userId(userId)
                .sent(true)
                .build();

        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            log.error(e.getMessage());
            alert.setSent(false);
        }

        alertRepository.saveAndFlush(alert);
        log.info("Email sent to {}, subject {}, body {}", to, subject, body);
    }
}
