package hu.unideb.inf.meinauto.xml2_meinauto.model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Verbrauch {


	@XmlAttribute(required = true)
	private float min;

	@XmlAttribute(required = true)
	private float max;
	
	public Verbrauch() {}

	public Verbrauch(String s) throws ParseException {
		
		
		Pattern pattern = Pattern.compile("\\s*(\\d*.\\d*)\\s*(\\d*.\\d*)\\s*");
		Matcher matcher = pattern.matcher(s);
		
		if (! matcher.matches()) throw new ParseException(s, 0);

		DecimalFormat df = new DecimalFormat("0.00");
		
		System.out.println("kedwfhe");
		min = Float.parseFloat(matcher.group(1));
		max = Float.parseFloat(matcher.group(2));
	}
	
	public Verbrauch(Float min, Float max) {
		this.min = min;
		this.max = max;
	}

	public Float getMin() {
		return min;
	}

	public void setMax(Float max) {
		this.max = max;
	}

	public Float getMax() {
		return max;
	}

	public void setMin(Float min) {
		this.min = min;
	}
	
	public String toString() {
		return String.format("%.2fmm x %.2fmm", min, max);
	}

}
