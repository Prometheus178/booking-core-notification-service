package org.booking.core.service;

public interface MessageDataProcessor<R> {

	R execute(String json, Class<R> cls);
}
