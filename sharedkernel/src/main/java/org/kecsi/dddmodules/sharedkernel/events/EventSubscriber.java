package org.kecsi.dddmodules.sharedkernel.events;

import org.springframework.stereotype.Service;

@Service
public interface EventSubscriber {
	<E extends ApplicationEvent> void onEvent( E event );
}
