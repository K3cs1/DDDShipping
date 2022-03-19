package org.kecsi.dddmodules.shippingcontext.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.kecsi.dddmodules.shippingcontext.repository.PackageItemRepository;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableMongoRepositories( "org.kecsi.dddmodules.shippingcontext.repository" )
public class ParcelShippingService implements ShippingService {

	private final ShippingOrderRepository shippingOrderRepository;
	private final PackageItemRepository packageItemRepository;
	private final EventBus eventBus;

	@Override
	public void shipOrder( ShippableOrder shippableOrder ) {
		if ( shippableOrder.getPackageItems() != null && !shippableOrder.getPackageItems().isEmpty() ) {
			packageItemRepository.saveAll( shippableOrder.getPackageItems() );
		}
		shippingOrderRepository.save( shippableOrder );
	}

	@Override
	public Optional<Parcel> getParcelByOrderId( String orderId ) {
		return shippingOrderRepository.findShippableOrderById( orderId )
				.map( shippableOrder -> Optional.of( Parcel.builder()
						.orderId( shippableOrder.getId() )
						.totalPrice( shippableOrder.getTotalPrice() )
						.packageItems( shippableOrder.getPackageItems() )
						.build() ) )
				.orElse( Optional.empty() );
	}

	@Override
	public void deleteSippableOrderByOrderId( String orderId ) {
		Optional<ShippableOrder> shippableOrderOptional = shippingOrderRepository.findById( orderId );
		if ( shippableOrderOptional.isPresent() ) {
			packageItemRepository.deleteAll( shippableOrderOptional.get().getPackageItems() );
			shippingOrderRepository.deleteShippableOrderById( orderId );
		} else {
			throw new IllegalStateException( "Shippable order not found by id: " + orderId );
		}
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

}
