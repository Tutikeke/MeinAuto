package hu.unideb.inf.meinauto.xml2_meinauto.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)

public class Gepackraumvol {

		@XmlAttribute(required = true)
		private BigDecimal volohnesitze;

		@XmlAttribute(required = true)
		private BigDecimal volsitze;
		
		public BigDecimal getVolohnesitze() {
			return volohnesitze;
		}

		public void setVolohnesitze(BigDecimal volohnesitze) {
			this.volohnesitze = volohnesitze;
		}

		public BigDecimal getVolsitze() {
			return volsitze;
		}

		public void setVolsitze(BigDecimal volsitze) {
			this.volsitze = volsitze;
		}

		public Gepackraumvol() {}

		public Gepackraumvol(String s) throws ParseException {
			Pattern pattern = Pattern.compile("(^\\d*.*\\d*)\\s(\\d*.*\\d*)\\s");
			Matcher matcher = pattern.matcher(s);
		
			if (! matcher.matches()) throw new ParseException(s, 0);
			DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(java.util.Locale.ENGLISH);
			df.setParseBigDecimal(true);
			volsitze = (BigDecimal) df.parse(matcher.group(1));
			volohnesitze = (BigDecimal) df.parse(matcher.group(2));
		}
		
		public Gepackraumvol(BigDecimal volohnesitze, BigDecimal volsitze) {
			this.volohnesitze = volohnesitze;
			this.volsitze = volsitze;
		}
		
		public String toString() {
			return String.format("%.2fmm x %.2fmm", volohnesitze, volsitze);
		}
	}
