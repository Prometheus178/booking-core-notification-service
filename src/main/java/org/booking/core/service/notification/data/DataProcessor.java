package org.booking.core.service.notification.data;

import org.booking.core.domain.notification.NotificationDto;

import java.util.Map;

public interface DataProcessor {

	Map<String, String> execute(NotificationDto notificationDto);
}
