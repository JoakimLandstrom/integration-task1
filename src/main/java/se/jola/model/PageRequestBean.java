package se.jola.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class PageRequestBean {

	@QueryParam("amount")
	@DefaultValue("5")
	private int amount;
	
	@QueryParam("sort")
	@DefaultValue("asc")
	private String sort;
	
	public int getAmount() {
		return amount;
	}
	
	public String getSort() {
		return sort;
	}
	
}
