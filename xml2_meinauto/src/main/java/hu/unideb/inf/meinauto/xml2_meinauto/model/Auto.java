package hu.unideb.inf.meinauto.xml2_meinauto.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@javax.xml.bind.annotation.XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(
	propOrder = {
		"ps",
		"name",
		"hubraum",
		"verbrauch",
		"fahrzeugtyp",
		"dimensions",
		"turen",
		"vsitze",
		"rsitze",
		"austattungsm",
		"gepackraumvol"
	}
)

public class Auto {

	@XmlAttribute(required = true)
	private String uri;
	
	@XmlElement(name = "austattungsm", required = true)
	private List<String> austattungsm = new ArrayList<String>();
	
	public List<String> getAustattungsm() {
		return austattungsm;
	}

	public void setAustattungsm(List<String> austattungsm2) {
		this.austattungsm = austattungsm2;
	}

	@XmlElement(required = true)
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(required = true)
	private String ps;
	
	@XmlElement(required = true)
	private Gepackraumvol gepackraumvol;
	
	public Gepackraumvol getGepackraumvol() {
		return gepackraumvol;
	}
	
	public void setGepackraumvol(Gepackraumvol gepackraumvol) {
		this.gepackraumvol = gepackraumvol;
	}

	@XmlElement(required = true)
	private Hubraum hubraum;
	
	@XmlElement(required = true)
	private Verbrauch verbrauch;
	
	@XmlElement(required = true)
	private String fahrzeugtyp;
	
	@XmlElement(required = false)
	private Dimensions dimensions;
	
	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	@XmlElement(required = true)
	private Integer turen;
	
	@XmlElement(required = true)
	private Integer vsitze;
	
	@XmlElement(required = true)
	private Integer rsitze;
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public Hubraum getHubraum() {
		return hubraum;
	}

	public void setHubraum(Hubraum hubraum) {
		this.hubraum = hubraum;
	}

	public Verbrauch getVerbrauch() {
		return verbrauch;
	}

	public void setVerbrauch(Verbrauch verbrauch) {
		this.verbrauch = verbrauch;
	}

	public String getFahrzeugtyp() {
		return fahrzeugtyp;
	}

	public void setFahrzeugtyp(String fahrzeugtyp) {
		this.fahrzeugtyp = fahrzeugtyp;
	}

	public Integer getTuren() {
		return turen;
	}

	public void setTuren(Integer turen) {
		this.turen = turen;
	}

	public Integer getvSitze() {
		return vsitze;
	}
	
	public Integer getrSitze() {
		return rsitze;
	}

	public void setvSitze(Integer vsitze) {
		this.vsitze = vsitze;
	}
	
	public void setrSitze(Integer rsitze) {
		this.rsitze = rsitze;
	}

	public static void main(String[] args) throws Exception {
		Auto auto = new Auto();
		auto.setUri("http://www.meinauto.de/seat/neuwagen/43-leon/angebote/leon-st");
		auto.getTuren();

		try {
			hu.unideb.inf.jaxb.JAXBUtil.toXML(auto, System.out);
		} catch(javax.xml.bind.JAXBException e) {
			e.printStackTrace();
		}
	}

}
