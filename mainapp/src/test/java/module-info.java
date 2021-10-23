module org.kecsi.dddmodules.mainapp.test {
	requires transitive org.assertj.core;
	requires transitive org.mockito;
	requires org.slf4j;
	requires org.slf4j.simple;
	requires transitive org.junit.jupiter.api;
	requires transitive org.junit.platform.commons;
	requires transitive org.junit.jupiter.engine;
	requires transitive org.junit.jupiter.params;
	requires transitive org.kecsi.dddmodules.infrastructure;
	requires org.apiguardian.api;
	requires org.kecsi.dddmodules.mainapp;
	requires com.fasterxml.jackson.databind;
	opens org.kecsi.dddmodules.mainapp to org.kecsi.dddmodules.shippingcontext, spring.core, spring.beans, spring.context;
}