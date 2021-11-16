package com.mainproject.useraccount.services;

import com.mainproject.useraccount.entity.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async("threadPoolTaskExecutor")
    public String sendmail(SendMail mail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            String[] bcc=mail.getMailTo();
            InternetAddress[] bccAddress = new InternetAddress[bcc.length];
            for( int i = 0; i < bcc.length; i++ ) {
                bccAddress[i] = new InternetAddress(bcc[i]);
            }
            for( int i = 0; i < bccAddress.length; i++) {
                mimeMessage.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
            }
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "SENDit-Mailer.com"));
            mimeMessageHelper.setText(mail.getContent());

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Mail Sent!!!";
    }
}
