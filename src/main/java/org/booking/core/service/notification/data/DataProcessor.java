package org.booking.core.service.notification.data;

import org.booking.core.domain.document.notification.Notification;

import java.util.Map;

public interface DataProcessor {

	Map<String, String> execute(Notification notification);
}
