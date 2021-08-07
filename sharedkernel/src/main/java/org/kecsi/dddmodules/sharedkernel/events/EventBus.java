package org.kecsi.dddmodules.sharedkernel.events;

import org.springframework.stereotype.Service;

@Service
public interface EventBus {
	<E extends ApplicationEvent> void publish( E event );

	<E extends ApplicationEvent> void subscribe( String eventType, EventSubscriber subscriber );

	<E extends ApplicationEvent> void unsubscribe( String eventType, EventSubscriber subscriber );
}
