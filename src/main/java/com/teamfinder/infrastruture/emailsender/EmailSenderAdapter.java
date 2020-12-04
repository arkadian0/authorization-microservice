package com.teamfinder.infrastruture.emailsender;

import com.teamfinder.domain.authorization.ConfirmationToken;
import com.teamfinder.domain.authorization.ports.outcoming.EmailSenderPort;
import com.teamfinder.messages.InfoMessages;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderAdapter implements EmailSenderPort {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value(("${confirm.account.url}"))
    private String confirmationUrl;

    @Async
    public void sendEmailWithConfirmationToken(ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = createMailContent(confirmationToken);
        javaMailSender.send(mailMessage);
    }

    private SimpleMailMessage createMailContent(ConfirmationToken confirmationToken){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(confirmationToken.getAccount().getEmail());
        mailMessage.setSubject(InfoMessages.COMPLETE_REGISTRATION);
        mailMessage.setFrom(mailFrom);
        mailMessage.setText(InfoMessages.CONFIRMATION_INFO + confirmationUrl + confirmationToken.getToken());
        return mailMessage;
    }
}