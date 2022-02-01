package org.kecsi.dddmodules.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ShippingErrorController implements ErrorController {

	private ErrorAttributes errorAttributes;

	@Autowired
	public void setErrorAttributes( ErrorAttributes errorAttributes ) {
		this.errorAttributes = errorAttributes;
	}

	@RequestMapping( "/error" )
	public String error( Model model, HttpServletRequest request ) {
		WebRequest webRequest = new ServletWebRequest( request );
		Map<String, Object> error = this.errorAttributes.getErrorAttributes( webRequest, ErrorAttributeOptions.of( ErrorAttributeOptions.Include.EXCEPTION ) );
		model.addAttribute( "timestamp", error.get( "timestamp" ) );
		model.addAttribute( "error", error.get( "error" ) );
		model.addAttribute( "message", error.get( "message" ) );
		model.addAttribute( "path", error.get( "path" ) );
		model.addAttribute( "status", error.get( "status" ) );
		return "detailedError";
	}

}
