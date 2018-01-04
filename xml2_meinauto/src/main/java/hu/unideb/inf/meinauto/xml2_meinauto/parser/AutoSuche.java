package hu.unideb.inf.meinauto.xml2_meinauto.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import hu.unideb.inf.meinauto.xml2_meinauto.model.SearchResults;
import hu.unideb.inf.jaxb.JAXBUtil;
import hu.unideb.inf.meinauto.xml2_meinauto.model.Auto;
import hu.unideb.inf.meinauto.xml2_meinauto.model.SearchResultItem;

public class AutoSuche {

		private static Logger logger = LoggerFactory.getLogger(AutoSuche.class);
		private static final int TIMEOUT_IN_SECONDS = 60000;
		private static final String URI ="http://www.meinauto.de/";
		
		
		public AutoSuche() {
		}
		
		private List<SearchResultItem> extractItems(Document doc) throws IOException {
			List<SearchResultItem> items = new LinkedList<SearchResultItem>();
			for (Element element : doc.select("div.vehicle-list:nth-child(2) > div:nth-child(1) > div:nth-child(1) > article:nth-child(1) > div:nth-child(1) > div:nth-child(1)")) {
				String uri = null;
				try {
					uri = element.select("div.vehicle-list:nth-child(2) > div:nth-child(1) > div:nth-child(1) > article:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)").get(0).attr("abs:href").trim().split("\\?")[0];
				} catch(Exception e) {
					throw new IOException("Malformed document");
				}
				logger.debug("Uri: {}", uri);

				String name = null;
				try {
					name = element.select("div.vehicle-list:nth-child(2) > div:nth-child(1) > div:nth-child(1) > article:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)").get(0).text().trim();
				} catch(Exception e) {
					throw new IOException("Malformed document");
				}
				logger.debug("Name: {}", name);
				
				String fahrzeugetype = null;
				try {
					fahrzeugetype = element.select("div.vehicle-list:nth-child(2) > div:nth-child(1) > div:nth-child(1) > article:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)").get(0).text().trim();
					System.out.println(fahrzeugetype);
				} catch(Exception e) {
					throw new IOException("Malformed document");
				}
				logger.debug("Fahrzeugetype: {}", fahrzeugetype);

	/*			String author = null;
				try {
					author = element.select("div.item-info > p.author").get(0).text().trim();
				} catch(Exception e) {
					logger.warn("No author provided");
				}
				logger.debug("Author: {}", author);

				LocalDate date = null;
				try {
					date = LocalDate.parse(
						element.select("div.item-info > p.published").get(0).text().trim(),
						formatter
					);
				} catch(Exception e) {
					logger.warn("No publication date provided");
				}
				logger.debug("Date: {}", date);

				String format = null;
				try {
					format = element.select("div.item-info > p.format").get(0).text().trim();
				} catch(Exception e) {
					throw new IOException("Malformed document");
				}
				logger.debug("Format: {}", format);*/

				SearchResultItem item = new SearchResultItem(uri, name, fahrzeugetype);
				items.add(item);
			}
			return items;
		}
		
		public SearchResultItem parse(String url) throws IOException {
			WebView webView = new WebView();
			WebEngine webEngine = webView.getEngine();
	        webEngine.load("http://www.google.com/");
	        webView.setMinSize(300, 300);
	        webView.setMaxSize(300, 300);
	        //System.out.println(webEngine.getDocument().getElementById("ma_carbox_Lada_Kalina").getTextContent());
			/*Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
			SearchResultItem ri = parse(doc);
			ri.setUri(url);
			return ri;*/
	        return null;
		}

		public SearchResultItem parse(File file) throws IOException {
			Document doc = Jsoup.parse(file, null);
			SearchResultItem ri = parse(doc);
			ri.setUri(file.toURI().toString());
			return ri;
		}

		private SearchResultItem parse(Document doc) throws IOException {
			System.out.println(doc.html());
			SearchResultItem sri = new SearchResultItem();
			
			String name = null;
			try {
				name = doc.select("div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) ").text().trim();
				Elements e = doc.select("div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)");
				for(Element el: e){
					System.out.println(el.text());
				}
			//	name = doc.select("div.widget-foldable:nth-child(5) > div:nth-child(1)").text().trim();
				System.out.println(name+" name");
			
			} catch(Exception e) {
				throw new IOException("Malformed document");
			}
			sri.setName(name);
			
			/*String uri = null;
			try {
				uri = doc.select("div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > img:nth-child(1)").get(0).attr("abs:href").trim().split("\\?")[0];
			} catch(Exception e) {
				throw new IOException("Malformed document");
			}*/
			//logger.debug("Uri: {}", uri);
			//sri.setUri(uri);
			
			return sri;
			
		}
		
		public static void main(String[] args){
			if (args.length != 1) {
				System.err.printf("Usage: java %s <url>\n", AutoParser.class.getName());
				System.exit(1);
			}
			try {
				SearchResultItem ri = new AutoSuche().parse(args[0]);
				System.out.println(ri);
				JAXBUtil.toXML(ri, System.out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

