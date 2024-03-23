package org.booking.core.service.notification.data;

import org.booking.core.domain.notification.NotificationDto;

import java.util.Map;

public interface ActionDataProcessor {

	Map<String, String> execute(String type, NotificationDto notificationDto);

}
