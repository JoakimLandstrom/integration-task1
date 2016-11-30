package se.jola.model.parser;

import java.io.File;

import org.json.XML;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import se.jola.model.Customer;

public class CustomerParser {

	public static Customer fromString(String value) {
		String[] parts = value.split(";");
		Long id = Long.parseLong(parts[0]);
		String firstName = parts[1];
		String lastName = parts[2];
		return (Customer) new Customer(firstName, lastName).setId(id);
	}

	public static String asString(Customer customer) {

		return String.format("%s;%s;%s;%s", customer.getId(), customer.getFirstName(), customer.getLastName(),
				customer.getCustomerNumber());
	}

	public static String asXml(Customer customer) {

		try {
			Element root = new Element("customer");
			
			root.appendChild(createElement("id", customer.getId().toString()));
			root.appendChild(createElement("firstName", customer.getFirstName()));
			root.appendChild(createElement("lastName", customer.getLastName()));
			root.appendChild(createElement("number", "" + customer.getCustomerNumber()));
			
			return new Document(root).toXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Customer fromXml(String xml) {
		Customer customer = null;
		
		try {
			Builder parser = new Builder();
			
			Element root = parser.build(xml, null).getRootElement();
			
			System.out.println(root.getChildElements().toString());
			
			customer = new Customer("syso", "syso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	private static Element createElement(String name, String value) {
		Element element = new Element(name);
		element.appendChild(value);
		return element;
	}

}
