package hu.unideb.inf.meinauto.xml2_meinauto.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
	propOrder = {
		"name",
		"fahrzeugtype"
	}
)

public class SearchResultItem {

	@XmlElement
	private String name;
	
	@XmlElement
	private String fahrzeugtype;
	
	public String getFahrzeugtype() {
		return fahrzeugtype;
	}
	public void setFahrzeugtype(String fahrzeugtype) {
		this.fahrzeugtype = fahrzeugtype;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	@XmlAttribute(required = true)
	private String uri;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public SearchResultItem() {
	}

	public SearchResultItem(String uri, String name, String fahrzeugtype) {
		this.uri = uri;
		this.name = name;
		this.fahrzeugtype = fahrzeugtype;
	}
	
/*	@Override
	public String toString() {
		return "SearchResultItem [name=" + name + "]";
	}*/
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public static void main(String[] args) {
		SearchResultItem item = new SearchResultItem();
		item.setUri("http://www.meinauto.de/seat/neuwagen/43-leon/angebote/leon-st");
		item.setName("Seat Leon ST");
		item.setFahrzeugtype("kombi");
		System.out.println(item);
		try {
			hu.unideb.inf.jaxb.JAXBUtil.toXML(item, System.out);
		} catch(javax.xml.bind.JAXBException e) {
			e.printStackTrace();
		}
	}
}
