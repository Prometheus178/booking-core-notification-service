package org.booking.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.booking.core.domain.document.notification.Notification;
import org.booking.core.repository.notification.NotificationRepository;
import org.booking.core.service.notification.email.EmailNotificationService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@Slf4j
class NotificationTopologyTest {

	private TopologyTestDriver testDriver;
	private TestInputTopic<String, Notification> inputTopic;

	@Mock
	private EmailNotificationService emailNotificationService;
	@Mock
	private NotificationRepository notificationRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-app");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class.getName());

		log.info("Setting up StreamsBuilder and building topology");
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		NotificationTopology topology = new NotificationTopology(emailNotificationService, notificationRepository);
		topology.process(streamsBuilder);
		Topology builtTopology = streamsBuilder.build();

		log.info("Setting up TopologyTestDriver");
		testDriver = new TopologyTestDriver(builtTopology, props);

		log.info("Creating input topic for testing");
		inputTopic = testDriver.createInputTopic(NotificationTopology.NOTIFICATION_IN_0,
				Serdes.String().serializer(), new JsonSerde<>(Notification.class).serializer());
	}

	@Test
	void testProcess() {
		log.info("Starting testProcess");

		Notification notification = Instancio.create(Notification.class);
		notification.getNotificationChannel().setNotifyBy(List.of("EMAIL"));
		log.info("Generated test notification: {}", notification);

		log.info("Piping notification to input topic");
		inputTopic.pipeInput("key", notification);

		ArgumentCaptor<Notification> notificationCaptor = ArgumentCaptor.forClass(Notification.class);
		verify(notificationRepository, times(1)).save(notificationCaptor.capture());

		Notification savedNotification = notificationCaptor.getValue();
		log.info("Captured notification for repository save: {}", savedNotification);
		assertEquals(notification.getAction(), savedNotification.getAction());
		assertEquals(notification.getNotificationChannel().getNotifyBy(), savedNotification.getNotificationChannel().getNotifyBy());

		verify(emailNotificationService, times(1)).sent(notificationCaptor.capture());

		Notification sentNotification = notificationCaptor.getValue();
		log.info("Captured notification for email service: {}", sentNotification);
		assertEquals(notification.getAction(), sentNotification.getAction());
		assertEquals(notification.getNotificationChannel().getNotifyBy(), sentNotification.getNotificationChannel().getNotifyBy());

		log.info("Finished testProcess");
	}

	@Test
	void testProcessWithoutNotifyBy() {
		log.info("Starting testProcessWithoutNotifyBy");

		Notification notification = Instancio.create(Notification.class);
		notification.getNotificationChannel().setNotifyBy(null);
		log.info("Generated test notification without NotifyBy: {}", notification);

		log.info("Piping notification to input topic");
		inputTopic.pipeInput("key", notification);

		ArgumentCaptor<Notification> notificationCaptor = ArgumentCaptor.forClass(Notification.class);
		verify(notificationRepository, times(1)).save(notificationCaptor.capture());

		Notification savedNotification = notificationCaptor.getValue();
		log.info("Captured notification for repository save: {}", savedNotification);
		assertEquals(notification.getAction(), savedNotification.getAction());
		assertNull(savedNotification.getNotificationChannel().getNotifyBy());

		verify(emailNotificationService, times(0)).sent(notification);

		log.info("Finished testProcessWithoutNotifyBy");
	}
}