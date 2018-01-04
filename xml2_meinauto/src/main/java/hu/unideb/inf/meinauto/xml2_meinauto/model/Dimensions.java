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

public class Dimensions {

	@XmlAttribute(required = true)
	private BigDecimal lange;

	@XmlAttribute(required = true)
	private BigDecimal breite;
	
	@XmlAttribute(required = true)
	private BigDecimal hohe;
	
	public Dimensions() {}

	public Dimensions(String s) throws ParseException {
		Pattern pattern = Pattern.compile("(^\\d*.*\\d*)\\s(\\d*.*\\d*)\\s(\\d*.*\\d*)\\s");
		Matcher matcher = pattern.matcher(s);
	
		if (! matcher.matches()) throw new ParseException(s, 0);
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(java.util.Locale.ENGLISH);
		df.setParseBigDecimal(true);
		lange = (BigDecimal) df.parse(matcher.group(1));
		breite = (BigDecimal) df.parse(matcher.group(2));
		hohe = (BigDecimal) df.parse(matcher.group(3));
	}
	
	public Dimensions(BigDecimal breite, BigDecimal lange, BigDecimal hohe) {
		this.lange = lange;
		this.breite = breite;
		this.hohe = hohe;
	}

	public BigDecimal getLange() {
		return lange;
	}

	public void setLange(BigDecimal lange) {
		this.lange = lange;
	}

	public BigDecimal getBreite() {
		return breite;
	}

	public void setBreite(BigDecimal breite) {
		this.breite = breite;
	}
	
	public BigDecimal getHohe() {
		return hohe;
	}
	
	public void setHohe(BigDecimal hohe) {
		this.hohe = hohe;
	}
	
	public String toString() {
		return String.format("%.2fmm x %.2fmm x %.2fmm", lange, breite, hohe);
	}
}