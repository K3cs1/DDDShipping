package org.kecsi.dddmodules.shippingcontext.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.NoArgsConstructor;
import org.kecsi.dddmodules.sharedkernel.events.ApplicationEvent;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.sharedkernel.events.EventSubscriber;
import org.kecsi.dddmodules.shippingcontext.model.PackageItem;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ParcelShippingService implements ShippingService {
	public static final String EVENT_ORDER_READY_FOR_SHIPMENT = "OrderReadyForShipmentEvent";
	private ShippingOrderRepository shippingOrderRepository;
	private EventBus eventBus;
	private Map<Integer, Parcel> shippedParcels = new HashMap<>();

	@Autowired
	public ParcelShippingService( ShippingOrderRepository shippingOrderRepository, EventBus eventBus ) {
		this.shippingOrderRepository = shippingOrderRepository;
		this.eventBus = eventBus;
	}

	@Override
	public void shipOrder( Optional<ShippableOrder> shippableOrder ) {
		shippableOrder.ifPresent( completedOrder -> {
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
				Optional<ShippableOrder> orderOptional = shippingOrderRepository.
						findShippableOrderByOrderId( Integer.parseInt( event.getPayloadValue( "order_id" ) ) );
				if ( orderOptional.isPresent() ) {
					shipOrder( orderOptional );
				}
			}
		} );
	}

	@Override
	public Optional<Parcel> getParcelByOrderId( int orderId ) {
		return Optional.ofNullable( this.shippedParcels.get( orderId ) );
	}

	@Override
	public void initShippableOrder() {
		ShippableOrder shippableOrder = new ShippableOrder( 1, "Test Address 1",
				List.of( new PackageItem( 1, 1, 6 ),
						new PackageItem( 2, 1, 1 ),
						new PackageItem( 3, 21, 44 ) ) );
		shippingOrderRepository.save( shippableOrder );
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

}
