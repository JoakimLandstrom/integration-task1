package se.jola.repos;

import javax.inject.Singleton;

import se.jola.model.Customer;

@Singleton
public interface CustomerRepository {
	
	public Customer getCustomer(Long id);
	
	public Customer updateCustomer(Long id, Customer customer);
	
	public void deleteCustomer(Long id);
	
	public Customer createCustomer(Customer customer);

}
