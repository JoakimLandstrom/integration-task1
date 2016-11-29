package se.jola.model;

public class Customer extends AbstractEntity{
	
	private String firstName;
	
	private String lastName;
	
	private Long customerNumber;
	
	protected Customer(){
		
	}
	
	public Customer(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public Customer setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Long getCustomerNumber() {
		return customerNumber;
	}
	
	public Customer setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
		return this;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + customerNumber;
	}

}
