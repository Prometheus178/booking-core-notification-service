package org.booking.core.utils;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class JsonDecoder {

	private final Gson gson = new Gson();

	public <R> R decode(String json, Class<R> cls) {
		if (json == null || json.isBlank()) {
			String messageIsEmpty = "Message is empty";
			log.warning(messageIsEmpty);
			throw new RuntimeException(messageIsEmpty);
		}
		return gson.fromJson(json, cls);
	}
}
