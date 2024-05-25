package org.booking.core.service.notification.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.booking.core.constant.NotificationType;
import org.booking.core.constant.ProcessorType;
import org.booking.core.domain.document.notification.Notification;
import org.booking.core.service.notification.data.DefaultActionDataProcessorStrategy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.booking.core.service.notification.data.EmailDataProcessor.*;

@Log
@RequiredArgsConstructor
@Service(value = NotificationType.EMAIL)
public class EmailNotificationService   {

	private final JavaMailSender javaMailSender;
	private final DefaultActionDataProcessorStrategy actionDataProcessor;

	public void sent(Notification notification) {
		log.info("From: " + NotificationType.EMAIL);
		try {
			Map<String, String> data = actionDataProcessor.apply(ProcessorType.EMAIL_PROCESSOR, notification);
			sent(data.get(MAIL_TO), data.get(MAIL_SUBJECT), data.get(MAIL_TEXT));
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
	}

	private void sent(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@booking-core-notification-service.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}
}
