package hu.unideb.inf.meinauto.xml2_meinauto.main;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.meinauto.xml2_meinauto.model.Auto;
import hu.unideb.inf.meinauto.xml2_meinauto.parser.AutoParser;

public class AutoResource extends ServerResource {

	
	private static Logger logger = LoggerFactory.getLogger(AutoResource.class);
	
	@Get("xml|json")
	public Auto represent(){
		String marka = getAttribute("marka");
		String tipus = getAttribute("tipus");
		String altipus = getAttribute("altipus");
		Auto auto = null;
		
		try {
			AutoParser aParser = new AutoParser();
			String uri = "http://www.meinauto.de/"+ marka + "/neuwagen/"+tipus+"/angebote/"+altipus;
			System.out.println("URI!!!!!!!!!: "+uri);
			auto = aParser.parse(uri);
		} catch (Exception e) {
			logger.error("{} cause: {}",e.getMessage(),e.getCause());
			throw new ResourceException(404);
		}
		
		return auto;
	}
}
