package com.apiyer.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class FileController {
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String getService(){
		return "UPLOAD";
	}
}