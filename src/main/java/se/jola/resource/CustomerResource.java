package se.jola.resource;

import static se.jola.model.parser.CustomerParser.asXml;
import static se.jola.model.parser.CustomerParser.createElement;
import static se.jola.model.parser.CustomerParser.fromXml;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nu.xom.Document;
import nu.xom.Element;
import se.jola.model.Customer;
import se.jola.repos.CustomerRepository;

@Path("customer")
@Component
@Produces(MediaType.APPLICATION_XML)
public final class CustomerResource {

	@Autowired
	private CustomerRepository repo;

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("{id}")
	public Response getCustomer(@PathParam("id") Long id) {

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
	@Consumes(MediaType.APPLICATION_XML)
	public Response createCustomerWithXml(String content) {

		Customer customer = repo.createCustomer(fromXml(content));

		URI location = uriInfo.getAbsolutePathBuilder().path(CustomerResource.class, "getCustomer")
				.build(customer.getId());

		return Response.created(location).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") Long id) {

		repo.deleteCustomer(id);

		return Response.ok().build();
	}

	// @GET
	// public Response getCustomers(@BeanParam PageRequestBean bean) {
	//
	// StringBuilder builder = new StringBuilder();
	//
	// List<Customer> customers = repo.getCustomers(bean.getAmount());
	//
	// switch (bean.getSort()) {
	// case "asc":
	// Collections.sort(customers, (a,b)->a.getId().compareTo(b.getId()));
	// break;
	// case "des":
	// Collections.sort(customers, (a,b) -> b.getId().compareTo(a.getId()));
	// default:
	// break;
	// }
	//
	// for (Customer customer : customers) {
	// builder.append(customer.toString() + "\n");
	// }
	//
	// return Response.ok(builder.toString()).build();
	// }

	@GET
	public Response getAllCustomers() {

		List<Customer> customers = repo.getAllCustomers();
		
		Element root = new Element("Customers");

		for (Customer customer : customers) {

			URI uri = uriInfo.getAbsolutePathBuilder().path(CustomerResource.class, "getCustomer")
					.build(customer.getId());

			root.appendChild(createElement("uri-" + customer.getId(), uri.toString()));
		}
		
		String result = new Document(root).toXML();
		
		return Response.status(Status.ACCEPTED).entity(result).build();
	}
}
