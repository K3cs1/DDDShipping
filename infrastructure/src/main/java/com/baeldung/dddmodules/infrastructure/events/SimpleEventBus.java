package com.baeldung.dddmodules.infrastructure.events;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.baeldung.dddmodules.sharedkernel.events.ApplicationEvent;
import com.baeldung.dddmodules.sharedkernel.events.EventBus;
import com.baeldung.dddmodules.sharedkernel.events.EventSubscriber;

public class SimpleEventBus implements EventBus {

	private final Map<String, Set<EventSubscriber>> subscribers = new ConcurrentHashMap<>();

	@Override
	public <E extends ApplicationEvent> void publish( E event ) {

	}

	@Override
	public <E extends ApplicationEvent> void subscribe( String eventType, EventSubscriber subscriber ) {

	}

	@Override
	public <E extends ApplicationEvent> void unsubscribe( String eventType, EventSubscriber subscriber ) {

	}
}
