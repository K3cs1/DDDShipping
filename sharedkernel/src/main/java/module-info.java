module org.kecsi.dddmodules.sharedkernel {
	requires lombok;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.data.commons;
	requires spring.data.mongodb;
	exports org.kecsi.dddmodules.sharedkernel.events;
	exports org.kecsi.dddmodules.sharedkernel.service;
}