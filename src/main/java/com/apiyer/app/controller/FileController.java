package com.apiyer.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/upload")
public class FileController {
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String getService(){
		return "UPLOAD";
	}
}