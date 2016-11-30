package se.jola.repos;

import java.util.List;

import se.jola.model.Customer;

public interface CustomerRepository {
	
	public Customer getCustomer(Long id);
	
	public Customer updateCustomer(Long id, Customer customer);
	
	public void deleteCustomer(Long id);
	
	public Customer createCustomer(Customer customer);
	
	public List<Customer> getCustomers(int amout); 

}
