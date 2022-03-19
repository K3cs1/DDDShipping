package org.kecsi.dddmodules.shippingcontext.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableMongoRepositories( "org.kecsi.dddmodules.shippingcontext.repository" )
public class ParcelShippingService implements ShippingService {

	private final ShippingOrderRepository shippingOrderRepository;
	private final EventBus eventBus;

	@Override
	public void shipOrder( ShippableOrder shippableOrder ) {
		shippingOrderRepository.save( shippableOrder );
	}

	@Override
	public Optional<Parcel> getParcelByOrderId( long orderId ) {
		Optional<ShippableOrder> shippableOrderOptional = shippingOrderRepository.findShippableOrderByOrderId( orderId );
		if ( shippableOrderOptional.isPresent() ) {
			ShippableOrder shippableOrder = shippableOrderOptional.get();
			return Optional.of( Parcel.builder()
					.orderId( shippableOrder.getOrderId() )
					.totalPrice( shippableOrder.getTotalPrice() )
					.packageItems( shippableOrder.getPackageItems() )
					.build() );
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
