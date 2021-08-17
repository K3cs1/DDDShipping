package org.kecsi.dddmodules.shippingcontext.service;

import java.util.Optional;

import lombok.NoArgsConstructor;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ParcelShippingService implements ShippingService {
	private ShippingOrderRepository shippingOrderRepository;
	private EventBus eventBus;

	@Autowired
	public ParcelShippingService( ShippingOrderRepository shippingOrderRepository, EventBus eventBus ) {
		this.shippingOrderRepository = shippingOrderRepository;
		this.eventBus = eventBus;
	}

	@Override
	public void shipOrder( ShippableOrder shippableOrder ) {
		shippingOrderRepository.save( shippableOrder );
	}

	@Override
	public Optional<Parcel> getParcelByOrderId( long orderId ) {
		Optional<ShippableOrder> shippableOrder = shippingOrderRepository.findShippableOrderByOrderId( orderId );
		if ( shippableOrder.isPresent() ) {
			return Optional.of( new Parcel( shippableOrder.get().getOrderId(), shippableOrder.get().getAddress(), shippableOrder.get().getPackageItems() ) );
		}
		return Optional.empty();
	}

	@Override
	public void deleteSippableOrderByOrderId( long orderId ) {
		shippingOrderRepository.deleteShippableOrderByOrderId( orderId );
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

}
