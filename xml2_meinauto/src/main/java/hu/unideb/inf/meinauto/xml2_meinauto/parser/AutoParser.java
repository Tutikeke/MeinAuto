package hu.unideb.inf.meinauto.xml2_meinauto.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.jaxb.JAXBUtil;
import hu.unideb.inf.meinauto.xml2_meinauto.model.Auto;
import hu.unideb.inf.meinauto.xml2_meinauto.model.Dimensions;
import hu.unideb.inf.meinauto.xml2_meinauto.model.Gepackraumvol;
import hu.unideb.inf.meinauto.xml2_meinauto.model.Hubraum;
import hu.unideb.inf.meinauto.xml2_meinauto.model.Verbrauch;

public class AutoParser {

	private static Logger logger = LoggerFactory.getLogger(AutoParser.class);

	public AutoParser() {
	}

	public Auto parse(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
		Auto auto = parse(doc);
		auto.setUri(url);
		return auto;
	}

	public Auto parse(File file) throws IOException {
		Document doc = Jsoup.parse(file, null);
		Auto auto = parse(doc);
		auto.setUri(file.toURI().toString());
		return auto;
	}

	private Auto parse(Document doc) throws IOException {
		// TODO Auto-generated method stub

		Auto auto = new Auto();

		String name = null;
		try {
			name = doc
					.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(1) > div > h1")
					.first().text().trim();
			System.out.println(name);

			logger.info("Auto name: {}", name);
		} catch (Exception e) {
			throw new IOException("Malformed document");
		}
		auto.setName(name);

		if (!doc.select(
				"body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(2) > td.table-value")
				.isEmpty()) {
			try {
				String lange = doc
						.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(2) > td.table-value")
						.first().text().trim();
				String breite = doc
						.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(3) > td.table-value")
						.first().text().trim();
				String hohe = doc
						.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(4) > td.table-value")
						.first().text().trim();
				String dimensionn = lange + breite + hohe;

				dimensionn = dimensionn.replace("mm", "");
				logger.info("Dimensions: {}", dimensionn);
				Dimensions dimensions = new Dimensions(dimensionn);
				auto.setDimensions(dimensions);

			} catch (Exception e) {
				throw new IOException("Malformed document");
			}
		}

		if (!doc.select(
				"body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(5) > td.table-value")
				.isEmpty()) {
			try {
				String volumen = doc
						.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(5) > td.table-value")
						.first().text().trim();

				volumen = volumen.replace("l", "");

				logger.info("Volumen: {}", volumen);
				Gepackraumvol gepackraumvol = new Gepackraumvol(volumen);
				auto.setGepackraumvol(gepackraumvol);

			} catch (Exception e) {
				throw new IOException("Malformed document");
			}
		}

		try {
			String string = doc
					.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(1) > div > table > tbody > tr:nth-child(3) > td.table-value")
					.first().text().trim();
			System.out.println(string);

			Pattern pattern = Pattern.compile("(^min\\.\\s\\d*\\.*\\d*\\sccm)\\s(max\\.\\s\\d*\\.*\\d*\\sccm)");
			Matcher matcher = pattern.matcher(string);

			string = string.replaceAll("[A-Za-z]", "").replaceAll("\\.", "");

			System.out.println(string);

			logger.info("Hubraum: {}", string);

			Hubraum hubraum = new Hubraum(string);
			auto.setHubraum(hubraum);

		} catch (Exception e) {
			throw new IOException("Malformed document");
		}
		
		try {
			String string = doc
					.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(1) > div > table > tbody > tr:nth-child(7) > td.table-value")
					.first().text().trim();

			Pattern pattern = Pattern.compile("(min\\.\\s\\d*,\\d*\\sl/\\d*km)\\s(max\\.\\s\\d*,\\d*\\sl/\\d*km)");
			Matcher matcher = pattern.matcher(string);

			string = string.replaceAll("[A-Za-z]", "").replaceAll("\\.", "").replaceAll("/100", "").replaceAll(",", ".");

			logger.info("Verbrauch per 100 km: {}", string);

			Verbrauch verbrauch = new Verbrauch(string);
			auto.setVerbrauch(verbrauch);

		} catch (Exception e) {
			throw new IOException("Malformed document");
		}

		String turen = null;
		Integer turennr = null;
		try {

			turen = doc
					.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(7) > td.table-value")
					.first().text().trim();

			Pattern pattern = Pattern.compile("\\d Türen");
			Matcher matcher = pattern.matcher(turen);

			if (!matcher.matches())
				throw new IOException("Malformed document");

			if (matcher.group(0) != null) {
				turennr = Character.getNumericValue(turen.charAt(0));
				System.out.println(turennr);
			}
			logger.info("Türen: {}", turennr);
		} catch (Exception e) {
			throw new IOException("Malformed document");
		}
		auto.setTuren(turennr);

		String fahrzeugtyp = null;
		try {

			fahrzeugtyp = doc
					.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > table > tbody > tr:nth-child(2) > td")
					.first().text().trim();
			System.out.println(fahrzeugtyp);
			Pattern pattern = Pattern.compile(".*");
			Matcher matcher = pattern.matcher(fahrzeugtyp);

			if (!matcher.matches())
				throw new IOException("Malformed document");

			logger.info("Fahrzeugtyp: {}", fahrzeugtyp);
		} catch (Exception e) {
			throw new IOException("Malformed document");
		}
		auto.setFahrzeugtyp(fahrzeugtyp);

		String sitzen = null;
		Integer vsitzenr = null;
		Integer rsitzenr = null;

		try {

			sitzen = doc
					.select("body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > table > tbody > tr:nth-child(8) > td.table-value")
					.first().text().trim();
			String[] sitzenn = sitzen.split(",");

			Pattern pattern = Pattern.compile("\\d Vordersitze, \\d Rücksitze");
			Matcher matcher = pattern.matcher(sitzen);

			if (!matcher.matches())
				throw new IOException("Malformed5 document");

			if (matcher.group(0) != null) {
				vsitzenr = Character.getNumericValue(sitzenn[0].charAt(0));
				rsitzenr = Character.getNumericValue(sitzenn[1].charAt(1));
				System.out.println(rsitzenr);
			}
			logger.info("Vordersitze: {}", vsitzenr);
			logger.info("Rücksitze: {}", rsitzenr);
		} catch (Exception e) {
			throw new IOException("Malformed document");
		}
		auto.setvSitze(vsitzenr);
		auto.setrSitze(rsitzenr);


		List<String> Austattungsm = null;
		Elements Austattungsmle = doc.select(
				"body > div.page > div > section > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > div > div > div:nth-child(4) > div > div:nth-child(3) > div:nth-child(1) > div > table > tbody > tr > td:nth-child(2)");
		String elm = Austattungsmle.text();
		System.out.println(elm);
		if (Austattungsmle != null && Austattungsmle.size() != 0) {
			Austattungsm = new ArrayList<String>();
			for (Element austt : Austattungsmle) {
				String austtName = austt.text();
				Austattungsm.add(new String(austtName));
			}
		}
		auto.setAustattungsm(Austattungsm);

		return auto;

	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: java %s <url>\n", AutoParser.class.getName());
			System.exit(1);
		}
		try {
			Auto auto = new AutoParser().parse(args[0]);
			System.out.println(auto);
			JAXBUtil.toXML(auto, System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
