package org.booking.core.service;

import org.booking.core.domain.notification.MetaInfoDto;
import org.booking.core.domain.notification.NotificationDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationStrategy {

	private final Map<String, NotificationService> notificationServiceMap;

	public NotificationStrategy(Map<String, NotificationService> notificationServiceMap) {
		this.notificationServiceMap = notificationServiceMap;
	}

	public void apply(NotificationDto notificationDto) {
		MetaInfoDto metaInfo = notificationDto.getMetaInfo();
		metaInfo.getNotifyBy().forEach(
				type -> {
					NotificationService notificationService = getNotificationService(type);
					notificationService.sent(notificationDto);
				}
		);
	}

	private NotificationService getNotificationService(String notificationType) {
		NotificationService notificationService = notificationServiceMap.get(notificationType);
		if (notificationService == null) {
			throw new RuntimeException("Unsupported notification type");
		}
		return notificationService;
	}
}
