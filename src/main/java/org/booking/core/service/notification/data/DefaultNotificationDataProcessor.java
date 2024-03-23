package org.booking.core.service.notification.data;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.booking.core.domain.notification.DefaultNotificationDto;
import org.springframework.stereotype.Component;

@Log
@Component
public class DefaultNotificationDataProcessor implements MessageDataProcessor<DefaultNotificationDto> {

	private final Gson gson = new Gson();
	@Override
	public DefaultNotificationDto execute(String json, Class<DefaultNotificationDto> cls) {
		if (json == null || json.isBlank()){
			String messageIsEmpty = "Message is empty";
			log.warning(messageIsEmpty);
			throw new RuntimeException(messageIsEmpty);
		}
		return gson.fromJson(json, cls);
	}
}
