package com.apiyer.app.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apiyer.app.utils.CsvToFirebaseDb;

@Controller
public class WebAppController {	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String root() {
		return "index";
	}

	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String upload() {
		return "upload";
	}

	@RequestMapping(value="/services", method = RequestMethod.GET)
	public String showAllServices() {
		return "services";
	}

	@RequestMapping(value="/services/{serviceID}", method = RequestMethod.GET)
	public String showService(@PathVariable("serviceID") final String serviceID) {
		return "serviceItem";
	}

	@RequestMapping(value="/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
        
	@RequestMapping(value="/testInternalFile", method = RequestMethod.GET)
	public void testParse() {
            CsvToFirebaseDb parser = new CsvToFirebaseDb();
            File file = new File("src/main/resources/repoverseasvoterturnout2013.csv");
            System.out.println("file " + file.getAbsolutePath());
            
            parser.parseToJSON(file);
            
	}
}
