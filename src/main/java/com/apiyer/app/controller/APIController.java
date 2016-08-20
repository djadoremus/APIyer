package com.apiyer.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class APIController {

	@RequestMapping(value="/{user}/{service}/{format}", method = RequestMethod.GET)
	public String getService(@PathVariable("user") final String user,
			@PathVariable("service") final String service,
			@PathVariable("format") final String format,
			HttpServletRequest request) {

		// get parameters
		Map<String, String[]> params = request.getParameterMap();

		return "API CALL";
	}
}