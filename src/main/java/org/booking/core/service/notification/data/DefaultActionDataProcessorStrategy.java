package org.booking.core.service.notification.data;

import lombok.RequiredArgsConstructor;
import org.booking.core.domain.document.notification.Notification;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class DefaultActionDataProcessorStrategy {

	private final Map<String, DataProcessor> dataProcessorMap;

	public Map<String, String> apply(String type, Notification notification) {
		DataProcessor dataProcessor = getDataProcessor(type);
		return dataProcessor.execute(notification);
	}

	private DataProcessor getDataProcessor(String type) {
		DataProcessor dataProcessor = dataProcessorMap.get(type);
		if (dataProcessor == null) {
			throw new RuntimeException("Unsupported notification type");
		}
		return dataProcessor;
	}
}
