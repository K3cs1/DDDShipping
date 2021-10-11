import org.kecsi.dddmodules.infrastructure.service.AdapterService;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;

module org.kecsi.dddmodules.mainapp.test {
	requires org.junit.jupiter.api;
	requires org.assertj.core;
	requires org.mockito;
	requires org.apiguardian.api;
	requires org.junit.platform.commons;
	requires org.junit.jupiter.params;
	requires org.junit.platform.launcher;

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
	opens org.kecsi.dddmodules.controller
			to spring.core, spring.beans, spring.context, org.junit.platform.commons, org.junit.platform.commons.logging, org.junit.platform.launcher;
}