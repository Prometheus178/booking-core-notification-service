package org.booking.core.service.notification.data;

public interface MessageDataProcessor<R> {

	R execute(String json, Class<R> cls);
}
