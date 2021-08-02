package com.baeldung.dddmodules.ordercontext.service;

import com.baeldung.dddmodules.ordercontext.model.CustomerOrder;
import com.baeldung.dddmodules.ordercontext.repository.CustomerOrderRepository;
import com.baeldung.dddmodules.sharedkernel.events.ApplicationEvent;
import com.baeldung.dddmodules.sharedkernel.events.EventBus;
import com.baeldung.dddmodules.sharedkernel.events.EventSubscriber;

public class CustomerOrderService implements OrderService {
	public static final String EVENT_ORDER_READY_FOR_SHIPMENT = "OrderReadyForShipmentEvent";

	private CustomerOrderRepository orderRepository;
	private EventBus eventBus;

	@Override
	public void placeOrder( CustomerOrder order ) {

	}

	@Override
	public void setOrderRepository( CustomerOrderRepository orderRepository ) {

	}

	@Override
	public <E extends ApplicationEvent> void publishEvent( E event ) {
		OrderService.super.publishEvent( event );
	}

	@Override
	public <E extends ApplicationEvent> void subscribe( String eventType, EventSubscriber subscriber ) {
		OrderService.super.subscribe( eventType, subscriber );
	}

	@Override
	public <E extends ApplicationEvent> void unsubscribe( String eventType, EventSubscriber subscriber ) {
		OrderService.super.unsubscribe( eventType, subscriber );
	}

	@Override
	public EventBus getEventBus() {
		return null;
	}

	@Override
	public void setEventBus( EventBus eventBus ) {

	}
}
