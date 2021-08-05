package org.kecsi.dddmodules.sharedkernel.events;

public interface EventSubscriber {
	<E extends ApplicationEvent> void onEvent( E event );
}
