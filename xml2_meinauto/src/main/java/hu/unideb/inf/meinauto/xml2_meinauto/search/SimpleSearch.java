package hu.unideb.inf.meinauto.xml2_meinauto.search;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import hu.unideb.inf.meinauto.xml2_meinauto.model.SearchResults;
import hu.unideb.inf.jaxb.JAXBUtil;
import hu.unideb.inf.meinauto.xml2_meinauto.parser.AutoSuche;

public class SimpleSearch extends AutoSuche{

	private static final String SEARCH_URI = "http://www.meinauto.de/";
	
	public SimpleSearch() {
	}

	public SimpleSearch(int maxItems) {
		//super(maxItems);
	}
	
	/*public SearchResults doSearch(String query) throws IOException {
		Document doc = Jsoup.connect(SEARCH_URI).userAgent("Mozilla").data("searchTerm", query).data("search", "Find auto").get();
		return parse(doc);
	}*/

	private static void printHelpAndExit(int status) {
		System.err.printf("Usage: java %s [--limit <integer>] <term>\n", SimpleSearch.class.getName());
		System.exit(status);
	}

	/*public static void main(String[] args) {
		SimpleSearch search = new SimpleSearch();
		String searchTerm = null;
		if (args.length != 1 && args.length != 3) {
			printHelpAndExit(-1);
		} else if (args.length == 1) {
			searchTerm = args[0];
		} else if (args.length == 3) {
			if (! args[0].equals("--limit")) {
				printHelpAndExit(1);
			}
			try {
				search.setMaxItems(Integer.parseInt(args[1]));
			} catch(NumberFormatException e) {
				printHelpAndExit(1);
			}
			searchTerm = args[2];
		}
		try {
			SearchResults results = search.doSearch(searchTerm);
			JAXBUtil.toXML(results, System.out);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/

	
}
