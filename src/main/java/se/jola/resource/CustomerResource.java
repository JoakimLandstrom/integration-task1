package se.jola.resource;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import se.jola.model.Customer;
import se.jola.repos.CustomerRepository;
import se.jola.repos.CustomerRepositoryImpl;

@Path("customer/")
public class CustomerResource {

	private static CustomerRepository repo = new CustomerRepositoryImpl();
	private static AtomicLong atomicLong = new AtomicLong();

	@GET
	@Path("{id}")
	public Response getCustomer(@PathParam("id") Long id) {

		return Response.status(Status.OK).entity(repo.getCustomer(id).toString()).build();
	}

	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Long id, String content) {
		
		Customer customer = new Customer(content, content);
		
		repo.updateCustomer(id, customer);
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	public Response createCustomer(String content){
		Customer customer = new Customer(content, content);
		customer.setId(atomicLong.incrementAndGet());
		
		repo.createCustomer(customer);
		
		return Response.status(Status.CREATED).header("Location", "customer/" + customer.getId()).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id){
		
		repo.deleteCustomer(id);
		
		return Response.ok().build();
	}
	

}
