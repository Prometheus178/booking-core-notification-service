package org.booking.core.repository;

import org.booking.core.domain.entity.EmailData;

public interface ActionRepository {

	EmailData get(String action);
}
