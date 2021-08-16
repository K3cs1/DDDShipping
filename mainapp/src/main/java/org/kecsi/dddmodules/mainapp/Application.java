package org.kecsi.dddmodules.mainapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = "org.kecsi.dddmodules" )
public class Application {

	public static void main( String args[] ) {
		SpringApplication.run( Application.class, args );
	}

}
