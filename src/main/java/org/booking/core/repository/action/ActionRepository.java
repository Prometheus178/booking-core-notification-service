package org.booking.core.repository.action;

import org.booking.core.domain.document.EmailData;

public interface ActionRepository {

	EmailData get(String action);
}
