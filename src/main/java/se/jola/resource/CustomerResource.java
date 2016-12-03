package se.jola.resource;

import static se.jola.model.parser.CustomerParser.asXml;
import static se.jola.model.parser.CustomerParser.fromXml;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.jola.model.Customer;
import se.jola.model.PageRequestBean;
import se.jola.repos.CustomerRepository;

@Path("customer")
@Component
public class CustomerResource {

	@Autowired
	private CustomerRepository repo;

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCustomer(@PathParam("id") Long id) {

		return Response.status(Status.OK).entity(repo.getCustomer(id).toString()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCustomerAsXml(@PathParam("id") Long id) {

		return Response.status(Status.OK).entity(asXml(repo.getCustomer(id))).build();
	}

	@PUT
	@Path("{id}")
	public Response updateCustomer(@PathParam("id") Long id, String content) {

		Customer customer = new Customer(content, content);

		repo.updateCustomer(id, customer);

		return Response.status(Status.NO_CONTENT).build();
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response createCustomer(String content) {

		Customer customer = repo.createCustomer(new Customer(content, content));
		
		return Response.status(Status.CREATED).header("Location", "customer/" + customer.getId()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createCustomerWithXml(String content){
		
		Customer customer = repo.createCustomer(fromXml(content));
		
		return Response.status(Status.CREATED).header("Location", "customer/" + customer.getId()).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {

		repo.deleteCustomer(id);

		return Response.ok().build();
	}

	@GET
	public Response getCustomers(@BeanParam PageRequestBean bean) {

		StringBuilder builder = new StringBuilder();

		List<Customer> customers = repo.getCustomers(bean.getAmount());

		switch (bean.getSort()) {
		case "asc":
				Collections.sort(customers, (a,b)->a.getId().compareTo(b.getId()));
			break;
		case "des":
			Collections.sort(customers, (a,b) -> b.getId().compareTo(a.getId()));
		default:
			break;
		}

		for (Customer customer : customers) {
			builder.append(customer.toString() + "\n");
		}

		return Response.ok(builder.toString()).build();
	}
}
