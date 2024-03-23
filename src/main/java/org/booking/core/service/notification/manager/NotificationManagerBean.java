package org.booking.core.service.notification.manager;

import lombok.RequiredArgsConstructor;
import org.booking.core.domain.document.notification.Notification;
import org.booking.core.domain.notification.DefaultNotificationDto;
import org.booking.core.mapper.NotificationMapper;
import org.booking.core.repository.notification.NotificationRepository;
import org.booking.core.service.notification.NotificationStrategy;
import org.booking.core.utils.JsonDecoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationManagerBean implements NotificationManager {

	private final NotificationStrategy notificationStrategy;
	private final JsonDecoder jsonDecoder;
	private final NotificationRepository notificationRepository;
	private final NotificationMapper notificationMapper;

	@Override
	public void handle(String message) {
		DefaultNotificationDto notificationDto = jsonDecoder.decode(message, DefaultNotificationDto.class);
		Notification notification = notificationMapper.toEntity(notificationDto);
		notificationRepository.save(notification);
		notificationStrategy.apply(notificationDto);
	}
}
