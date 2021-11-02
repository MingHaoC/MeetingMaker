package com.meetingmaker.util;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.util.IOUtils;
import com.meetingmaker.MeetingmakerApplication;
import com.meetingmaker.service.impl.AuthenticationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${aws.ses.email}")
    private String FROM;

    public void sendEmail(String content, String subject, String to) {
        Session session = Session.getInstance(new Properties(System.getProperties()));
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setSubject(subject, "UTF-8");

            mimeMessage.setFrom(FROM);
            mimeMessage.setRecipients(RecipientType.TO, "minghao2@outlook.com");

            MimeMultipart msgBody = new MimeMultipart("alternative");
            MimeBodyPart wrap = new MimeBodyPart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(content, "text/html; charset=UTF-8");
            msgBody.addBodyPart(htmlPart);
            wrap.setContent(msgBody);

            MimeMultipart msg = new MimeMultipart("mixed");
            mimeMessage.setContent(msg);
            msg.addBodyPart(wrap);

            // attachment part
//           MimeBodyPart messageBodyPart = new MimeBodyPart();
//
//           // Attachment pdf file
//           DataSource source = new FileDataSource(filePath);
//           messageBodyPart.setDataHandler(new DataHandler(source));
//           messageBodyPart.setFileName(fileName);
//
//           msg.addBodyPart(messageBodyPart);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);
            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

            SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
            amazonSimpleEmailService.sendRawEmail(rawEmailRequest);
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }

    public String readTemplateFile(String fileName) throws IOException {
        try (InputStream in = resourceLoader.getResource("classpath:templates/" + fileName).getInputStream()) {
            return IOUtils.toString(in);
        }
    }

}
