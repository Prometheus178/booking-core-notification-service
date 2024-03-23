package org.booking.core.service.notification.data;

import lombok.RequiredArgsConstructor;
import org.booking.core.constant.RoleClassification;
import org.booking.core.domain.entity.EmailData;
import org.booking.core.domain.notification.ContactDto;
import org.booking.core.domain.notification.NotificationDto;
import org.booking.core.repository.ActionRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class DefaultActionDataProcessor implements ActionDataProcessor {

	public final static String MAIL_TO = "MAIL_TO";
	public final static String MAIL_SUBJECT = "MAIL_SUBJECT";
	public final static String MAIL_TEXT = "MAIL_TEXT";

	private final ActionRepository actionRepository;

	@Override
	public Map<String, String> execute(String type, NotificationDto notificationDto) {
		ContactDto customerContacts = notificationDto.getContacts().stream()
				.filter(contactDto -> RoleClassification.CUSTOMER.name().equals(contactDto.getRole()))
				.findFirst()
				.orElseThrow(RuntimeException::new);
		EmailData emailData = actionRepository.get(notificationDto.getAction());

		Map<String, String> store = new HashMap<>();
		store.put(MAIL_TO, customerContacts.getEmail());
		store.put(MAIL_SUBJECT, emailData.getSubject());
		store.put(MAIL_TEXT, emailData.getText());
		return store;
	}
}
