package se.jola.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.jola.resource.CustomerResource;

@Component
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig(){
		register(CustomerResource.class);
	}

}
