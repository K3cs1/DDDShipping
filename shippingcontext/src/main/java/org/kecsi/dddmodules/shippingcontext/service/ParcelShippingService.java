package org.kecsi.dddmodules.shippingcontext.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.NoArgsConstructor;
import org.kecsi.dddmodules.sharedkernel.events.ApplicationEvent;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.sharedkernel.events.EventSubscriber;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ParcelShippingService implements ShippingService {
	public static final String EVENT_ORDER_READY_FOR_SHIPMENT = "OrderReadyForShipmentEvent";
	private ShippingOrderRepository orderRepository;
	private EventBus eventBus;
	private Map<Integer, Parcel> shippedParcels = new HashMap<>();

	@Autowired
	public ParcelShippingService( ShippingOrderRepository orderRepository, EventBus eventBus ) {
		this.orderRepository = orderRepository;
		this.eventBus = eventBus;
	}

	@Override
	public void shipOrder( int orderId ) {
		Optional<ShippableOrder> order = this.orderRepository.findShippableOrderByOrderId( orderId );
		order.ifPresent( completedOrder -> {
			Parcel parcel = new Parcel( completedOrder.getOrderId(), completedOrder.getAddress(), completedOrder.getPackageItems() );
			if ( parcel.isTaxable() ) {
				// Calculate additional taxes
			}
			// Ship parcel
			this.shippedParcels.put( completedOrder.getOrderId(), parcel );
		} );
	}

	@Override
	public void listenToOrderEvents() {
		this.eventBus.subscribe( EVENT_ORDER_READY_FOR_SHIPMENT, new EventSubscriber() {
			@Override
			public <E extends ApplicationEvent> void onEvent( E event ) {
				shipOrder( Integer.parseInt( event.getPayloadValue( "order_id" ) ) );
			}
		} );
	}

	@Override
	public Optional<Parcel> getParcelByOrderId( int orderId ) {
		return Optional.ofNullable( this.shippedParcels.get( orderId ) );
	}

	public void setOrderRepository( ShippingOrderRepository orderRepository ) {
		this.orderRepository = orderRepository;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

//	@Override
//	public void setEventBus( EventBus eventBus ) {
//		this.eventBus = eventBus;
//	}

}
