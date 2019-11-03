package SDA.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

public class EmailService {
    private static void sendEmail(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("\n" +
                        "radusimu@gmail.com", "matehanA65536");
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            InternetAddress[] receiverAddresses = InternetAddress.parse("ioananaicu@yahoo.com,radusimu@yahoo.com");
            message.setRecipients(Message.RecipientType.TO, receiverAddresses);
            message.setSubject("");
//            message.setSentDate(Date.from(Instant.now()));
            message.setHeader("XPriority", "1");
            message.setText("Sampel System Generated mail");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();

            mimeBodyPart.setContent("Yey", "text/html");
            multipart.addBodyPart(mimeBodyPart);
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File("candidatiAcceptatiMarketing.json"));
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException | IOException e) {
            System.out.println("Mail could not be sent!");
        }
    }

    public static void main(String[] args) {
        sendEmail();
    }
}
