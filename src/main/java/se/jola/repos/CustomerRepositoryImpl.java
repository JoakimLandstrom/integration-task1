package se.jola.repos;

import java.util.HashMap;
import java.util.Map;

import se.jola.model.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {

	private static Map<Long, Customer> customers = new HashMap<Long, Customer>();

	@Override
	public Customer getCustomer(Long id) {

		return customers.get(id);
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) {

		return customers.put(id, customer);
	}

	@Override
	public void deleteCustomer(Long id) {

		customers.remove(id);
	}

	@Override
	public Customer createCustomer(Customer customer) {

		customers.put(customer.getId(), customer);

		return customer;
	}

}
