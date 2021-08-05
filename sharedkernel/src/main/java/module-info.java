module org.kecsi.dddmodules.sharedkernel {
	requires lombok;
	requires spring.context;
	exports org.kecsi.dddmodules.sharedkernel.events;
	exports org.kecsi.dddmodules.sharedkernel.service;
}