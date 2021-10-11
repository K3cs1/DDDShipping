import org.kecsi.dddmodules.infrastructure.service.AdapterService;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;

module org.kecsi.dddmodules.mainapp.test {
	requires transitive org.junit.jupiter.api;
	requires transitive org.assertj.core;
	requires transitive org.mockito;
	requires transitive org.apiguardian.api;

	requires lombok;
	requires org.apache.tomcat.embed.core;
	requires java.annotation;
	requires java.validation;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.web;
	requires spring.boot.starter.validation;
	requires org.mongodb.driver.sync.client;
	uses EventBus;
	uses OrderService;
	uses CustomerOrderRepository;
	uses ShippingOrderRepository;
	uses ShippingService;
	uses AdapterService;
	requires transitive org.kecsi.dddmodules.infrastructure;
	requires org.hamcrest;
	requires spring.test;
	requires spring.boot.test;
	requires spring.boot.test.autoconfigure;
	//opens org.kecsi.dddmodules.mainapp to spring.core, spring.beans, spring.context;
	opens org.kecsi.dddmodules.controller to spring.core, spring.beans, spring.context;
	//exports org.junit.platform.commons.logging;
}