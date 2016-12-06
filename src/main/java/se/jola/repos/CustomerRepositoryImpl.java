package se.jola.repos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import se.jola.model.Customer;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

	private static Map<Long, Customer> customers = new HashMap<Long, Customer>();
	private static AtomicLong atomicLong = new AtomicLong();


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

		customer.setId(atomicLong.incrementAndGet());
		
		customers.put(customer.getId(), customer);

		return customer;
	}

	@Override
	public List<Customer> getCustomers(int amount) {
		
		return Collections.list(Collections.enumeration(customers.values())).subList(0, amount);
	}
	
	@Override
	public List<Customer> getAllCustomers(){
		return new ArrayList<Customer>(customers.values());
	}
	
	

}
