package org.booking.core.service.notification.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.booking.core.constant.NotificationType;
import org.booking.core.domain.notification.NotificationDto;
import org.booking.core.service.notification.NotificationService;
import org.booking.core.service.notification.data.DefaultActionDataProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.booking.core.service.notification.data.DefaultActionDataProcessor.*;

@Log
@RequiredArgsConstructor
@Service(value = NotificationType.EMAIL)
public class EmailNotificationService implements NotificationService {

	private final JavaMailSender javaMailSender;
	private final DefaultActionDataProcessor actionDataProcessor;

	@Override
	public void sent(NotificationDto notificationDto) {
		log.info("From: " + NotificationType.EMAIL);
		Map<String, String> data = actionDataProcessor.execute(NotificationType.EMAIL, notificationDto);
		sent(data.get(MAIL_TO) , data.get(MAIL_SUBJECT), data.get(MAIL_TEXT));
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
