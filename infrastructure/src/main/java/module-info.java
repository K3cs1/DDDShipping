import org.kecsi.dddmodules.infrastructure.db.InMemoryOrderStore;
import org.kecsi.dddmodules.infrastructure.events.SimpleEventBus;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;

module org.kecsi.dddmodules.infrastructure {
	requires static lombok;
	requires transitive org.kecsi.dddmodules.sharedkernel;
	requires transitive org.kecsi.dddmodules.ordercontext;
	requires transitive org.kecsi.dddmodules.shippingcontext;
	provides EventBus
			with SimpleEventBus;
	provides CustomerOrderRepository
			with InMemoryOrderStore;
	provides ShippingOrderRepository
			with InMemoryOrderStore;
}