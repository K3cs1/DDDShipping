package org.kecsi.dddmodules.infrastructure.events;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.kecsi.dddmodules.sharedkernel.events.ApplicationEvent;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.sharedkernel.events.EventSubscriber;
import org.springframework.stereotype.Component;

@Component
public class SimpleEventBus implements EventBus {

	private final Map<String, Set<EventSubscriber>> subscribers = new ConcurrentHashMap<>();

	@Override
	public <E extends ApplicationEvent> void publish( E event ) {
		if ( subscribers.containsKey( event.getType() ) ) {
			subscribers.get( event.getType() )
					.forEach( subscriber -> subscriber.onEvent( event ) );
		}
	}

	@Override
	public <E extends ApplicationEvent> void subscribe( String eventType, EventSubscriber subscriber ) {
		Set<EventSubscriber> eventSubscribers = subscribers.get( eventType );
		if ( eventSubscribers == null ) {
			eventSubscribers = new CopyOnWriteArraySet<>();
			subscribers.put( eventType, eventSubscribers );
		}
		eventSubscribers.add( subscriber );
	}

	@Override
	public <E extends ApplicationEvent> void unsubscribe( String eventType, EventSubscriber subscriber ) {
		if ( subscribers.containsKey( eventType ) ) {
			subscribers.get( eventType ).remove( subscriber );
		}
	}
}
