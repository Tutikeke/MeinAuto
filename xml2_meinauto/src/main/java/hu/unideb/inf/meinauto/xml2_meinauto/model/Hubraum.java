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
public class Hubraum {

	@XmlAttribute(required = true)
	private BigDecimal min;

	@XmlAttribute(required = true)
	private BigDecimal max;
	
	public Hubraum() {}

	public Hubraum(String s) throws ParseException {
		
		
		Pattern pattern = Pattern.compile("^\\s(\\d*)\\s*(\\d*)\\s");
		Matcher matcher = pattern.matcher(s);
	    	
		if (! matcher.matches()) throw new ParseException(s, 0);
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(java.util.Locale.ENGLISH);
		df.setParseBigDecimal(true);
		min = (BigDecimal) df.parse(matcher.group(1));
		max = (BigDecimal) df.parse(matcher.group(2));
	}
	
	public Hubraum(BigDecimal min, BigDecimal max) {
		this.min = min;
		this.max = max;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}
	
	public String toString() {
		return String.format("%.2fmm x %.2fmm", min, max);
	}
}
