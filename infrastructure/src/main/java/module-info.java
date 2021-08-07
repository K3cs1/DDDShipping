import org.kecsi.dddmodules.infrastructure.events.SimpleEventBus;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;

module org.kecsi.dddmodules.infrastructure {
	requires lombok;
	requires java.annotation;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.data.commons;
	requires spring.data.mongodb;
	requires transitive org.kecsi.dddmodules.sharedkernel;
	requires transitive org.kecsi.dddmodules.ordercontext;
	requires transitive org.kecsi.dddmodules.shippingcontext;
	provides EventBus
			with SimpleEventBus;
//	provides CustomerOrderRepository
//			with InMemoryOrderStore;
//	provides ShippingOrderRepository
//			with InMemoryOrderStore;
}