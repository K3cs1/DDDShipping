import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;

module org.kecsi.dddmodules.mainapp {
	requires lombok;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires java.annotation;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	uses EventBus;
	uses OrderService;
	uses CustomerOrderRepository;
	uses ShippingOrderRepository;
	uses ShippingService;
	requires transitive org.kecsi.dddmodules.infrastructure;
	opens org.kecsi.dddmodules.mainapp to spring.core, spring.beans, spring.context;
}