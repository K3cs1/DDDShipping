package org.kecsi.dddmodules.sharedkernel.service;

import org.kecsi.dddmodules.sharedkernel.events.ApplicationEvent;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.sharedkernel.events.EventSubscriber;

public interface ApplicationService {

	default <E extends ApplicationEvent> void publishEvent( E event ) {
		EventBus eventBus = getEventBus();
		if ( eventBus != null ) {
			eventBus.publish( event );
		}
	}

	default <E extends ApplicationEvent> void subscribe( String eventType, EventSubscriber subscriber ) {
		EventBus eventBus = getEventBus();
		if ( eventBus != null ) {
			eventBus.subscribe( eventType, subscriber );
		}
	}

	default <E extends ApplicationEvent> void unsubscribe( String eventType, EventSubscriber subscriber ) {
		EventBus eventBus = getEventBus();
		if ( eventBus != null ) {
			eventBus.unsubscribe( eventType, subscriber );
		}
	}

	EventBus getEventBus();

	void setEventBus( EventBus eventBus );
}
