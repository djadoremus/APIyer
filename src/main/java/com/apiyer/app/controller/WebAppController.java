package com.apiyer.app.controller;

import com.apiyer.app.utils.CsvToFirebaseDb;
import java.io.File;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAppController {	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String root() {
		return "Welcome To APIyer!";
	}

	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String upload() {
		return "Upload CSV File";
	}

	@RequestMapping(value="/services", method = RequestMethod.GET)
	public String showAllServices() {
		return "Show All Services";
	}

	@RequestMapping(value="/services/{serviceID}", method = RequestMethod.GET)
	public String showService(@PathVariable("serviceID") final String serviceID) {
		return "Show Service";
	}

	@RequestMapping(value="/error", method = RequestMethod.GET)
	public String error() {
		return "Houston! We have a problem! An error occured!";
	}
        
	@RequestMapping(value="/testInternalFile", method = RequestMethod.GET)
	public void testParse() {
            CsvToFirebaseDb parser = new CsvToFirebaseDb();
            File file = new File("src/main/resources/repoverseasvoterturnout2013.csv");
            System.out.println("file " + file.getAbsolutePath());
            
            parser.parseToJSON(file);
            
	}
}
